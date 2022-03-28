package algorithm.leetcode.p351to400;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode359LoggerRateLimiter {
  
  /**
   * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
   * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
   * It is possible that several messages arrive roughly at the same time.
   * 
   * Example:
   * Logger logger = new Logger();
   * 
   * // logging string "foo" at timestamp 1
   * logger.shouldPrintMessage(1, "foo"); returns true; 
   * 
   * // logging string "bar" at timestamp
   * logger.shouldPrintMessage(2,"bar"); returns true;
   * 
   * // logging string "foo" at timestamp 3
   * logger.shouldPrintMessage(3,"foo"); returns false;
   * 
   * // logging string "bar" at timestamp 8
   * logger.shouldPrintMessage(8,"bar"); returns false;
   * 
   * // logging string "foo" at timestamp 10
   * logger.shouldPrintMessage(10,"foo"); returns false;
   * 
   * // logging string "foo" at timestamp 11
   * logger.shouldPrintMessage(11,"foo"); returns true;
   */
  public static class Map_method {
    
    private final Map<String, Integer> stub = new HashMap<>();
    
    public boolean shouldPrintMessage(int timestamp, String msg) {
      if (!stub.containsKey(msg) || stub.get(msg) <= timestamp - 10 ) {
        stub.put(msg, timestamp);
        return true;
      }
      return false;
    }
    
  }
  
  //Optimize the peak space usage.
  public static class Bucket_method {
    
    private final int INTERVAL = 10;
    private final int[] time = new int[INTERVAL];
    @SuppressWarnings("unchecked")
    private final Set<String>[] set = new Set[INTERVAL];
    
    public Bucket_method() {
      for (int i = 0; i < INTERVAL; i++) {
        set[i] = new HashSet<String>();
        time[i] = -INTERVAL;
      }
    }
    
    public boolean shouldPrintMessage(int timestamp, String msg) {
      int index = timestamp % INTERVAL;
      if (time[index] > timestamp) {
        set[index].clear();
        time[index] = timestamp;
      }
      return set[index].add(msg);
    }
    
  }
  

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
