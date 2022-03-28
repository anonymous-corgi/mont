package algorithm.leetcode.p701to750;

import java.util.HashMap;
import java.util.Map;
//Facebook
public class LeetCode710RandomPickWithBlacklist {
  
  private final int actualSize;
  private final Map<Integer, Integer> reMap = new HashMap<>();
  
  public LeetCode710RandomPickWithBlacklist(int N, int[] blacklist) {
    if (blacklist != null) {
      for (int num : blacklist) {
        reMap.put(num, -1);
      }
    }
    actualSize =  N - reMap.size();
    for (int num : blacklist) {
      if (num < actualSize) {
        while (reMap.containsKey(--N)) {}
        reMap.put(num, N);
      }
    }
  }
  
  public int pick() {
    int num = (int) (actualSize * Math.random()) ;
    if (reMap.containsKey(num)) {
      return reMap.get(num);
    }
    return num;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
