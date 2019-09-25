package leetcode.p051to100;

import java.util.Stack;

public class LeetCode084LargestRectangleInHistogram {

    private interface Method {
        int largestRectangleArea(int[] heights);
    }

    private static final class Greedy implements Method {

        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }
            int max = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i <= heights.length; ) {
                if (stack.isEmpty() || i < heights.length && heights[i] >= heights[stack.peek()]) {
                    // The design is that we push i into stack.
                    // And only pop the stack when the cursor move to j, where heights[i] > heights[j]
                    // In this case, we find that all heights between i and j are not lower than height[i]
                    stack.push(i++);
                } else {
                    int hIndex = stack.pop();
                    int height = heights[hIndex];
                    int length = stack.isEmpty() ? i : i - 1 - stack.peek();
                    // area = height x length
                    // We pop and get a hIndex.
                    // hIndex may be discontiguous to stack.peek(),
                    // because there might be an n (stack.peek() < n && n < hIndex), heights[n] > heights[hIndex],
                    // and n has already been popped.
                    // Also, n (hIndex < n && i < n) must have heights[n] >= heights[hIndex].
                    // All in all, for hIndex, stack.peek() is the first index whose height is lower than hIndex,
                    // i is the last index whose height is lower than hIndex.
                    // [stack.peek() + 1, i - 1] is the range of rectangle whose height are not lower than hIndex.
                    int area = height * length;
                    max = Math.max(max, area);
                }
            }
            return max;
        }
    }
}
