package algorithm.leetcode.p101to150;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

@RunWith(Parameterized.class)
public class LeetCode128LongestConsecutiveSequence {
    private final int[] nums;
    private final int expected;

    public LeetCode128LongestConsecutiveSequence(int[] nums, int expected) {
        this.nums = nums;
        this.expected = expected;
    }

    private interface Method {
        int longestConsecutive(int[] nums);
    }

    // Similar to Union Find
    private static final class Map_Method implements Method {

        private static final class Node {
            int lower;
            int upper;

            Node(int l, int u) {
                this.lower = l;
                this.upper = u;
            }
        }

        public int longestConsecutive(int[] nums) {
            int max = 0;
            Map<Integer, Node> map = new HashMap<>();
            for (int i : nums) {
                insert(map, i);
            }

            for (Map.Entry<Integer, Node> entry : map.entrySet()) {
                Node n = entry.getValue();
                max = Math.max(max, n.upper - n.lower + 1);
            }
            return max;
        }

        private void insert(Map<Integer, Node> map, int num) {
            if (map.containsKey(num)) {
                return;
            }
            Node n = new Node(num, num);
            if (map.containsKey(num + 1)) {
                Node u = map.get(num + 1);
                remove(map, u);
                n.upper = Math.max(n.upper, u.upper);
                n.lower = Math.min(n.lower, u.lower);
            }
            if (map.containsKey(num - 1)) {
                Node l = map.get(num - 1);
                remove(map, l);
                n.upper = Math.max(n.upper, l.upper);
                n.lower = Math.min(n.lower, l.lower);
            }
            map.put(n.upper, n);
            map.put(n.lower, n);
        }

        private void remove(Map<Integer, Node> map, Node n) {
            map.remove(n.upper);
            map.remove(n.lower);
        }
    }

    private static Method getMethod() {
        return new Map_Method();
    }

    private void test(int[] nums, int expected) {
        int actual = getMethod().longestConsecutive(nums);
        assertThat(actual, is(expected));
    }

    @Test
    public void test() {
        test(nums, expected);
    }

    @Parameterized.Parameters
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9},
                {new int[]{1, 2, 0, 1}, 3},
                {new int[]{-7, -1, 3, -9, -4, 7, -3, 2, 4, 9, 4, -9, 8, -7, 5, -1, -7}, 4},
        };
    }
}
