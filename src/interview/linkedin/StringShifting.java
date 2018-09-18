package interview.linkedin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringShifting {
  
  public String shiftString(String str, int R, int L) {
    int len = str.length();
    int shift = (R - L) % len;
    String res = str;
    if (shift < 0) {
      shift += len;
    }
    if (shift != 0) {
      res = shiftRight(str.toCharArray(), shift);
    }
    return res;
  }
  
  private String shiftRight(char[] chs, int n) {
    swap(chs, 0, chs.length - 1);
    swap(chs, 0, n - 1);
    swap(chs, n, chs.length - 1);
    return String.valueOf(chs);
  }
  
  private void swap(char[] chs, int a, int b) {
    while (a < b) {
      char temp = chs[a];
      chs[a++] = chs[b];
      chs[b--] = temp;
    }
  }
  
  @Test
  public void testShiftString1() {
    String str = "abcd";
    int R = 2;
    int L = 1;
    String res = "dabc";
    assertEquals(res, shiftString(str, R, L));
  }
  
  @Test
  public void testShiftString2() {
    String str = "abcd";
    int R = 10;
    int L = 1;
    String res = "dabc";
    assertEquals(res, shiftString(str, R, L));
  }  
  
  @Test
  public void testShiftString3() {
    String str = "abcd";
    int R = 2;
    int L = 9;
    String res = "dabc";
    assertEquals(res, shiftString(str, R, L));
  }
  
  public static void main(String[] args) {
    String str = "abcd";
    int R = 2;
    int L = 1;
    StringShifting one = new StringShifting();
    System.out.println(one.shiftString(str, R, L));
  }

}
