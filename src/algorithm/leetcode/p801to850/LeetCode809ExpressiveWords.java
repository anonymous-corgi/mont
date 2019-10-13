package algorithm.leetcode.p801to850;

public class LeetCode809ExpressiveWords {
  
  public static class N2_method {
    
    public int expressiveWords(String S, String[] words) {
      int count = 0;
      for (String w : words) {
        int i, j; // S & w's pointers.
        for (i = 0, j = 0; i < S.length(); ++i) {
          if (j < w.length() && S.charAt(i) == w.charAt(j)) { // matches, w pointer j 1 step forward to move together with i.
            ++j;
          } else if (i > 0 && S.charAt(i - 1) == S.charAt(i)
                        && i + 1 < S.length() && S.charAt(i) == S.charAt(i + 1)) { // previous, current & next are same.
            ++i; // S pointer 1 step forward, attempt to traverse the repeated chars.
          } else if (!(i > 1 && S.charAt(i) == S.charAt(i - 1) 
                        && S.charAt(i) == S.charAt(i - 2))) { // current & previous 2 are not same. 
            break; // No match.
          }
        }
        if (i == S.length() && j == w.length()) { ++count; } // both pointers reach ends.
      }
      return count;
    }
    
  }

  public static void main(String[] args) {
    String S = "heeellooo";
    String[] words = {"hello", "hi", "helo"};
    LeetCode809ExpressiveWords.N2_method one = 
        new LeetCode809ExpressiveWords.N2_method();
    System.out.print(one.expressiveWords(S, words));
  }

}
