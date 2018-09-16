package leetcode.p901to950;

public class LeetCode906SuperPalindromes {
  
  public int superpalindromesInRange(String L, String R) {
    //long is able to store 19 digits, but accuracy is 18 digits.
    long l = Long.parseLong(L);
    long r = Long.parseLong(R);
    int[] res = new int[1];
    //Don't forget to generate even digits palindromes
    dfs("", l, r, res);
    //Generate odd digits palindromes
    for (int i = 0; i < 10; i++) {
      dfs("" + i, l, r, res);
    }
    return res[0];
  }
  
  private void dfs(String s, long l, long r, int[] res) {
    //If s.length() > 9, super-palindrome's length must larger than 18
    if (s.length() > 9) {
      return;
    }
    //Verify if s is within the required boundary.
    if (s.length() > 0 && s.charAt(0) != 0) {
      long num = Long.parseLong(s);
      num *= num;
      //Prune the unqualified palindromes branch
      if (num > r) { return; }
      if (num >= l && isPalindrome("" + num)) {
        res[0]++;
      }
    }
    //Generate the next palindromes
    for (int i = 0; i < 10; i++) {
      String next = "" + i + s + i;
      dfs(next, l, r, res);
    }
  }
  
  private boolean isPalindrome(String s) {
    int head = 0;
    int tail = s.length() - 1;
    while (head < tail) {
      if (s.charAt(head++) != s.charAt(tail--)) {
        return false;
      }
    }
    return true;
  }

}
