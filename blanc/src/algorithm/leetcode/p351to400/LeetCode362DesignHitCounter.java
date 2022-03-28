package algorithm.leetcode.p351to400;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode362DesignHitCounter {
  
  public LeetCode362DesignHitCounter() {
    
  }
//  Queue method:
  public static class Queue_Method {
    
    private final Queue<Integer> record = new LinkedList<>();
    
    public void hit(int time) {
      while (!record.isEmpty() && record.peek() <= time - 300) {
        record.poll();
      }
      record.offer(time);
    }
    
    public int getHits(int time) {
      while (!record.isEmpty() && record.peek() <= time - 300) {
        record.poll();
      }
      return record.size();
    }
    
  }

//  Circular Array method:
  public static class Circular_Array_Method {
    
    private final int SPAN = 300;
    private final int[] timeStamp = new int[SPAN];
    private final int[] hits = new int[SPAN];
    
    public void hit(int time) {
      int index = time % SPAN;
      if (timeStamp[index] != time) {
        timeStamp[index] = time;
        hits[index] = 0;
      }
      hits[index]++;
    }
    
    public int getHits(int time) {
      int res = 0;
      int lastTimeStamp = time - SPAN;
      for (int i = 0; i < SPAN; i++) {
        if (timeStamp[i] > lastTimeStamp) {
          res += hits[i];
        }
      }
      return res;
    }
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
