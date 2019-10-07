package leetcode.p851to900;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode871MinimumNumberOfRefuelingStops {
    private final int target;
    private final int startFuel;
    private final int[][] stations;
    private final int expected;

    public LeetCode871MinimumNumberOfRefuelingStops(int target, int startFuel, int[][] stations, int expected) {
        this.target = target;
        this.startFuel = startFuel;
        this.stations = stations;
        this.expected = expected;
    }

    private interface Method {
        int minRefuelStops(int target, int startFuel, int[][] stations);
    }

    private static final class DP implements Method {

        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if (startFuel >= target) {
                return 0;
            } else if (stations.length == 0) {
                return -1;
            }
            int len = stations.length + 1;
            int[][] dp = new int[len][len];
            Arrays.fill(dp[0], startFuel);
            for (int stop = 1; stop < len; stop++) {
                for (int sIndex = stop; sIndex < len; sIndex++) {
                    int dis = stations[sIndex - 1][0];
                    int fuel = stations[sIndex - 1][1];
                    if (sIndex > stop) {
                        dp[stop][sIndex] = dp[stop][sIndex - 1];
                    }
                    if (dp[stop - 1][sIndex - 1] >= dis) {
                        dp[stop][sIndex] = Math.max(dp[stop][sIndex], dp[stop - 1][sIndex - 1] + fuel);
                    }
                    if (dp[stop][sIndex] >= target) {
                        return stop;
                    }
                }
            }
            return -1;
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    private void test() {
        int actual = getMethod().minRefuelStops(target, startFuel, stations);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase() {
        test();
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {1000, 83, new int[][]{{25, 27}, {36, 187}, {140, 186}, {378, 6}, {492, 202}, {517, 89}, {579, 234}, {673, 86}, {808, 53}, {954, 49}}, -1},
                {100, 25, new int[][]{{25, 25}, {50, 25}, {75, 25}}, 3},
                {100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}, 2},
        };
    }
}