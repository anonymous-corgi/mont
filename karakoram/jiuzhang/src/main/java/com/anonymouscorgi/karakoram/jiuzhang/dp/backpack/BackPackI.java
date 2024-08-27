package com.anonymouscorgi.karakoram.jiuzhang.dp.backpack;

/**
 * LintCode 92 BackPack https://www.lintcode.com/problem/backpack/description
 * <p>
 * Description Given n items with size Ai, an integer m denotes the size of a backpack. How full you
 * can fill this backpack?
 * <p>
 * Example If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3,
 * 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can
 * select [2, 3, 7] so that we can fulfill the backpack.
 * <p>
 * You function should return the max size we can fill in the given backpack.
 */
final class BackPackI {

  //Count Max weight can be stored in m capacity without repeated use
  interface Algorithm {

    int backPack(int capacity, int[] weights);
  }

  //This is the optimal way to count the maximum capacity
  static final class Method1 implements Algorithm {

    @Override
    public int backPack(int capacity, int[] weights) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      final int[] maxWeightAtCapacity = new int[capacity + 1];
      for (final int weight : weights) {
        for (int i = capacity; i >= weight; i--) {
          maxWeightAtCapacity[i] = Math.max(maxWeightAtCapacity[i],
              maxWeightAtCapacity[i - weight] + weight);
        }
      }
      return maxWeightAtCapacity[capacity];
    }
  }

  //This solution is mostly used for count number of possibilities.
  //Similar to BackPackVI
  static final class Method2 implements Algorithm {

    @Override
    public int backPack(int capacity, int[] weights) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      final boolean[] feasibleWeights = new boolean[capacity + 1];
      feasibleWeights[0] = true;
      for (final int weight : weights) {
        for (int i = capacity; i >= weight; i--) {
          feasibleWeights[i] |= feasibleWeights[i - weight];
        }
      }

      for (int i = capacity; 0 < i; i--) {
        if (feasibleWeights[i]) {
          return i;
        }
      }
      return 0;
    }
  }
}
