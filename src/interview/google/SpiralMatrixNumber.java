package interview.google;

public class SpiralMatrixNumber {
  
  /**
   *  第一道给个边长2^N 次方的二维矩阵 每次以田字形切割 顺序如下
   *  1   2
   *  0   3
   *  
   *  然后抽出每个小矩阵 再次切割
   *  比如N为2，边长为2^2的矩阵 就会长这样
   *  5   6    9  10
   *  4   7    8  11
   *  1   2   13  14
   *  0   3   12  15
   *  再给你一个(x, y)坐标 让你求那个点的数字是多少，
   *  比如在2^2里面 (3, 3) = 15 (3, 0) = 0. 
   *  
   *  求边长为2^N的二位矩阵里，坐标为(x, y)的点数字为多少？
   *  
   *  1 <= N <= 16
   *  0 <= x <= 2^N - 1
   *  0 <= y <= 2^N - 1
   */
  
  public long calNumber(int N, int x, int y) {
    if (N == 1) {
      return getIndex(x, y, 1);
    }
    int half = 1 << (N - 1);
    int index = getIndex(x, y, half);
    return (1 << N) * index + calNumber(N - 1, x % half, y % half); 
  }
  
  private int getIndex(int x, int y, int half) {
    return x >= half ? (y >= half ? 3 : 0) : (y >= half ? 2 : 1);
  }
  

  public static void main(String[] args) {
    int N = 2;
    int x = 3;
    int y = 3;
    SpiralMatrixNumber one = new SpiralMatrixNumber();
    System.out.println(one.calNumber(N, x, y));
  }

}
