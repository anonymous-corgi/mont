package algorithm.leetcode.p901to950;

public class LeetCode917ReverseOnlyLetters {

  interface Method {
    String reverseOnlyLetters(String s);
  }

  public static class My_method implements Method{

    @Override
    public String reverseOnlyLetters(String s) {
      int l = 0;
      int r = s.length() - 1;
      char[] res = s.toCharArray();
      while (l < r) {
        while (l < r && !Character.isAlphabetic(res[l])) {
          l++;
        }
        while (l < r && !Character.isAlphabetic(res[r])) {
          r--;
        }
        if (l < r) {
          swap(res, l++, r--);
        }
      }
      return String.valueOf(res);
    }

    private void swap(char[] chs, int a, int b) {
      char temp = chs[a];
      chs[a] = chs[b];
      chs[b] = temp;
    }

  }

}
