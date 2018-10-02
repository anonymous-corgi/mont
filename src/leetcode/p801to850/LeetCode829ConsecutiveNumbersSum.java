package leetcode.p801to850;

public class LeetCode829ConsecutiveNumbersSum {
  
  //if x, x + 1, x + 2, ..., x + k - 1 = N
  //=> k * x + k * (k - 1) / 2 = N;
  //That means there are k consecutive numbers and the first number is x.
  public int consecutiveNumbersSum(int N) {
    int count = 1;
    int LEN = (int) Math.sqrt(2 * N);
    for (int k = 2; k <= LEN; k++) {
      if ((N - (k * (k - 1) / 2)) % k == 0) count++;
    }
    
    return count;
  }

}
