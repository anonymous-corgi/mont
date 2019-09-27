package leetcode.p551to600;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 568. Maximum Vacation Days
 * Hard
 *
 * LeetCode wants to give one of its best employees the option
 * to travel among N cities to collect algorithm problems.
 * But all work and no play makes Jack a dull boy,
 * you could take vacations in some particular cities and weeks.
 * Your job is to schedule the traveling to maximize the number of vacation days you could take,
 * but there are certain rules and restrictions you need to follow.
 *
 * Rules and restrictions:
 * You can only travel among N cities, represented by indexes from 0 to N-1.
 * Initially, you are in the city indexed 0 on Monday.
 * The cities are connected by flights.
 * The flights are represented as a N*N matrix (not necessary symmetrical),
 * called flights representing the airline status from the city i to the city j.
 * If there is no flight from the city i to the city j, flights[i][j] = 0;
 * Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
 *
 * You totally have K weeks (each week has 7 days) to travel.
 * You can only take flights at most once per day and can only take flights on each week's Monday morning.
 * Since flight time is so short, we don't consider the impact of flight time.
 * For each city, you can only have restricted vacation days in different weeks,
 * given an N*K matrix called days representing this relationship.
 * For the value of days[i][j],
 * it represents the maximum days you could take vacation in the city i in the week j.
 * You're given the flights matrix and days matrix,
 * and you need to output the maximum vacation days you could take during K weeks.
 *
 * Example 1:
 * Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
 * Output: 12
 * Explanation:
 * Ans = 6 + 3 + 3 = 12.
 *
 * One of the best strategies is:
 * 1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
 * (Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
 * 2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
 * 3rd week : stay at city 2, and play 3 days and work 4 days.
 *
 *
 * Example 2:
 * Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
 * Output: 3
 * Explanation:
 * Ans = 1 + 1 + 1 = 3.
 *
 * Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks.
 * For each week, you only have one day to play and six days to work.
 * So the maximum number of vacation days is 3.
 *
 *
 * Example 3:
 * Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
 * Output: 21
 * Explanation:
 * Ans = 7 + 7 + 7 = 21
 *
 * One of the best strategies is:
 * 1st week : stay at city 0, and play 7 days.
 * 2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
 * 3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
 *
 *
 * Note:
 * N and K are positive integers, which are in the range of [1, 100].
 * In the matrix flights, all the values are integers in the range of [0, 1].
 * In the matrix days, all the values are integers in the range [0, 7].
 * You could stay at a city beyond the number of vacation days,
 * but you should work on the extra days, which won't be counted as vacation days.
 * If you fly from the city A to the city B and take the vacation on that day,
 * the deduction towards vacation days will count towards the vacation days of city B in that week.
 * We don't consider the impact of flight hours towards the calculation of vacation days.
 */
@RunWith(Parameterized.class)
public class LeetCode568MaximumVacationDays {
    private final int[][] flights;
    private final int[][] days;
    private final int expected;

    public LeetCode568MaximumVacationDays(int[][] flights, int[][] days, int expected) {
        this.flights = flights;
        this.days = days;
        this.expected = expected;
    }

    private interface Method {
        int maxVacationDays(int[][] flights, int[][] days);
    }

    private static final class DP implements Method {

        public int maxVacationDays(int[][] flights, int[][] days) {
            final int weeks = days[0].length;
            final int cities = flights.length;
            int maxDay = 0;
            int[] preMax = new int[cities];
            int[] curMax = new int[cities];
            Arrays.fill(curMax, -1);
            curMax[0] = 0;
            for (int week = 0; week < weeks; week++) {
                System.arraycopy(curMax, 0, preMax, 0, curMax.length);
                for (int orgCity = 0; orgCity < cities; orgCity++) {
                    if (preMax[orgCity] < 0) {
                        continue;
                    }
                    // If stay in the same city. As flights[i][i] == 0.
                    curMax[orgCity] = Math.max(curMax[orgCity], preMax[orgCity] + days[orgCity][week]);
                    for (int dstCity = 0; dstCity < cities; dstCity++) {
                        if (flights[orgCity][dstCity] == 0) {
                            continue;
                        }
                        curMax[dstCity] = Math.max(curMax[dstCity], preMax[orgCity] + days[dstCity][week]);
                    }
                }
            }
            for (int max : curMax) {
                maxDay = Math.max(maxDay, max);
            }
            return maxDay;
        }
    }

    private static final class DP_Pro implements Method {

        public int maxVacationDays(int[][] flights, int[][] days) {
            int weeks = days[0].length;
            int cities = flights.length;
            int[] preMax = new int[cities];
            int[] curMax = new int[cities];
            for (int city = 0; city < cities; city++) {
                curMax[city] = days[city][weeks - 1];
            }
            for (int week = weeks - 2; week >= 0; week--) {
                int[] temp = preMax;
                preMax = curMax;
                curMax = temp;
                for (int orgCity = 0; orgCity < cities; orgCity++) {
                    curMax[orgCity] = days[orgCity][week] + getMaxDays(preMax, orgCity, flights);
                }
            }
            return getMaxDays(curMax, 0, flights);
        }

        private int getMaxDays(int[] dp, int orgCity, int[][] flights) {
            // If stay in the same city.
            int max = dp[orgCity];
            for (int dstCity = 0; dstCity < flights.length; dstCity++) {
                if (flights[orgCity][dstCity] == 1) {
                    max = Math.max(max, dp[dstCity]);
                }
            }
            return max;
        }
    }

    private static Method getMethod() {
        return new DP();
    }

    private void test(int[][] flights, int[][] days, int expected) {
        int actual = getMethod().maxVacationDays(flights, days);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase() {
        test(flights, days, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}, new int[][]{{1, 3, 1}, {6, 0, 3}, {3, 3, 3}}, 12},
                {new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][]{{1, 1, 1}, {7, 7, 7}, {7, 7, 7}}, 3},
                {new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}, new int[][]{{7, 0, 0}, {0, 7, 0}, {0, 0, 7}}, 21},
        };
    }
}