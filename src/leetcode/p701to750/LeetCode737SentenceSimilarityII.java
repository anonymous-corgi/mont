package leetcode.p701to750;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode737SentenceSimilarityII {
  
  private Map<String, String> ufp;
  public boolean isSentenceSimilarity(String[] words1, String[] words2, List<List<String>> pairs) {
    if (words1 == null || words2 == null 
        || words1.length == 0 || words2.length == 0) {
      return false;
    }
    if (words1.length != words2.length) {
      return false;
    }
    ufp = new HashMap<>();
    for (int i = 0, iLen = pairs.size(); i < iLen; i++) {
      List<String> list = pairs.get(i);
      connect(list.get(0), list.get(1));
    }
    
    for (int i = 0; i < words1.length; i++) {
      if (!isConnected(words1[i], words2[i])) {
        return false;
      }
    }
    
    return true;
  }
  
  private String find(String str) {
    String strp = ufp.get(str);
    if (str.equals(strp)) {
      return str;
    }
    String strpp = find(strp);
    ufp.put(str, strpp);
    return strpp;
  }
  
  private void connect(String a, String b) {
    String ap = find(a);
    String bp = find(b);
    if (!ap.equals(bp)) {
      ufp.put(ap, bp);
    }
  }
  
  private boolean isConnected(String a, String b) {
    String ap = find(a);
    String bp = find(b);
    return ap.equals(bp);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
