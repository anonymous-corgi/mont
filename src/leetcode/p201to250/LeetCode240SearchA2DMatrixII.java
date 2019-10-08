package leetcode.p201to250;

public class LeetCode240SearchA2DMatrixII {

    private interface Method {
        boolean searchMatrix(int[][] matrix, int target);
    }

    // O(n + m)
    private static final class Greedy implements Method {

        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int col = 0;
            int row = matrix.length - 1;
            while (col < matrix[0].length && row >= 0) {
                if (matrix[row][col] < target) {
                    col++;
                } else if (matrix[row][col] > target) {
                    row--;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    // O(logn + logm)
    private static final class BinarySearch implements Method {

        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            return searchMatrix(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
        }

        private boolean searchMatrix(int[][] matrix, int sRow, int sCol, int eRow, int eCol, int target) {
            if (sRow > eRow || sCol > eCol) {
                return false;
            }
            if (target < matrix[sRow][sCol] || matrix[eRow][eCol] < target) {
                return false;
            }
            if (sRow == eRow && sCol == eCol) {
                return matrix[sRow][sCol] == target;
            }
            int midRow = sRow + (eRow - sRow) / 2;
            int midCol = sCol + (eCol - sCol) / 2;
            boolean hasValue = false;
            if (matrix[midRow][midCol] < target) {
                if (searchMatrix(matrix, midRow + 1, midCol + 1, eRow, eCol, target)) return true;
                if (searchMatrix(matrix, midRow + 1, sCol, eRow, midCol, target)) return true;
                if (searchMatrix(matrix, sRow, midCol + 1, midRow, eCol, target)) return true;
            } else if (matrix[midRow][midCol] > target) {
                if (searchMatrix(matrix, sRow, sCol, midRow - 1, midCol - 1, target)) return true;
                if (searchMatrix(matrix, midRow, sCol, eRow, midCol - 1, target)) return true;
                if (searchMatrix(matrix, sRow, midCol, midRow - 1, eCol, target)) return true;
            } else {
                return true;
            }
            return false;
        }
    }
}