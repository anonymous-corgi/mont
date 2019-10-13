package algorithm.leetcode.p851to900;

import java.util.ArrayList;
import java.util.List;

public class LeetCode890FindAndReplacePattern {
  
  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> res = new ArrayList<>();
    char[] p = pattern.toCharArray();
    for (String word : words) {
      if (isMatch(word.toCharArray(), p)) {
        res.add(word);
      }
    }
    return res;
  }
  
  private boolean isMatch(char[] str, char[] pattern) {
    char[] sp = new char[26];
    char[] ps = new char[26];
    for (int i = 0; i < pattern.length; i++) {
      int vs = str[i] - 'a';
      int vp = pattern[i] - 'a';
      if (sp[vs] == 0) {
        if (ps[vp] == 0) {
          sp[vs] = pattern[i];
          ps[vp] = str[i];
        } else {
          return false;
        }
      } else if (sp[vs] != pattern[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

  }

}
