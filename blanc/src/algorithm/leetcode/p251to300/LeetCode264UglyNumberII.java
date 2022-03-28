package algorithm.leetcode.p251to300;

public class LeetCode264UglyNumberII {
  
  public int nthUglyNumber(int n) {
    int[] record = new int[n];
    record[0] = 1;
    int index = 0;
    int i2 = 0, i3 = 0, i5 = 0;
    int s2 = 2, s3 = 3, s5 = 5;
    while (index < n - 1) {
      while (s2 <= record[index]) {
        s2 = record[++i2] * 2;
      }
      while (s3 <= record[index]) {
        s3 = record[++i3] * 3;
      }
      while (s5 <= record[index]) {
        s5 = record[++i5] * 5;
      }
      int min = Math.min(s2, Math.min(s3, s5));
      record[++index] = min;
    }
    return record[n - 1];
  }

  public static void main(String[] args) {
    LeetCode264UglyNumberII one = 
        new LeetCode264UglyNumberII();
    int n = 10;
    System.out.println(one.nthUglyNumber(n));

  }

}
