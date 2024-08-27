package com.anonymouscorgi.karakoram.jiuzhang.dp.backpack;

final class BackPackV {

  //Count How many ways can get to target without repeated use
  interface Algorithm {

    int backPackV(int[] weights, int capacity);
  }

  static final class Method1 implements Algorithm {

    @Override
    public int backPackV(int[] weights, int capacity) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      int[] maxWayAtCapacity = new int[capacity + 1];
      maxWayAtCapacity[0] = 1;
      for (int weight : weights) {
        //If j starts from weights[i] might cause duplication
        for (int j = capacity; j >= weight; j--) {
          maxWayAtCapacity[j] += maxWayAtCapacity[j - weight];
        }
      }
      return maxWayAtCapacity[capacity];
    }
  }
}
