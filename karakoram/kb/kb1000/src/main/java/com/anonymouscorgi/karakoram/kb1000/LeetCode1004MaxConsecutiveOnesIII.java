package com.anonymouscorgi.karakoram.kb1000;

import java.util.ArrayList;

interface LeetCode1004MaxConsecutiveOnesIII {

  int longestOnes(int[] nums, int k);


  LeetCode1004MaxConsecutiveOnesIII GREEDY = new LeetCode1004MaxConsecutiveOnesIII() {

    @Override
    public int longestOnes(int[] nums, int k) {
      int left = -1, maxCount = 0, zeroCount = 0;
      for (int right = 0; right < nums.length; right++) {
        if (nums[right] == 0) {
          zeroCount++;
        }
        while (zeroCount > k) {
          if (nums[++left] == 0) {
            zeroCount--;
          }
        }
        maxCount = Math.max(maxCount, right - left);
      }
      return maxCount;
    }
  };

  LeetCode1004MaxConsecutiveOnesIII METHOD = new LeetCode1004MaxConsecutiveOnesIII() {

    @Override
    public int longestOnes(int[] nums, int k) {
      if (nums.length == 1 && k == 1) {
        return 1;
      }
      int value = nums[0] == 1 ? 1 : -1;
      ArrayList<Integer> values = new ArrayList<>();
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i - 1]) {
          value += nums[i] == 1 ? 1 : -1;
        } else {
          values.add(value);
          value = nums[i] == 1 ? 1 : -1;
        }
      }
      values.add(value);

      int maxCount = 0;
      for (int i = 0; i < values.size(); i++) {
        if (values.get(i) < 0) {
          continue;
        }
        int count = 0;
        int remainK = k;
        for (int j = i; j < values.size(); j++) {
          if (values.get(j) > 0) {
            count += values.get(j);
          } else if (-values.get(j) <= remainK) {
            count += -values.get(j);
            remainK += values.get(j);
          } else {
            count += remainK;
            remainK = 0;
            break;
          }
        }
        if (remainK > 0 && i > 0) {
          count += Math.min(-values.get(i - 1), remainK);
          remainK += values.get(i - 1);
        }
        maxCount = Math.max(maxCount, count);
        if (remainK > 0) {
          break;
        }
      }

      return maxCount;
    }
  };
}
