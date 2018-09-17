package leetcode.p251to300;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class LeetCode291WordPatternII {
  
  private String[] map;
  private Set<String> pSet;
  
  public boolean wordPatternMatch(String pattern, String str) {
    map = new String[26];
    pSet = new HashSet<>();
    return helper(pattern.toCharArray(), 0, 0, str);
  }
  
  private boolean helper(char[] pattern, int pos, int pIndex, String str) {
    if (pos == str.length()) {
      return pIndex == pattern.length;
    }
    if (pIndex == pattern.length) {
      return false;
    }

    int mapIndex = pattern[pIndex] - 'a';
    if (map[mapIndex] != null) {
      if (str.startsWith(map[mapIndex], pos)) {
        return helper(pattern, pos + map[mapIndex].length(), pIndex + 1, str);
      }
      return false;
    } else {
      for (int i = pos + 1, iLen = str.length(); i <= iLen; i++) {
        map[mapIndex] = str.substring(pos, i);
        if (pSet.contains(map[mapIndex])) {
          continue;
        }
        pSet.add(map[mapIndex]);
        if (helper(pattern, i, pIndex + 1, str)) {
          return true;
        }
        pSet.remove(map[mapIndex]);
      }
      map[mapIndex] = null;
    }
    return false;
  }
  
  
  @Test
  public void testWordPatternMatch() {
    String[] patterns = {"abab", "aaaa", "aabb", "d", "bdpbibletwuwbvh", "itwasthebestoftimes"};
    String[] strs = {"redblueredblue", "asdasdasdasd", "xyzabcxzyabc", "ef", "aaaaaaaaaaaaaaa", "ittwaastthhebesttoofttimes"};
    boolean[] res = {true, true, false, true, false, true};
    
   for (int i = 0; i < res.length; i++) {
     assertEquals(res[i], wordPatternMatch(patterns[i], strs[i]));
   }
  }
  
  public static void main(String[] args) {
    String pattern = "itwasthebestoftimes";
    String str = "ittwaastthhebesttoofttimes";
    LeetCode291WordPatternII one =
        new LeetCode291WordPatternII();
    System.out.println(one.wordPatternMatch(pattern, str));
  }
  
}
