package algorithm.interview.google;

public class MagicSquare {
  
  private static int[][] DIRS = {{-1, 1}, {1, -2}};
  public int[][] generateMagicSquare(int n) {
    if (n < 1) {
      return new int[0][0];
    }
    int[][] res = new int[n][n];
    int index = 1;
    int row = n / 2;
    int column = n - 1;
    int total = n * n;
    while (index <= total) {
      if (res[row][column] != 0) {
        row = nextValid(row, DIRS[1][0], n);
        column = nextValid(column, DIRS[1][1], n);
      }
      res[row][column] = index++;        
      row = nextValid(row, DIRS[0][0], n);
      column = nextValid(column, DIRS[0][1], n);
    }
    
    return res;
  }
  
  private int nextValid(int num, int d, int n) {
    num += d;
    if (num >= n) {
      num %= n;
    }
    if (num < 0) {
      num += n;
    }
    return num;
  }

  public static void main(String[] args) {
    MagicSquare one = new  MagicSquare();
    int n = 4;
    int[][] res = one.generateMagicSquare(n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(res[i][j] + "\t");
      }
      System.out.println();
    }
  }
  
}
