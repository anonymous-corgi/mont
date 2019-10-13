package algorithm.interview.visa;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConsectiveSum {
  
  interface Method {
    int consecutive(long num);
  }
  
  public static class Linear_method implements Method {
    
    @Override
    public int consecutive(long num) {
      int count = 0;
      long head = 1;
      long tail = 2;
      long sum = 1;
      while (tail < num) {
        sum += tail++;
        
        while (sum >= num) {
          if (sum == num) {
            count++;
          }
          sum -= head++;
        }
      }
      return count;
    }
    
  }
  
  public static class LogN_method implements Method {

    @Override
    public int consecutive(long num) {
      int count = 0;
      long LEN = 2 * num;
      for (long k = 2; k * (k - 1) < LEN; k++) {
        if ((num - (k * (k - 1) / 2)) % k == 0) {
          count++;
        }
      }
      return count;
    }
    
  }
  
  
  private Method getInstance() {
    return new LogN_method();
//    return new Linear_method();
  }
  
  public int getConsectiveSumWays(int num) {
    return getInstance().consecutive(num);
  }
  
  
  @Test
  public void testGetConsectiveSumWays1() {
    int num = 21;
    int res = 3;
    assertEquals(res, getConsectiveSumWays(num));
  }
  
  @Test
  public void testGetConsectiveSumWays2() {
    int num = 15;
    int res = 3;
    assertEquals(res, getConsectiveSumWays(num));
  }
  
  @Test
  public void testGetConsectiveSumWays3() {
    int[] num = {3, 5, 6};
    int[] res = {1, 1, 1};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], getConsectiveSumWays(num[i]));
    }
  }
  
  public static void main(String[] args) {
    int num = 6;
    ConsectiveSum one = new ConsectiveSum();
    System.out.println(one.getConsectiveSumWays(num));
  }
  
}
