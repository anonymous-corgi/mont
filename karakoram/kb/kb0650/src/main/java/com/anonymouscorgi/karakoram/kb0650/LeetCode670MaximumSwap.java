package com.anonymouscorgi.karakoram.kb0650;

import java.util.ArrayList;
import java.util.Collections;

interface LeetCode670MaximumSwap {

  int maximumSwap(int num);

  LeetCode670MaximumSwap Method =
      new LeetCode670MaximumSwap() {

        @Override
        public int maximumSwap(int num) {
          if (num == 0) {
            return 0;
          }
          ArrayList<Integer> digits = toList(num);
          ArrayList<Integer> maxDigits = new ArrayList<>(digits);
          Collections.sort(maxDigits);

          for (int i = digits.size() - 1; i >= 0; i--) {
            if (maxDigits.get(i) > digits.get(i)) {
              for (int j = 0; j < digits.size(); j++) {
                if (digits.get(j) == maxDigits.get(i)) {
                  int temp = digits.get(i);
                  digits.set(i, digits.get(j));
                  digits.set(j, temp);
                  break;
                }
              }
              break;
            }
          }

          return toInt(digits);
        }

        private ArrayList<Integer> toList(int num) {
          int bit = 1;
          int remain = num;
          ArrayList<Integer> digits = new ArrayList<>();
          while (remain > 0) {
            int value = remain % (bit * 10);
            remain -= value;
            digits.add(value / bit);
            bit *= 10;
          }
          return digits;
        }

        private int toInt(ArrayList<Integer> digits) {
          int bit = 1;
          int value = 0;
          for (int digit : digits) {
            value += digit * bit;
            bit *= 10;
          }
          return value;
        }
      };
}
