package interview.fb.phoneinterview;

public class LeetCode680ValidPalindromeII {
  
  public boolean validPalindrome(String s) {
    int l = -1, r = s.length();
    while (++l < --r) 
        if (s.charAt(l) != s.charAt(r)) return isPalindromic(s, l, r+1) || isPalindromic(s, l-1, r);
    return true;
}

public boolean isPalindromic(String s, int l, int r) {
    while (++l < --r) 
        if (s.charAt(l) != s.charAt(r)) return false;
    return true;
}

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
    System.out.println(new LeetCode680ValidPalindromeII().validPalindrome(s));

  }

}
