package interview.fb.onsite;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LintCode945TaskScheduler {
  
  public int leastInterval(char[] tasks, int n) {
    // write your code here
    int[] c = new int[26];
    for(char t : tasks){
        System.out.print(t + " ");
        c[t - 'A']++;
    }
    Arrays.sort(c);
    int i = 25;
    while(i >= 0 && c[i] == c[25]) {
        i--;
    }
    
    return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);        
}
  
//  public int leastInterval(char[] tasks, int n) {
//    // write your code here
//    if (tasks == null) {
//      return 0;
//    }
//    int tLen = tasks.length;
//    if (tLen == 0) {
//      return 0;
//    }
//    int dIndex = 0;
//    int[] last = new int[26];
//    Arrays.fill(last, -(n + 1));
//    Queue<Character> queue = new LinkedList<>();
//    for (int i = 0; i < tLen; i++) {
//      queue.offer(tasks[i]);
//    }
//    while (!queue.isEmpty()) {
//      int size = queue.size();
//      for (int i = 0; i < size; i++) {
//        Character cursor = queue.poll();
//        if (dIndex - last[cursor - 'A'] > n) {
//          last[cursor - 'A'] = dIndex;
//          System.out.print(cursor + " -> ");
//          break;
//        }
//        queue.offer(cursor);
//      }
//      if (queue.size() == size) {
//        System.out.print("idle -> ");
//      }
//      dIndex++;
//    }
//    return dIndex;
//  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LintCode945TaskScheduler one = new LintCode945TaskScheduler();
    String tasks = "AAAAAABCDEFG";
    int n = 2;
    System.out.println(one.leastInterval(tasks.toCharArray(), n));
  }

}
