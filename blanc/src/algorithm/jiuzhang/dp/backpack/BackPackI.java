package algorithm.jiuzhang.dp.backpack;

/**
 * LintCode 92 BackPack
 * https://www.lintcode.com/problem/backpack/description
 * <p>
 * Description
 * Given n items with size Ai, an integer m denotes the size of a backpack.
 * How full you can fill this backpack?
 * <p>
 * Example
 * If we have 4 items with size [2, 3, 5, 7], the backpack size is 11,
 * we can select [2, 3, 5], so that the max size we can fill this backpack is 10.
 * If the backpack size is 12. we can select [2, 3, 7]
 * so that we can fulfill the backpack.
 * <p>
 * You function should return the max size we can fill in the given backpack.
 */
public class BackPackI {

    private interface Method {
        int backPack(int capacity, int[] weights);
    }

    //Count Max weight can be stored in m capacity without repeated use
    //This is the optimal way to count the maximum capacity
    private static class Method1 implements Method {

        @Override
        public int backPack(int capacity, int[] weights) {
            if (weights == null || weights.length == 0) {
                return 0;
            }
            int[] dp = new int[capacity + 1];
            for (int weight : weights) {
                for (int i = capacity; i >= weight; i--) {
                    dp[i] = Math.max(dp[i], dp[i - weight] + weight);
                }
            }
            return dp[capacity];
        }
    }

    //This solution is mostly used for count number of possibilities.
    //Similar to BackPackVI
    private static class Method2 implements Method {

        @Override
        public int backPack(int capacity, int[] weights) {
            if (weights == null || weights.length == 0) {
                return 0;
            }
            boolean[] dp = new boolean[capacity + 1];
            dp[0] = true;
            for (int weight : weights) {
                for (int i = capacity; i >= weight; i--) {
                    dp[i] |= dp[i - weight];
                }
            }

            for (int i = capacity; i > 0; i--) {
                if (dp[i]) {
                    return i;
                }
            }
            return 0;
        }
    }
}
