package algorithm.leetcode.p151to200;

public class LeetCode200NumberOfIslands {

    private interface Method {
        int numIslands(char[][] grid);
    }

    private static class UnionFind implements Method {

        @Override
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
                return 0;
            }

            int rows = grid.length;
            int cols = grid[0].length;
            int size = rows * cols;
            int cursor = -1;
            int count = 0;
            int[] ufp = new int[size];
            for (int i = 0; i < size; i++) {
                ufp[i] = i;
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    cursor++;
                    if (grid[i][j] == '0') {
                        continue;
                    }
                    count++;
                    if (i + 1 < rows && grid[i + 1][j] == '1'
                            && connect(ufp, cursor, cursor + cols)) {
                        count--;
                    }
                    if (j + 1 < cols && grid[i][j + 1] == '1'
                            && connect(ufp, cursor, cursor + 1)) {
                        count--;
                    }
                }
            }
            return count;
        }

        private int find(int[] ufp, int num) {
            return ufp[num] == num ? num : (ufp[num] = find(ufp, ufp[num]));
        }

        private boolean connect(int[] ufp, int to, int from) {
            int rootTo = find(ufp, to);
            int rootFrom = find(ufp, from);
            ufp[rootFrom] = ufp[rootTo];
            return rootTo == rootFrom;
        }
    }
}
