package algorithm.leetcode.p001to050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode037SudokuSolver {

    private interface Method {
        void solveSudoku(char[][] board);
    }

    private static final class DFS implements Method {
        private int[] rows;
        private int[] cols;
        private int[] grid;

        public void solveSudoku(char[][] board) {
            rows = new int[9];
            cols = new int[9];
            grid = new int[9];
            List<int[]> task = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != '.') {
                        int FLAG = 1 << (board[i][j] - '1');
                        mark(i, j , i / 3 * 3 + j / 3, FLAG);
                    } else {
                        task.add(new int[]{i, j, i / 3 * 3 + j / 3});
                    }
                }
            }
            dfs(task, 0, board);
        }

        private boolean dfs(List<int[]> task, int pos, char[][] board) {
            if (pos == task.size()) {
                return true;
            }
            int[] p = task.get(pos);
            for (int i = 0; i < 9; i++) {
                final int FLAG = 1 << i;
                if ((rows[p[0]] & FLAG) != 0 || (cols[p[1]] & FLAG) != 0 || (grid[p[2]] & FLAG) != 0) {
                    continue;
                }
                mark(p[0], p[1], p[2], FLAG);
                if (dfs(task, pos + 1, board)) {
                    board[p[0]][p[1]] = (char) (i + '1');
                    return true;
                }
                mark(p[0], p[1], p[2], FLAG);
            }
            return false;
        }

        private void mark(int i, int j, int g, int FLAG) {
            rows[i] ^= FLAG;
            cols[j] ^= FLAG;
            grid[g] ^= FLAG;
        }
    }

    public static void main(String[] args) {
        Method one = new DFS();
        char[][] board =   {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        one.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}
