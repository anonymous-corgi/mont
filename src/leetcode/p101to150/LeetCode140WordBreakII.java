package leetcode.p101to150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode140WordBreakII {
  
  private HashMap<String, List<String>> cache;
  
  public List<String> wordBreak(String s, List<String> wordDict) {
    cache = new HashMap<String, List<String>>();
    cache.put("", Arrays.asList(""));
    List<Integer> lenList = getLenList(wordDict);
    return dfs(s, lenList, new HashSet<String>(wordDict));
  }       
  
  private List<String> dfs(String s, List<Integer> lenList, Set<String> wordDict) {
    if (cache.containsKey(s)) {
      return cache.get(s);
    }
    
    List<String> res = new ArrayList<String>();
    for (Integer wLen : lenList) {
      if (wLen > s.length()) {
        break;
      }
      String head = s.substring(0, wLen);
      if (wordDict.contains(head)) {
        List<String> sublist = dfs(s.substring(wLen), lenList, wordDict);
        for (String sub : sublist) {
          res.add(head + (sub.length() == 0? "" : " " + sub));
        }
      }
    }
    cache.put(s, res);
    return res;
  }
  
  private List<Integer> getLenList(List<String> wordDict) {
    Set<Integer> set = new HashSet<>();
    for (String str : wordDict) {
      set.add(str.length());
    }
    List<Integer> list = new ArrayList<>(set);
    Collections.sort(list);
    return list;
  }

  public static void main(String[] args) {

  }

}
