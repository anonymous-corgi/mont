package algorithm.leetcode.p351to400;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 361. Bomb Enemy
 * Medium
 * <p>
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
 * return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point
 * until it hits the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 * <p>
 * Example:
 * Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 * Explanation: For the given grid,
 * <p>
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * <p>
 * Placing a bomb at (1,1) kills 3 enemies.
 */
public class LeetCode361BombEnemy {

    private interface Method {
        int maxKilledEnemies(char[][] grid);
    }

    private static final class Impl implements Method {

        public int maxKilledEnemies(char[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int max = 0;
            int[][] killOneDir = new int[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                int sum = 0;
                for (int j = 0; j < grid[0].length; j++) {
                    // The order of updating sum and updating killOneDir matters!!!!!!
                    killOneDir[i][j] += grid[i][j] == '0' ? sum : 0;
                    sum = grid[i][j] == 'E' ? sum + 1 : (grid[i][j] == 'W' ? 0 : sum);
                }
                sum = 0;
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    killOneDir[i][j] += grid[i][j] == '0' ? sum : 0;
                    sum = grid[i][j] == 'E' ? sum + 1 : (grid[i][j] == 'W' ? 0 : sum);
                }
            }
            for (int j = 0; j < grid[0].length; j++) {
                int sum = 0;
                for (int i = 0; i < grid.length; i++) {
                    killOneDir[i][j] += grid[i][j] == '0' ? sum : 0;
                    sum = grid[i][j] == 'E' ? sum + 1 : (grid[i][j] == 'W' ? 0 : sum);
                }
                sum = 0;
                for (int i = grid.length - 1; i >= 0; i--) {
                    sum = grid[i][j] == 'E' ? sum + 1 : (grid[i][j] == 'W' ? 0 : sum);
                    killOneDir[i][j] += grid[i][j] == '0' ? sum : 0;
                    max = Math.max(max, killOneDir[i][j]);
                }
            }
            return max;
        }
    }

    private static Method getMethod() {
        return new Impl();
    }

    private void test(char[][] grid, int expected) {
        int actual = getMethod().maxKilledEnemies(grid);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        char[][] grid = new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
        test(grid, 3);
    }
}