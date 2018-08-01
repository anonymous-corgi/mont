package leetcode.p601to650;

import java.util.Arrays;

public class LeetCode621TaskScheduler {
  
  public int leastInterval(char[] tasks, int n) {
    if (tasks == null || tasks.length == 0) {
      return 0;
    }
    int[] cs = new int[26];
    for (int i = 0; i < tasks.length; i++) {
      cs[tasks[i] - 'A']++;
    }
    Arrays.sort(cs);
    int i = 1;
    while (i < 26 && cs[25 - i] == cs[25]) {
      i++;
    }
    return Math.max(tasks.length, (n + 1) * (cs[25] - 1) + i);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
