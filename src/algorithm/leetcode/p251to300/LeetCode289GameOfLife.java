package algorithm.leetcode.p251to300;

public class LeetCode289GameOfLife {

    private interface Method {
        void gameOfLife(int[][] board);
    }

    private static class Ordinary implements Method {

        private static int[][] DIRS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        private static int[] LIVE_NEW_STATE = new int[]{0, 0, 1, 1, 0, 0, 0, 0, 0};
        private static int[] DEAD_NEW_STATE = new int[]{0, 0, 0, 1, 0, 0, 0, 0, 0};

        @Override
        public void gameOfLife(int[][] grid) {
            int[][] nextGrid = new int[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    nextGrid[i][j] = 0;
                    for (int d = 0; d < 8; d++) {
                        int x = i + DIRS[d][0];
                        int y = j + DIRS[d][1];
                        if (0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] == 1) {
                            nextGrid[i][j]++;
                        }
                    }
                    if (grid[i][j] == 1) {
                        nextGrid[i][j] = LIVE_NEW_STATE[nextGrid[i][j]];
                    } else {
                        nextGrid[i][j] = DEAD_NEW_STATE[nextGrid[i][j]];
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                System.arraycopy(nextGrid[i], 0, grid[i], 0, grid[0].length);
            }
        }
    }
}
