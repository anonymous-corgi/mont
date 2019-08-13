package leetcode.p101to150;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeetCode135Candy {

    private interface Method {
        int candy(int[] ratings);
    }

    private static class DP implements Method {

        @Override
        public int candy(int[] ratings) {
            if (ratings == null || ratings.length == 0) {
                return 0;
            }
            int res = 0;
            int[] dp = new int[ratings.length];
            dp[0] = 1;
            for (int i = 1; i < ratings.length; i++) {
                dp[i] = ratings[i] > ratings[i - 1] ? dp[i - 1] + 1 : 1;
            }
            for (int i = ratings.length - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    dp[i] = Math.max(dp[i], dp[i + 1] + 1);
                }
            }
            for (int num : dp) {
                res += num;
            }
            return res;
        }
    }

    private Method getMethod() {
        return new DP();
    }

    @Test
    public void testCandy1() {
        Method method = getMethod();
        int[] ratings = new int[]{1, 2, 3, 3, 3, 1};
        assertEquals(10, method.candy(ratings));
    }

    @Test
    public void testCandy2() {
        Method method = getMethod();
        int[] ratings = new int[]{1, 0, 2};
        assertEquals(5, method.candy(ratings));
    }

    @Test
    public void testCandy3() {
        Method method = getMethod();
        int[] ratings = new int[]{1, 2, 2};
        assertEquals(4, method.candy(ratings));
    }
}
