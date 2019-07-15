package jiuzhang.dp.backpack;

/**
 * LintCode 564. Combination Sum IV
 * LeetCode 377. Combination Sum IV Description Given an integer
 * <p>
 * array nums with all positive numbers and no duplicates, find the number of possible combinations
 * that add up to a positive integer target.
 * <p>
 * Example Given nums = [1, 2, 4], target = 4 The possible combination ways are:
 * [1, 1, 1, 1]
 * [1, 1, 2]
 * [1, 2, 1]
 * [2, 1, 1]
 * [2, 2]
 * [4]
 * return 6
 */
public class BackPackVI {

    //Count How many ways can get to target with repeated use. Permutation of coins matters.
    private interface Method {
        int backPackVI(int[] weights, int capacity);
    }

    private class Method1 implements Method {

        @Override
        public int backPackVI(int[] weights, int capacity) {
            if (weights == null || weights.length == 0) {
                return 0;
            }
            int[] dp = new int[capacity + 1];
            dp[0] = 1;
            for (int i = 1; i <= capacity; i++) {
                //j starts from 0, because it contains duplication.
                for (int weight : weights) {
                    if (weight <= i) {
                        dp[i] += dp[i - weight];
                    }
                }
            }
            return dp[capacity];
        }
    }
}
