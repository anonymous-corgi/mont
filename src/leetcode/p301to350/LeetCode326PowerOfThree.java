package leetcode.p301to350;

public class LeetCode326PowerOfThree {
  
  public boolean isPowerOfThree(int n) {
    // 1162261467 is 3^19,  3^20 is bigger than int  
    return ( n > 0 &&  1162261467 % n == 0);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
