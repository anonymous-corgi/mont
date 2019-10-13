package algorithm.leetcode.p301to350;

public class LeetCode308RangeSumQuery2DMutable {

    private static abstract class NumMatrix {

        abstract public void update(int row, int col, int val);

        abstract public int sumRegion(int row1, int col1, int row2, int col2);
    }

    private static final class BinaryIndexedTree extends NumMatrix {
        private final int[][] stub;
        private final int[][] bit;

        public BinaryIndexedTree(int[][] matrix) {
            int rows = matrix.length + 1;
            int cols = rows == 1 ? 1 : matrix[0].length + 1;
            stub = new int[rows][cols];
            bit = new int[rows][cols];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            int diff = val - stub[row + 1][col + 1];
            stub[row + 1][col + 1] = val;
            for (int i = row + 1; i < bit.length; i += (i & -i)) {
                for (int j = col + 1; j < bit[i].length; j += (j & -j)) {
                    bit[i][j] += diff;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return getSum(row2, col2) - getSum(row2, col1 - 1) -
                    getSum(row1 - 1, col2) + getSum(row1 - 1, col1 - 1);
        }

        private int getSum(int row, int col) {
            int sum = 0;
            for (int i = row + 1; i > 0; i -= (i & -i)) {
                for (int j = col + 1; j > 0; j -= (j & -j)) {
                    sum += bit[i][j];
                }
            }
            return sum;
        }
    }
}
