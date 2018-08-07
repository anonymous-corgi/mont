package leetcode.p701to750;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode734SentenceSimilarity {
  
  public boolean isSentenceSimilarity(String[] words1, String[] words2, List<List<String>> pairs) {
    if (words1 == null || words2 == null 
        || words1.length == 0 || words2.length == 0) {
      return false;
    }
    if (words1.length != words2.length) {
      return false;
    }
    HashMap<String, Set<String>> map = new HashMap<>();
    for (int i = 0, iLen = pairs.size(); i < iLen; i++) {
      List<String> list = pairs.get(i);
      String w1 = list.get(0);
      String w2 = list.get(1);
      if (!map.containsKey(w1)) {
        map.put(w1, new HashSet<String>());
      }
      if (!map.containsKey(w2)) {
        map.put(w2, new HashSet<String>());
      }
      map.get(w1).add(w2);
      map.get(w2).add(w1);
    }
    for (int i = 0; i < words1.length; i++) {
      if (!map.get(words1[i]).contains(words2[i])) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
