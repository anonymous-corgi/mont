# ViewModel & LiveData

- ### ViewModel
  
  + ##### The advantages of using ViewModel
    + The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
    + The ViewModel class allows data to survive configuration changes such as screen rotations.

  + ##### Caution!
    + A ViewModel must never reference a view, Lifecycle, or any class that may hold a reference to the activity context.
    + ViewModel objects can contain LifecycleObservers, such as LiveData objects. However ViewModel objects must never observe changes to lifecycle-aware observables, such as LiveData objects.

  + ##### The lifecycle of a ViewModel
    + ***ViewModel*** instance is stored in ***ViewModelStore*** after being created, so the sequent call of get() will fetch the ViewModel instance from that ViewModelStore. As a result, the lifecycle of the ViewModelStore determines the lifecycle of the ViewModel.
    + ***The lifecycle of a ViewModel is bound to a LifecycleOwner. If two Fragments want to get same ViewModel instance, they need to parse a same LifecycleOwner - Activity or Fragment.***
    + ***FragmentActivity*** implementing interface ***ViewModelStoreOwner*** has an internal ***ViewModelStore*** instance. What's more, the ***ViewModelStore*** instance will be retained through onSaveInstanceState() and  onRestoreInstanceState(). So the ViewModel instances bound to ***FragmentActivity*** are able to survieve configuration change.
    + It uses the 'LifecycleOwner' - (FragmentActivity or Fragment) to get its FragmentManager(or ChildFragmentManager) and adds a ***HolderFragment*** to this FragmentManager. This HolderFragment has a ***ViewModelStore*** used to store ViewModel instance. So we   
    
  + ##### Source Code Analysis
    + ***ViewModelProviders***
    ```java
    public class ViewModelProviders {
      
      public static ViewModelProvider of(@NonNull Fragment fragment, @NonNull Factory factory) {
        return new ViewModelProvider(ViewModelStores.of(fragment), factory);
      }

      public static ViewModelProvider of(@NonNull FragmentActivity activity,
              @NonNull Factory factory) {
        return new ViewModelProvider(ViewModelStores.of(activity), factory);
      }
    }
    ```
    
    + ***ViewModelStores***
    of() returns the ViewModelStore of the given activity or fragment.
    ```java
    public class ViewModelStores {
      
      public static ViewModelStore of(@NonNull FragmentActivity activity) {
        if (activity instanceof ViewModelStoreOwner) {
          return ((ViewModelStoreOwner) activity).getViewModelStore();
        }
        return holderFragmentFor(activity).getViewModelStore();
      }

      public static ViewModelStore of(@NonNull Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
          return ((ViewModelStoreOwner) fragment).getViewModelStore();
        }
        return holderFragmentFor(fragment).getViewModelStore();
      }
    }
    ```
    
    + ***ViewModelStore***
    Class to store ViewModels.
    ```java
    public class ViewModelStore {

      private final HashMap<String, ViewModel> mMap = new HashMap<>();

      final void put(String key, ViewModel viewModel) {
        ViewModel oldViewModel = mMap.put(key, viewModel);
        if (oldViewModel != null) {
          oldViewModel.onCleared();
        }
      }

      final ViewModel get(String key) {
        return mMap.get(key);
      }

      public final void clear() {
        for (ViewModel vm : mMap.values()) {
          vm.onCleared();
        }
        mMap.clear();
      }
    }
    ```

    + ***ViewModelProvider***
    ```java
    public class HolderFragment extends Fragment {

      private ViewModelStore mViewModelStore = new ViewModelStore();
      
      static class HolderFragmentManager {
        
        HolderFragment holderFragmentFor(FragmentActivity activity) {
          FragmentManager fm = activity.getSupportFragmentManager();
          HolderFragment holder = findHolderFragment(fm);
          if (holder != null) {
            return holder;
          }
          holder = mNotCommittedActivityHolders.get(activity);
          if (holder != null) {
            return holder;
          }

          if (!mActivityCallbacksIsAdded) {
            mActivityCallbacksIsAdded = true;
            activity.getApplication().registerActivityLifecycleCallbacks(mActivityCallbacks);
          }
          holder = createHolderFragment(fm);
          mNotCommittedActivityHolders.put(activity, holder);
          return holder;
        }
        
        private static HolderFragment findHolderFragment(FragmentManager manager) {
          if (manager.isDestroyed()) {
            throw new IllegalStateException("Can't access ViewModels from onDestroy");
          }

          Fragment fragmentByTag = manager.findFragmentByTag(HOLDER_TAG);
          if (fragmentByTag != null && !(fragmentByTag instanceof HolderFragment)) {
            throw new IllegalStateException("Unexpected "
                  + "fragment instance was returned by HOLDER_TAG");
          }
          return (HolderFragment) fragmentByTag;
        }
        
        private static HolderFragment createHolderFragment(FragmentManager fragmentManager) {
          HolderFragment holder = new HolderFragment();
          fragmentManager.beginTransaction().add(holder, HOLDER_TAG).commitAllowingStateLoss();
          return holder;
        }
      }  
    ```
    **The Key is createHolderFragment()** method.

