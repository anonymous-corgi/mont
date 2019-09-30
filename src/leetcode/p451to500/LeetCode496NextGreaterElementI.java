package leetcode.p451to500;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode496NextGreaterElementI {
    private final int[] nums1;
    private final int[] nums2;
    private final int[] expected;

    public LeetCode496NextGreaterElementI(int[] nums1, int[] nums2, int[] expected) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.expected = expected;
    }

    private interface Method {
        int[] nextGreaterElement(int[] nums1, int[] nums2);
    }

    private static final class StackImpl implements Method {

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> nextGreaterMap = new HashMap<>(nums2.length);
            Stack<Integer> greaterStack = new Stack<>();
            int[] res = new int[nums1.length];
            for (int i = nums2.length - 1; i >= 0; i--) {
                while (!greaterStack.isEmpty() && nums2[i] >= greaterStack.peek()) {
                    greaterStack.pop();
                }
                int nextGreaterNum = greaterStack.isEmpty() ? -1 : greaterStack.peek();
                nextGreaterMap.put(nums2[i], nextGreaterNum);
                greaterStack.push(nums2[i]);
            }
            for (int i = 0; i < nums1.length; i++) {
                res[i] = nextGreaterMap.get(nums1[i]);
            }
            return res;
        }
    }

    private static Method getMethod() {
        return new StackImpl();
    }

    private void test(int[] nums1, int[] nums2, int[] expected) {
        int[] actual = getMethod().nextGreaterElement(nums1, nums2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testcase() {
        test(nums1, nums2, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}, new int[]{-1, 3, -1}},
        };
    }
}
