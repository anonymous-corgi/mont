package algorithm.leetcode.p301to350;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode329LongestIncreasingPathInAMatrix {

    private interface Method {
        int longestIncreasingPath(int[][] matrix);
    }

    private static final class Memorization implements Method {

        private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int rows = matrix.length;
            int columns = matrix[0].length;
            int[][] cache = new int[rows][columns];
            int max = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    int length = dfs(i, j, matrix, cache);
                    max = Math.max(max, length);
                }
            }
            return max;
        }

        private int dfs(int row, int col, int[][] matrix, int[][] cache) {
            if (cache[row][col] != 0) {
                return cache[row][col];
            }
            int maxPath = 1;
            for (int[] dir : DIRS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (0 <= nextRow && nextRow < matrix.length && 0 <= nextCol && nextCol < matrix[0].length
                        && matrix[nextRow][nextCol] > matrix[row][col]) {
                    int nextPath = dfs(nextRow, nextCol, matrix, cache) + 1;
                    maxPath = Math.max(maxPath, nextPath);
                }
            }
            cache[row][col] = maxPath;
            return maxPath;
        }
    }

    private static Method getMethod() {
        return new Memorization();
    }

    private void test(int[][] matrix, int expected) {
        int actual = getMethod().longestIncreasingPath(matrix);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[][] matrix = {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}};
        test(matrix, 25);
    }
}
