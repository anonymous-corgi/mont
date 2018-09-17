package leetcode.p601to650;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.junit.Test;

public class LeetCode636ExclusiveTimeOfFunctions {
  
  public int[] exclusiveTime(int n, List<String> logs) {
    int prevTime = 0;
    int[] res = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (String log : logs) {
      String[] strs = log.split(":");
      int func = Integer.parseInt(strs[0]);
      int time = Integer.parseInt(strs[2]);
      if (!stack.isEmpty()) {
        res[stack.peek()] += time - prevTime;
      }
      prevTime = time;
      if ("start".equals(strs[1])) {
        stack.push(func);
      } else {
        res[stack.pop()]++;
        prevTime++;
      }
    }
    return res;
  }
  
  
  @Test
  public void testExclusiveTime1() {
    int n = 1;
    List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","0:end:6");
    int[] res = {7};
    assertArrayEquals(res, exclusiveTime(n, logs));
  }  
  
  @Test
  public void testExclusiveTime2() {
    int n = 2;
    List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7");
    int[] res = {7,1};
    assertArrayEquals(res, exclusiveTime(n, logs));
  }
  
  @Test
  public void testExclusiveTime3() {
    int n = 1;
    List<String> logs = Arrays.asList("0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5");
    int[] res = {6};
    assertArrayEquals(res, exclusiveTime(n, logs));
  }
  
  public static void main(String[] args) {
    int n = 1;
    List<String> logs = Arrays.asList("0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5");
    LeetCode636ExclusiveTimeOfFunctions one =
        new LeetCode636ExclusiveTimeOfFunctions();
    one.exclusiveTime(n, logs);
  }

}
