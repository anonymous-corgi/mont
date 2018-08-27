package interview.google;

public class SpiralMatrixNumber {
  
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
