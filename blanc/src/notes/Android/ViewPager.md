# ViewPager

- ### FragmentPagerAdapter
  + ***getItem() & instantiateItem()***
  FragmentPagerAdapter will always try to find Fragment By Tag firstly.
  Only if there is no existed fragment instance, it calls getItem to generate it.
  ```java
  /**
   * Return the Fragment associated with a specified position.
   */
  public abstract Fragment getItem(int position);

  @SuppressWarnings("ReferenceEquality")
  @Override
  public Object instantiateItem(ViewGroup container, int position) {
      if (mCurTransaction == null) {
          mCurTransaction = mFragmentManager.beginTransaction();
      }

      final long itemId = getItemId(position);

      // Do we already have this fragment?
      String name = makeFragmentName(container.getId(), itemId);
      Fragment fragment = mFragmentManager.findFragmentByTag(name);
      if (fragment != null) {
          if (DEBUG) Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
          mCurTransaction.attach(fragment);
      } else {
          fragment = getItem(position);
          if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
          mCurTransaction.add(container.getId(), fragment,
                  makeFragmentName(container.getId(), itemId));
      }
      if (fragment != mCurrentPrimaryItem) {
          fragment.setMenuVisibility(false);
          fragment.setUserVisibleHint(false);
      }

      return fragment;
  }
  ```

  It creates instance before it calls onPageSelected().
