package algorithm.leetcode.p051to100;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode074SearchA2DMatrix {

    private interface Method {
        boolean searchMatrix(int[][] matrix, int target);
    }

    private static final class BinarySearch implements Method {

        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            final int ROWS = matrix.length;
            final int COLS = matrix[0].length;
            int start = 0;
            int end = ROWS * COLS - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                int row = mid / COLS;
                int col = mid % COLS;
                if (matrix[row][col] < target) {
                    start = mid + 1;
                } else if (matrix[row][col] > target) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
            return matrix[start / COLS][start % COLS] == target;
        }
    }

    private static Method getMethod() {
        return new BinarySearch();
    }

    private void test() {
//        actual = getMethod()
//        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        test();
    }
}