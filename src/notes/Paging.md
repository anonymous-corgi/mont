# Paging

- ### SourceCode Analysis
  + ##### ***PagedListAdatper*** 
  only exposes **submitList()** method for data modification, and uses ***AsyncPagedListDiffer*** to handle the difference between new and old list.
  + ##### ***AsyncPagedListDiffer\<T\>*** ***\*Key Class***
  Helper object for mapping a {@link PagedList} into a {@link androidx.recyclerview.widget.RecyclerView.Adapter RecyclerView.Adapter}.
  Used to handle the data change and UI update, classify each data change, use **mUpdateCallback** to do the coresponding UI update, calls listener about the list change, and calls commitCallback in the end.
  Use 
  ```java
  public class AsyncPagedListDiffer<T> {
    
    // Corresponding UI update Callback strategy
    final ListUpdateCallback mUpdateCallback;
    // Identification rules of the item in list.
    final AsyncDifferConfig<T> mConfig;
    
    private PagedList<T> mPagedList;
    // mSnapshot stores the previous list while updating new list.
    private PagedList<T> mSnapshot;
    
    public AsyncPagedListDiffer(@NonNull RecyclerView.Adapter adapter,
        @NonNull DiffUtil.ItemCallback<T> diffCallback) {
      mUpdateCallback = new AdapterListUpdateCallback(adapter);
      mConfig = new AsyncDifferConfig.Builder<>(diffCallback).build();
    }
    
    public T getItem(int index) {
      if (mPagedList == null) {
        if (mSnapshot == null) {
          throw new IndexOutOfBoundsException(
                "Item count is zero, getItem() call is invalid");
        } else {
          return mSnapshot.get(index);
        }
      }
      mPagedList.loadAround(index);
      return mPagedList.get(index);
    }
    
    public int getItemCount() {
      if (mPagedList != null) {
        return mPagedList.size();
      }
      return mSnapshot == null ? 0 : mSnapshot.size();
    }
    
    public void submitList(@Nullable final PagedList<T> pagedList,
        @Nullable final Runnable commitCallback) {
      //J: make sure contiguous is consistent.
      if (pagedList != null) {
        if (mPagedList == null && mSnapshot == null) {
            mIsContiguous = pagedList.isContiguous();
        } else {
          if (pagedList.isContiguous() != mIsContiguous) {
            throw new IllegalArgumentException("AsyncPagedListDiffer cannot handle both"
                    + " contiguous and non-contiguous lists.");
          }
        }
      }

      // incrementing generation means any currently-running diffs are discarded when they finish
      final int runGeneration = ++mMaxScheduledGeneration;

      if (pagedList == mPagedList) {
        // nothing to do (Note - still had to inc generation, since may have ongoing work)
        if (commitCallback != null) {
          commitCallback.run();
        }
        return;
      }

      final PagedList<T> previous = (mSnapshot != null) ? mSnapshot : mPagedList;

      if (pagedList == null) {
        int removedCount = getItemCount();
        if (mPagedList != null) {
          mPagedList.removeWeakCallback(mPagedListCallback);
          mPagedList = null;
        } else if (mSnapshot != null) {
          mSnapshot = null;
        }
        // dispatch update callback after updating mPagedList/mSnapshot
        mUpdateCallback.onRemoved(0, removedCount);
        onCurrentListChanged(previous, null, commitCallback);
        return;
      }
      
      if (mPagedList == null && mSnapshot == null) {
        // fast simple first insert
        mPagedList = pagedList;
        pagedList.addWeakCallback(null, mPagedListCallback);

        // dispatch update callback after updating mPagedList/mSnapshot
        mUpdateCallback.onInserted(0, pagedList.size());

        onCurrentListChanged(null, pagedList, commitCallback);
        return;
      }

      if (mPagedList != null) {
        // first update scheduled on this list, so capture mPages as a snapshot, removing
        // callbacks so we don't have resolve updates against a moving target
        mPagedList.removeWeakCallback(mPagedListCallback);
        mSnapshot = (PagedList<T>) mPagedList.snapshot();
        mPagedList = null;
      }

      if (mSnapshot == null || mPagedList != null) {
        throw new IllegalStateException("must be in snapshot state to diff");
      }

      final PagedList<T> oldSnapshot = mSnapshot;
      final PagedList<T> newSnapshot = (PagedList<T>) pagedList.snapshot();
      mConfig.getBackgroundThreadExecutor().execute(new Runnable() {
        @Override
        public void run() {
          final DiffUtil.DiffResult result;
          result = PagedStorageDiffHelper.computeDiff(
                  oldSnapshot.mStorage,
                  newSnapshot.mStorage,
                  mConfig.getDiffCallback());

          mMainThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
              if (mMaxScheduledGeneration == runGeneration) {
                latchPagedList(pagedList, newSnapshot, result,
                        oldSnapshot.mLastLoad, commitCallback);
              }
            }
          });
        }
      });
    }
  }
  ```
  
  + ##### ***AsyncDifferConfig\<T\>***
  Provides Executor and DiffUtil.ItemCallback<T>
  Executor defines mainThread and backgroundThread
  DiffUtil.ItemCallback<T> provides Identification rules of the item in list.
  ```java to kotlin
  public abstract class PagedList<T>(private mMainThreadExecutor: Executor,
        private mBackgroundThreadExecutor: Executor,
        private mDiffCallback: DiffUtil.ItemCallback<T>) : AbstractList<T>() {
  }
  ```
  
  + ##### ***PagedList\<T\>***
  ```java
  public abstract class PagedList<T> extends AbstractList<T> {
    
    public void loadAround(int index) {
      if (index < 0 || index >= size()) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
      }

      mLastLoad = index + getPositionOffset();
      loadAroundInternal(index);

      mLowestIndexAccessed = Math.min(mLowestIndexAccessed, index);
      mHighestIndexAccessed = Math.max(mHighestIndexAccessed, index);

      /*
      * mLowestIndexAccessed / mHighestIndexAccessed have been updated, so check if we need to
      * dispatch boundary callbacks. Boundary callbacks are deferred until last items are loaded,
      * and accesses happen near the boundaries.
      *
      * Note: we post here, since RecyclerView may want to add items in response, and this
      * call occurs in PagedListAdapter bind.
      */
      tryDispatchBoundaryCallbacks(true);
    }
  }
  ```