- ### LiveData

  + ##### The Core of LiveData
    + It wrap an normal observer with a LifecycleOwner into LifecycleBoundObserver, so that this observer is able to judge the state of its bound LifecycleOwner when LiveData call *considerNotify()* as well as unsubscribe - *removeObserver()* the LiveData when its bound LifecycleOwner is in *DESTROYED* state.

  + ##### Source Code Analysis
    + When you call observe(), LiveData binds the LifecycleOwner and Observer creating a Wrapper object (LifecycleBoundObserver). So if LifecycleOwner has state change, the wrapper object will be notified.
    ***LifecycleBoundObserver***
    ```java
    class LifecycleBoundObserver implements GenericLifecycleObserver {
      public final LifecycleOwner owner;
      public final Observer<T> observer;
      public boolean active;
      public int lastVersion = START_VERSION;

      LifecycleBoundObserver(LifecycleOwner owner, Observer<T> observer) {
        this.owner = owner;
        this.observer = observer;
      }

      @Override
      public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (owner.getLifecycle().getCurrentState() == DESTROYED) {
          removeObserver(observer);
          return;
        }
        // immediately set active state, so we'd never dispatch anything to inactive
        // owner
        activeStateChanged(isActiveState(owner.getLifecycle().getCurrentState()));
      }

      void activeStateChanged(boolean newActive) {
        if (newActive == active) {
          return;
        }
        active = newActive;
        boolean wasInactive = LiveData.this.mActiveCount == 0;
        LiveData.this.mActiveCount += active ? 1 : -1;
        if (wasInactive && active) {
          onActive();
        }
        if (LiveData.this.mActiveCount == 0 && !active) {
          onInactive();
        }
        if (active) {
          dispatchingValue(this);
        }
      }
    }
    ```
   
  + ##### Overall
    
    Follow these steps to work with LiveData objects:
    1. Create an instance of LiveData to hold a certain type of data. This is usually done within your ViewModel class. (LiveData works)
    2. Create an Observer object that defines the onChanged() method, which controls what happens when the LiveData object's held data changes. You usually create an Observer object in a UI controller, such as an activity or fragment.
    3. Attach the Observer object to the LiveData object using the observe() method. The observe() method takes a LifecycleOwner object. This subscribes the Observer object to the LiveData object so that it is notified of changes. You usually attach the Observer object in a UI controller, such as an activity or fragment.

  + ##### The advantages of using LiveData
    
    + **Ensures your UI matches your data state**
      LiveData follows the observer pattern. LiveData notifies Observer objects when the lifecycle state changes. You can consolidate your code to update the UI in these Observer objects. Instead of updating the UI every time the app data changes, your observer can update the UI every time there's a change.
      
    + **No memory leaks**
      Observers are bound to Lifecycle objects and clean up after themselves when their associated lifecycle is destroyed.

    + **No crashes due to stopped activities**
      If the observer's lifecycle is inactive, such as in the case of an activity in the back stack, then it doesn’t receive any LiveData events.

    + **No more manual lifecycle handling**
      UI components just observe relevant data and don’t stop or resume observation. LiveData automatically manages all of this since it’s aware of the relevant lifecycle status changes while observing.
      
    + **Always up to date data**
      If a lifecycle becomes inactive, it receives the latest data upon becoming active again. For example, an activity that was in the background receives the latest data right after it returns to the foreground.

    + **Proper configuration changes**
      If an activity or fragment is recreated due to a configuration change, like device rotation, it immediately receives the latest available data.

    + **Sharing resources**
      You can extend a LiveData object using the singleton pattern to wrap system services so that they can be shared in your app. The LiveData object connects to the system service once, and then any observer that needs the resource can just watch the LiveData object. For more information, see Extend LiveData.

  + ##### Create LiveData objects

    + A LiveData object is usually stored within a ViewModel object and is accessed via a getter method
    + Usually the ViewModel only exposes **immutable LiveData** objects to the observers.

  + ##### Observe LiveData objects

    + In most cases, an app component’s onCreate() method is the right place to begin observing a LiveData object
    + Generally, LiveData delivers updates only when data changes, and only to active observers. An exception to this behavior is that observers also receive an update when they change from an inactive to an active state. Furthermore, if the observer changes from inactive to active a second time, it only receives an update if the value has changed since the last time it became active.
  
  + ##### Update LiveData objects

    + LiveData has no publicly available methods to update the stored data. The MutableLiveData class exposes the setValue(T) and postValue(T) methods publicly.
    
    + MutableLiveData
      + setValue(T value)
      Sets the value. If there are active observers, the value will be dispatched to them.
      This method must be called from the **main thread**. If you need set a value from a background thread, you can use postValue(Object)
      
      + postValue(T value)
      Posts a task to a main thread to set the given value.