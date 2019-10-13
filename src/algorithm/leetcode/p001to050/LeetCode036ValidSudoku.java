package algorithm.leetcode.p001to050;

public class LeetCode036ValidSudoku {
  
  private int[] rows;    
  private int[] cols;
  private int[] grid;
  
  public boolean isValidSudoku(char[][] board) {
    rows = new int[9];
    cols = new int[9];
    grid = new int[9];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != '.') {
          final int FLAG = 1 << (board[i][j] - '1');
          if (!addAll(i, j, FLAG)) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  private boolean addAll(int i, int j, int FLAG) {
    int gridPos = i / 3 * 3 + j / 3;
    if ((rows[i] & FLAG) == 0 && (cols[j] & FLAG) == 0 
        && (grid[gridPos] & FLAG) == 0) {
      rows[i] ^= FLAG;
      cols[j] ^= FLAG;
      grid[gridPos] ^= FLAG;
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    LeetCode036ValidSudoku one = 
        new LeetCode036ValidSudoku();
    char[][] board = {{'8','3','.','.','7','.','.','.','.'},
                      {'6','.','.','1','9','5','.','.','.'},
                      {'.','9','8','.','.','.','.','6','.'},
                      {'8','.','.','.','6','.','.','.','3'},
                      {'4','.','.','8','.','3','.','.','1'},
                      {'7','.','.','.','2','.','.','.','6'},
                      {'.','6','.','.','.','.','2','8','.'},
                      {'.','.','.','4','1','9','.','.','5'},
                      {'.','.','.','.','8','.','.','7','9'}};
    System.out.println(one.isValidSudoku(board));
  }

}
