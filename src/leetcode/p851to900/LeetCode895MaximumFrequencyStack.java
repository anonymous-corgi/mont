package leetcode.p851to900;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class LeetCode895MaximumFrequencyStack {
  
  public static class Map_method {
    
    private int maxFreq = 0;
    private Map<Integer, Integer> frequency = new HashMap<>();
    private Map<Integer, Stack<Integer>> elements = new HashMap<>();
    
    public void push(int x) {
      int freq = frequency.getOrDefault(x, 0) + 1;
      if (freq > maxFreq) {
        maxFreq = freq;
        elements.putIfAbsent(freq, new Stack<Integer>());
      }
      frequency.put(x, freq);
      elements.get(freq).push(x);
    }
    
    public int pop() {
      int res = elements.get(maxFreq).pop();
      frequency.put(res, maxFreq - 1);
      if (elements.get(maxFreq).isEmpty()) {
        maxFreq--;
      }
      return res;
    }
    
  }
  
  public static class PQ_method {
    
    private int timeStamp = 0;
    private PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> 
                                  {return b[1] != a[1] ? b[1] - a[1] :b[2] - a[2];});
    private Map<Integer, Integer> frequency = new HashMap<>();
    
    public void push(int x) {
      int freq = frequency.getOrDefault(x, 0) + 1;
      frequency.put(x, freq);
      pq.add(new int[]{x, freq, timeStamp++});
    }
    
    public int pop() {
      int[] element = pq.poll();
      frequency.put(element[0], frequency.get(element[0]) - 1);
      return element[0];
    }
    
  }

  public static void main(String[] args) {

  }

}
