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
    
    // This is the Key!
    // submitList() will use pagedList, mPagedList and mSnapshot. 
    public void submitList(@Nullable final PagedList<T> pagedList,
        @Nullable final Runnable commitCallback) {
      + Set mSnapshot = mPagedList?.snapshot()
      + Set mPagedList = pagedList
      + Then pagedList.addWeakCallback(null, mPagedListCallback)
      + According to the change between pagedList(newList) and mPagedList(oldList), submitList() uses mUpdateCallback to notify Adapter about the change in list. 
      + Calls onCurrentListChanged() (notify all PagedListListener in mListeners). Calls commitCallback?.run() (commitCallback passed in as a parameter)
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
    
    
    /**
     * Call this when mLowest/HighestIndexAccessed are changed, or
     * mBoundaryCallbackBegin/EndDeferred is set.
     */
    void tryDispatchBoundaryCallbacks(boolean post) {
      final boolean dispatchBegin = mBoundaryCallbackBeginDeferred
              && mLowestIndexAccessed <= mConfig.prefetchDistance;
      final boolean dispatchEnd = mBoundaryCallbackEndDeferred
              && mHighestIndexAccessed >= size() - 1 - mConfig.prefetchDistance;
      ...
    }
    
    void dispatchBoundaryCallbacks(boolean begin, boolean end) {
      // safe to deref mBoundaryCallback here, since we only defer if mBoundaryCallback present
      if (begin) {
        mBoundaryCallback.onItemAtFrontLoaded(mStorage.getFirstLoadedItem());
      }
      if (end) {
        mBoundaryCallback.onItemAtEndLoaded(mStorage.getLastLoadedItem());
      }
    }
  }
  ```
  
  + ##### ***PagedList\<T\>.Config***
  ```java
  public static class Config {
    /**
     * When {@link #maxSize} is set to {@code MAX_SIZE_UNBOUNDED}, the maximum number of items
     * loaded is unbounded, and pages will never be dropped.
     */
    public static final int MAX_SIZE_UNBOUNDED = Integer.MAX_VALUE;

    /**
     * Size of each page loaded by the PagedList.
     */
    public final int pageSize;

    /**
     * Prefetch distance which defines how far ahead to load.
     * <p>
     * If this value is set to 50, the paged list will attempt to load 50 items in advance of
     * data that's already been accessed.
     *
     * @see PagedList#loadAround(int)
     */
    public final int prefetchDistance;

    /**
     * Defines whether the PagedList may display null placeholders, if the DataSource provides
     * them.
     */
    public final boolean enablePlaceholders;

    /**
     * Defines the maximum number of items that may be loaded into this pagedList before pages
     * should be dropped.
     * <p>
     * {@link PageKeyedDataSource} does not currently support dropping pages - when
     * loading from a {@code PageKeyedDataSource}, this value is ignored.
     *
     * @see #MAX_SIZE_UNBOUNDED
     * @see Builder#setMaxSize(int)
     */
    public final int maxSize;

    /**
     * Size hint for initial load of PagedList, often larger than a regular page.
     */
    public final int initialLoadSizeHint;
  }
  ```
  
  + ##### ***PagedList\<T\>.BoundaryCallback\<T\>***
  ```java
  @MainThread
  public abstract static class BoundaryCallback<T> {
    /**
     * Called when zero items are returned from an initial load of the PagedList's data source.
     */
    public void onZeroItemsLoaded() {}

    /**
     * Called when the item at the front of the PagedList has been loaded, and access has
     * occurred within {@link Config#prefetchDistance} of it.
     * <p>
     * No more data will be prepended to the PagedList before this item.
     *
     * @param itemAtFront The first item of PagedList
     */
    public void onItemAtFrontLoaded(@NonNull T itemAtFront) {}

    /**
     * Called when the item at the end of the PagedList has been loaded, and access has
     * occurred within {@link Config#prefetchDistance} of it.
     * <p>
     * No more data will be appended to the PagedList after this item.
     *
     * @param itemAtEnd The first item of PagedList
     */
    public void onItemAtEndLoaded(@NonNull T itemAtEnd) {}
  }
  ```