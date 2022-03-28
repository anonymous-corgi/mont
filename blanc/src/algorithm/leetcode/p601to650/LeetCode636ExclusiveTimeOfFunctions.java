package algorithm.leetcode.p601to650;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LeetCode636ExclusiveTimeOfFunctions {

    private interface Method {
        int[] exclusiveTime(int n, List<String> logs);
    }

    private static class StackMethod implements Method {

        @Override
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
    }

    private static Method getMethod() {
        return new StackMethod();
    }

    @Test
    public void testExclusiveTime1() {
        int n = 1;
        List<String> logs = Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:end:6");
        int[] expected = {7};
        test(n, logs, expected);
    }

    @Test
    public void testExclusiveTime2() {
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7");
        int[] expected = {7, 1};
        test(n, logs, expected);
    }

    @Test
    public void testExclusiveTime3() {
        int n = 1;
        List<String> logs = Arrays.asList("0:start:0", "0:start:1", "0:start:2", "0:end:3", "0:end:4", "0:end:5");
        int[] expected = {6};
        test(n, logs, expected);
    }

    private void test(int n, List<String> logs, int[] expected) {
        Method method = getMethod();
        int[] actual = method.exclusiveTime(n, logs);
        assertThat(actual, equalTo(expected));
    }
}
