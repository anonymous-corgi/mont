package com.anonymouscorgi.karakoram.kb0150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

interface LeetCode179LargestNumber {

  String largestNumber(int[] nums);

  LeetCode179LargestNumber METHOD = new LeetCode179LargestNumber() {
    @Override
    public String largestNumber(int[] nums) {
      String[] numStrings = new String[nums.length];
      Arrays.setAll(numStrings, i -> Integer.toString(nums[i]));
      Arrays.sort(numStrings, (a, b) -> compare(a, b));

      StringBuilder sb = new StringBuilder();
      for (String str : numStrings) {
        sb.append(str);
      }
      for (int i = 0, len = sb.length() - 1; i < len; i++) {
        if (sb.charAt(0) == '0') {
          sb.deleteCharAt(0);
        }
      }
      return sb.toString();
    }

    private static int compare(String a, String b) {
      for (int i = 0, len = a.length() + b.length(); i < len; i++) {
        char c1 = i < a.length() ? a.charAt(i) : b.charAt(i - a.length());
        char c2 = i < b.length() ? b.charAt(i) : a.charAt(i - b.length());
        if (c1 != c2) {
          return c2 - c1;
        }
      }
      return 0;
    }
  };
}
