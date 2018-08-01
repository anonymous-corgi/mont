package leetcode.p151to200;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LeetCode139WordBreak {
  
  private Set<Integer> getLen(List<String> dict) {
    Set<Integer> set = new TreeSet<>();
    for (int i = 0, len = dict.size(); i < len; i++) {
      set.add(dict.get(i).length());
    }
    return set;
  }
  
  public boolean wordBreak(String s, List<String> wordDict) {
    if (s == null || s.length() == 0) {
      return false;
    }
    if (wordDict == null || wordDict.size() == 0) {
      return false;
    }
    int len = s.length();
    boolean[] dp = new boolean[len + 1];
    dp[0] = true;
    Set<Integer> set = getLen(wordDict);
    for (int i = 1; i <= len; i++) {
      Iterator<Integer> iter = set.iterator();
      while (iter.hasNext()) {
        int wLen = (int) iter.next();
        if (wLen > i) {
          break;
        }
        String word = s.substring(i - wLen, i);
        if (wordDict.contains(word) && dp[i - wLen]) {
          dp[i] = true;
        }
      }
    }
    return dp[len];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
