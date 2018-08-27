package leetcode.p101to150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode139WordBreak {
  
  private List<Integer> getLen(List<String> dict) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0, len = dict.size(); i < len; i++) {
      set.add(dict.get(i).length());
    }
    List<Integer> list = new ArrayList<>(set);
    Collections.sort(list);
    return list;
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
    List<Integer> lenList = getLen(wordDict);
    for (int i = 1; i <= len; i++) {
      for (Integer wLen : lenList) {
        if (wLen > i) { break; }
        if (!dp[i - wLen]) { continue; }
        String word = s.substring(i - wLen, i);
        dp[i] |= wordDict.contains(word);
      }
    }
    return dp[len];
  }

  public static void main(String[] args) {
    //Expected Result: true.
    String s = "abcd";
    List<String> wordDict = Arrays.asList("a","abc","b","cd");

////    Expected Result: true.
//    String s = "applepenapple";
//    List<String> wordDict = Arrays.asList("apple","pen");
        LeetCode139WordBreak one = new LeetCode139WordBreak();
   System.out.println(one.wordBreak(s, wordDict));
  }

}
