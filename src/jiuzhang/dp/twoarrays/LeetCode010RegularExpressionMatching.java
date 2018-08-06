package jiuzhang.dp.twoarrays;

public class LeetCode010RegularExpressionMatching {
  
  public boolean isMatch(String s, String p) {
    // write your code here
    if (s == null || p == null) {
      return false;
    }
    char[] ss = s.toCharArray();
    char[] ps = p.toCharArray();
    boolean[][] isMatch = new boolean[s.length() + 1][p.length() + 1];
    isMatch[0][0] = true;
    
    for (int i = 0; i < isMatch.length; i++) {
      for (int j = 1; j < isMatch[0].length; j++) {
        if (j > 1 && ps[j - 1] == '*') {
          isMatch[i][j] |= isMatch[i][j - 2]; 
          
          if (i > 0 && (ss[i - 1] == ps[j - 2]
              || ps[j - 2]== '.')) {
            isMatch[i][j] |= isMatch[i - 1][j];
          }
        } else if (i > 0 && (ss[i - 1] == ps[j - 1]
            || ps[j - 1] == '.')) {
          isMatch[i][j] |= isMatch[i - 1][j - 1];
        }
      }
    }
    return isMatch[s.length()][p.length()];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode010RegularExpressionMatching one = new LeetCode010RegularExpressionMatching();
    String s = "aaaa";
    String p = "aa*";
    System.out.println(one.isMatch(s, p));
  }

}
