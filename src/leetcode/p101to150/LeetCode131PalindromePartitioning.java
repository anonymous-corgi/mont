package leetcode.p101to150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import utils.Print;

public class LeetCode131PalindromePartitioning {
  
  public static class Manachers_method {
    
    private Map<Integer, List<List<String>>> cache;
    
    public List<List<String>> partition(String s) {
      char[] chs = s.toCharArray();
      int[] p = getP(addBoundary(chs));
      cache = new HashMap<>();
      cache.put(chs.length, Arrays.asList(Arrays.asList()));
      return dfs(chs, p, 0);
    }
    
    private List<List<String>> dfs(char[] chs, int[] p, int pos) {
      if (cache.containsKey(pos)) {
        return cache.get(pos);
      }
      List<List<String>> res = new ArrayList<>();
      for (int i = pos + 1; i <= chs.length; i++) {
        if (p[pos + i] >= i - pos) {
          for (List<String> prev : dfs(chs, p, i)) {
            List<String> neo = new LinkedList<>(prev);
            String str = String.valueOf(chs, pos, i - pos);
            neo.add(0, str);
            res.add(neo);
          }
        }
      }
      return res;
    }
    
    private int[] getP(char[] chs) {
      int[] p = new int[chs.length];
      int c = 0, r = 0;
      int f = 0, b = 0;
      for (int i = 1; i < p.length; i++) {
        if (i > r) {
          b = i + 1;
          f = i - 1;
        } else {
          int im = c * 2 - i;
          if (i + p[im] < r) {
            p[i] = p[im];
            f = -1;
          } else {
            p[i] = r - i;
            b = r + 1;
            f = i * 2 - b;
          }
        }
        
        while (f >= 0 && b < chs.length && chs[f] == chs[b]) {
          p[i]++;
          f--;
          b++;
        }
        
        if (i + p[i] > r) {
          c = i;
          r = i + p[i];
        }
      }
      return p;
    }
    
    private char[] addBoundary(char[] chs) {
      char[] res = new char[chs.length * 2 + 1];
      res[0] = '*';
      for (int i = 1; i < res.length; i += 2) {
        res[i] = chs[i / 2];
        res[i + 1] = '*';
      }
      return res;
    }
    
  }
  
  
  public static void main(String[] args) {
    String s = "aab";
    LeetCode131PalindromePartitioning.Manachers_method one =
        new LeetCode131PalindromePartitioning.Manachers_method();
    Print.printListList(one.partition(s));
  }
  
}
