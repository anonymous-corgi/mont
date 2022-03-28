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