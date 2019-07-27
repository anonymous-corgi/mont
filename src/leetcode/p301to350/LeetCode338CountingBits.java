package leetcode.p301to350;

class LeetCode338CountingBits {

    private interface Method {
        int[] countBits(int num);
    }

    private static class MoveBit_method implements Method {

        @Override
        public int[] countBits(int num) {
            int[] dp = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                dp[i] = dp[i >> 1] + (i & 1);
            }
            return dp;
        }
    }

    private static class BitOP_method implements Method {

        @Override
        public int[] countBits(int num) {
            int[] dp = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                dp[i] = dp[i & (i - 1)] + 1;
            }
            return dp;
        }
    }
}
