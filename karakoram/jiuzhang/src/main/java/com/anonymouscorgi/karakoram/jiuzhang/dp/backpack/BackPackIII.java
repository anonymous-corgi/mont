package com.anonymouscorgi.karakoram.jiuzhang.dp.backpack;

//BackPackII doesn't contain repeated object
final class BackPackIII {

  //Count Max value can be stored in m capacity WITH repeated use
  interface Algorithm {

    int backPackIII(int capacity, int[] weights, int[] values);
  }

  //Method1: Loop from items first.
  static Algorithm Method1 = new Algorithm() {

    @Override
    public int backPackIII(int capacity, int[] weights, int[] values) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      int[] maxValueAtCapacity = new int[capacity + 1];
      for (int i = 0; i < weights.length; i++) {
        for (int j = weights[i]; j <= capacity; j++) {
          maxValueAtCapacity[j] = Math.max(maxValueAtCapacity[j],
              maxValueAtCapacity[j - weights[i]] + values[i]);
        }
      }
      return maxValueAtCapacity[capacity];
    }
  };

  //Method2: Loop from weight first.
  static Algorithm Method2 = new Algorithm() {

    @Override
    public int backPackIII(int capacity, int[] weights, int[] values) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      int[] maxValueAtCapacity = new int[capacity + 1];
      for (int i = 1; i <= capacity; i++) {
        for (int j = 0; j < weights.length; j++) {
          if (weights[j] <= i) {
            maxValueAtCapacity[i] = Math.max(maxValueAtCapacity[i],
                maxValueAtCapacity[i - weights[j]] + values[j]);
          }
        }
      }
      return maxValueAtCapacity[capacity];
    }
  };
}
