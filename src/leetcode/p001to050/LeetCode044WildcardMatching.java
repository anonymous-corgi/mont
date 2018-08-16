package leetcode.p001to050;

public class LeetCode044WildcardMatching {
  
  public boolean isMatch(String s, String p) {
    if (s == null || p == null) {
      return false;
    }
    char[] ss = s.toCharArray();
    char[] ps = p.toCharArray();
    boolean[][] isMatch = new boolean[s.length() + 1][p.length() + 1];
    isMatch[0][0] = true;
    
    for (int i = 0; i < isMatch.length; i++) {
      for (int j = 1; j < isMatch[0].length; j++) {
        if (ps[j - 1] == '*') {
          //This is the situation neglecting the last two chars of p;
          isMatch[i][j] |= isMatch[i][j - 1];
          
          //This is the situation assuming '*' matches
          //the last char of the s;
          if (i > 0) {
            isMatch[i][j] |= isMatch[i - 1][j];
          }
        }
        
        //This is the situation where last chars of both s and p match.
        if (i > 0 && (ss[i - 1] == ps[j - 1] 
                          || ps[j - 1] == '?')) {
          isMatch[i][j] |= isMatch[i - 1][j - 1];
        }
      }
    }
    return isMatch[s.length()][p.length()];
  }

  public static void main(String[] args) {
    String s = "acdcd";
    String p = "a*c?b";
    LeetCode044WildcardMatching one = 
        new LeetCode044WildcardMatching();
    System.out.println(one.isMatch(s, p));
  }

}
