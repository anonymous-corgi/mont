package algorithm.leetcode.p351to400;

public class LeetCode365WaterAndJugProblem {
  
  public boolean canMeasureWater(int x, int y, int z) {
    return z == 0 || (z <= x + y && z % gcd(x, y) == 0);
  }
  
  private int gcd(int x, int y) {
    return y == 0 ? x : gcd(y, x % y);
  }

  public static void main(String[] args) {

  }

}
