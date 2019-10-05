package lintcode.p551to600;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LintCode574BuildPostOffice {

    private interface Method {
        int shortestDistance(int[][] grid);
    }

    private static final class Batch implements Method {

        public int shortestDistance(int[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            int result = Integer.MAX_VALUE;
            List<Integer> xPositions = new ArrayList<>();
            List<Integer> yPositions = new ArrayList<>();
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (grid[x][y] == 1) {
                        xPositions.add(x);
                        yPositions.add(y);
                    }
                }
            }
            Collections.sort(xPositions);
            Collections.sort(yPositions);

            final int NUM = xPositions.size();
            List<Integer> xPrefix = new ArrayList<>(NUM);
            List<Integer> yPrefix = new ArrayList<>(NUM);
            xPrefix.add(0);
            yPrefix.add(0);
            for (int i = 0; i < NUM; ++i) {
                xPrefix.add(xPrefix.get(i) + xPositions.get(i));
                yPrefix.add(yPrefix.get(i) + yPositions.get(i));
            }

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (grid[x][y] == 0) {
                        int xCost = getCost(xPositions, xPrefix, x, NUM);
                        int yCost = getCost(yPositions, yPrefix, y, NUM);
                        result = Math.max(result, xCost + yCost);
                    }
                }
            }
            return result;
        }

        private int getCost(List<Integer> positions, List<Integer> prefix, int pos, int NUM) {
            if (NUM == 0) {
                return 0;
            }
            if (positions.get(0) >= pos) {
                return prefix.get(NUM) - pos * NUM;
            } else if (positions.get(NUM - 1) <= pos) {
                return pos * NUM - prefix.get(NUM);
            }

            //Find the last position l where x.get(l) <= pos.
            int l = 0, r = NUM - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (positions.get(mid) <= pos) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            //(sum.get(N) - sum.get(l + 1) - pos * (N - l - 1)) is the distance
            //from pos to all the points in the right of pos.
            //((l + 1) * pos - sum.get(l + 1)) is the distance
            //from pos to all the points in the left of pos(including points in pos).
            return (prefix.get(NUM) - prefix.get(l + 1) - pos * (NUM - (l + 1))) +
                    ((l + 1) * pos - prefix.get(l + 1));
        }
    }

    private static Method getMethod() {
        return new Batch();
    }

    private void test(int[][] grid, int expected) {
        int actual = getMethod().shortestDistance(grid);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[][] grid = {{0, 1, 0, 0,}, {1, 0, 1, 1}, {0, 1, 0, 0}};
        test(grid, 6);
    }
}
