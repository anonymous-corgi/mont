package algorithm.leetcode.p751to800;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 774. Minimize Max Distance to Gas Station
 * Hard
 *
 * On a horizontal number line, we have gas stations
 * at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations,
 * is minimized.
 *
 * Return the smallest possible value of D.
 *
 * Example:
 *
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 *
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
@RunWith(Parameterized.class)
public class LeetCode774MinimizeMaxDistanceToGasStation {
    private final int[] stations;
    private final int k;
    private final double expected;

    public LeetCode774MinimizeMaxDistanceToGasStation(int[] stations, int k, double expected) {
        this.stations = stations;
        this.k = k;
        this.expected = expected;
    }

    private interface Method {
        double minmaxGasDist(int[] stations, int k);
    }

    private static final class BinaryTrial implements Method {

        public double minmaxGasDist(int[] stations, int k) {
            if (stations == null || stations.length == 0) {
                return 0d;
            }
            double begin = (double) (stations[stations.length - 1] - stations[0])
                    / (k + stations.length - 1);
            double end = 0d;
            double[] gaps = new double[stations.length - 1];
            for (int i = 0, iLen = stations.length - 1; i < iLen; i++) {
                gaps[i] = stations[i + 1] - stations[i];
                end = Math.max(end, gaps[i]);
            }

            while (end - begin > 1e-6) {
                double mid = (begin + end) / 2;
                if (canSplit(gaps, mid, k)) {
                    end = mid;
                } else {
                    begin = mid;
                }
            }
            return end;
        }

        private boolean canSplit(double[] gaps, double maxGap, int maxStations) {
            int count = 0;
            for (double gap : gaps) {
                count += gap / maxGap;
                if (count > maxStations) {
                    return false;
                }
            }
            return true;
        }
    }

    private static Method getMethod() {
        return new BinaryTrial();
    }

    private void test(int[] stations, int k, double expected) {
        double actual = getMethod().minmaxGasDist(stations, k);
        assertThat(true, is(Math.abs(actual - expected) < 1e-6));
    }

    @Test
    public void testcase() {
        test(stations, k, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9, 0.500000d},
                {new int[]{3, 6, 12, 19, 33, 44, 67, 72, 89, 95}, 2, 14.00000d},
        };
    }
}
