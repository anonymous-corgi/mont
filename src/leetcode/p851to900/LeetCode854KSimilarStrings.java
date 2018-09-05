package leetcode.p851to900;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode854KSimilarStrings {
  
  public static class BackTracking_method {
    
    private List<Integer> task;
    private Map<String, Integer> cache;
    
    public int kSimilarity(String A, String B) {
      task = new ArrayList<>();
      cache = new HashMap<>();
      char[] chsA = A.toCharArray();
      char[] chsB = B.toCharArray();
      for (int i = 0; i < A.length(); i++) {
        if (chsA[i] != chsB[i]) {
          task.add(i);
        }
      }
      return dfs(0, chsA, chsB);
    }
    
    private int dfs(int pos, char[] chsA, char[] chsB) {
      String A = String.valueOf(chsA);
      if (cache.containsKey(A)) {
        return cache.get(A);
      }
      while (pos < task.size() && chsA[task.get(pos)] == chsB[task.get(pos)]) {
        pos++;
      }
      if (pos == task.size()) {
        cache.put(A, 0);
        return 0;
      }
      int head = task.get(pos);
      int min = Integer.MAX_VALUE;
      for (int i = pos + 1; i < task.size(); i++) {
        if (chsA[task.get(i)] == chsB[head]) {
          swap(chsA, task.get(i), head);
          int num = dfs(pos + 1, chsA, chsB) + 1;
          min = Math.min(min, num);
          swap(chsA, task.get(i), head);
        }
      }
      cache.put(A, min);
      return min;
    }
    
    private void swap(char[] ch, int i, int j) {
      char t = ch[i];
      ch[i] = ch[j];
      ch[j] = t;
    }
    
  }
  
  public static void main(String[] args) {
    String A = "aabc";
    String B = "abca";
    LeetCode854KSimilarStrings.BackTracking_method one =
        new LeetCode854KSimilarStrings.BackTracking_method();
    System.out.println(one.kSimilarity(A, B));
  }
  
}
