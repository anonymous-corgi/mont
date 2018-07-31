package jiuzhang.dp.memerization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LeetCode140WordBreakII {
  
  public List<String> wordBreak(String s, Set<String> wordDict) {
    return dfs(s, wordDict, new HashMap<String, LinkedList<String>>());
  }       
  
// DFS function returns an array including all substrings derived from s.
  List<String> dfs(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
    if (map.containsKey(s)) {
      return map.get(s);
    }
    
    LinkedList<String>res = new LinkedList<String>();     
    if (s.length() == 0) {
      res.add("");
      return res;
    }               
    for (String word : wordDict) {
      if (s.startsWith(word)) {
        List<String>sublist = dfs(s.substring(word.length()), wordDict, map);
        for (String sub : sublist) 
          res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
      }
    }       
    map.put(s, res);
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
