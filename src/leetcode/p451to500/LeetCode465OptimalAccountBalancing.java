package leetcode.p451to500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode465OptimalAccountBalancing {
  
  public static class NonRecursive_mothed {
    
    public int balanceGraph(int[][] edges) {
      if (edges == null || edges.length == 0 
          || edges[0] == null || edges[0].length == 0) {
        return 0;
      }
      Map<Integer, Integer> map = new HashMap<>();
      for (int[] edge : edges) {
        map.put(edge[0], map.getOrDefault(edge[0], 0) - edge[2]);
        map.put(edge[1], map.getOrDefault(edge[1], 0) + edge[2]);
      }
      int len = 0;
      int[] account = new int[map.size()];
      for (Integer each : map.values()) {
        if (each != 0) {
          account[len++] = each;
        }
      }
      if (len == 0) {
        return 0;
      }
      int[] dp = new int[1 << len];
      Arrays.fill(dp, Integer.MAX_VALUE >> 1);
      //dp[i] (suppose the first byte of i is 00000101, then dp[i] means
      //the sum of the first and the third elements.)
      for (int i = 1; i < dp.length; i++) {
        int sum = 0;
        //count means the number of 1-bits or elements.
        int count = 0;
        for (int j = 0; j < len; j++) {
          if ((1 << j & i) != 0) {
            sum += account[j];
            count++;
          }
        }
        if (sum == 0) {
          dp[i] = count - 1;
          //Search if there two qualified subsets of i 
          //that can make a smaller result.  
          for (int j = 0; j < i; j++) {
            //((i & j)  == j) means j is the subset of i
            if (((i & j)  == j) && dp[j] + dp[i - j] < dp[i]) {
              dp[i] = dp[j] + dp[i - j];
            }
          }
        }
      }
      return dp[dp.length - 1];
    }
    
  }
  
  public static class Recursive_mothed {
    
    public int balanceGraph(int[][] edges) {
      if (edges == null || edges.length == 0 
          || edges[0] == null || edges[0].length == 0) {
        return 0;
      }
      Map<Integer, Integer> map = new HashMap<>();
      for (int[] edge : edges) {
        map.put(edge[0], map.getOrDefault(edge[0], 0) - edge[2]);
        map.put(edge[1], map.getOrDefault(edge[1], 0) + edge[2]);
      }
      int len = 0;
      int[] account = new int[map.size()];
      for (Integer each : map.values()) {
        if (each != 0) {
          account[len++] = each;
        }
      }
      return helper(account, 0, len, 0);
    }
    
    private int helper(int[] account, int start, int end, int num) {
      int res = Integer.MAX_VALUE;
      while (start < end && account[start] == 0) {
        start++;
      }
      for (int i = start + 1; i < end; i++) {
        if ((account[i] < 0 && account[start] > 0) 
                      || (account[i] > 0 && account[start] < 0)) {
          account[i] += account[start];
          res = Math.min(res, helper(account, start + 1, end, num + 1));
          account[i] -= account[start];
        }
      }
      return res == Integer.MAX_VALUE ? num : res;
    }
    
  }

  public static void main(String[] args) {
//    int[][] edges = {{0,1,10},{2,0,5}};
    int[][] edges = {{7,6,1},{4,6,59},{8,9,46},{7,5,92},{14,13,92},{2,1,93},{9,8,19},{14,13,72},{9,8,68},{12,16,4},{14,15,74},{1,3,54},{3,0,63},{5,7,24},{5,6,17},{12,14,89},{8,10,65},{2,1,91},{6,5,94},{1,3,85},{8,10,77},{15,16,40},{11,9,39},{10,9,42},{7,6,5},{9,10,74},{9,8,73},{9,8,87},{9,8,56},{12,16,32},{2,1,25},{10,11,92},{14,15,84},{5,6,22},{2,1,69},{3,2,56},{11,8,38},{3,1,3},{11,8,75},{0,1,49}};
    LeetCode465OptimalAccountBalancing.Recursive_mothed one
    = new LeetCode465OptimalAccountBalancing.Recursive_mothed();
    System.out.println(one.balanceGraph(edges));
  }

}
