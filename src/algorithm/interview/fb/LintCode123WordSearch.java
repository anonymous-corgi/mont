package algorithm.interview.fb;

public class LintCode123WordSearch {
  
  //DP is used in certain direction, but this not.
  //DP is not used in a certain path solution problem.
  
  private int rows;
  private int columns;
  private final int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; 
  
  public boolean exist(char[][] board, String word) {
      // write your code here
      if (board == null || board[0] == null || word == null) {
          return false;
      }
      rows = board.length;
      columns = board[0].length;
      boolean[][] isBeen = new boolean[rows][columns];
      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
              if (word.charAt(0) == board[i][j]) {
                  isBeen[i][j] = true;
                  if (dfs(board, isBeen, i, j, word, 1)) {
                      return true;
                  }
                  isBeen[i][j] = false;
              }
          }
      }
      return false;
  }
  
  private boolean dfs(char[][] board, boolean[][] isBeen, 
                      int r, int c, String w, int p) {
      if (p == w.length()) {
          return true;
      }
      
      for (int i = 0; i < 4; i++) {
          int nR = r + dir[i][0];
          int nC = c + dir[i][1];
          if (nC >= 0 && nC < columns && nR >= 0 && nR < rows && !isBeen[nR][nC]) {
              isBeen[nR][nC] = true;
              if (board[nR][nC] == w.charAt(p) && dfs(board, isBeen, nR, nC, w, p + 1)) {
                  return true;
              }
              isBeen[nR][nC] = false;
          }
      }
      return false;
      
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
