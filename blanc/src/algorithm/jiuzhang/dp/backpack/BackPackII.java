package algorithm.jiuzhang.dp.backpack;

/**
 * LintCode 125. Backpack II There are n items and a backpack with size m. Given array A
 * representing the size of each item and array V representing the value of each item.
 *
 * What's the maximum value can you put into the backpack?
 *
 * Example Example 1:
 *
 * Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4] Output: 9 Explanation: Put A[1] and A[3] into
 * backpack, getting the maximum value V[1] + V[3] = 9 Example 2:
 *
 * Input: m = 10, A = [2, 3, 8], V = [2, 5, 8] Output: 10 Explanation: Put A[0] and A[2] into
 * backpack, getting the maximum value V[0] + V[2] = 10 Challenge O(nm) memory is acceptable, can
 * you do it in O(m) memory?
 *
 * Notice A[i], V[i], n, m are all integers. You can not split an item. The sum size of the items
 * you want to put into backpack can not exceed m. Each item can only be picked up once
 */
class BackPackII {

  //Count Max value can be stored in m capacity WITHOUT repeated use
  interface Method {

    int backPackII(int capacity, int[] weights, int[] values);
  }

  class Method1 implements Method {

    @Override
    public int backPackII(int capacity, int[] weights, int[] values) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      int[] maxValueAtCapacity = new int[capacity + 1];
      for (int i = 0; i < weights.length; i++) {
        for (int j = capacity; j >= weights[i]; j--) {
          maxValueAtCapacity[j] =
              Math.max(maxValueAtCapacity[j],
                  maxValueAtCapacity[j - weights[i]] + values[i]);
        }
      }
      return maxValueAtCapacity[capacity];
    }
  }
}
