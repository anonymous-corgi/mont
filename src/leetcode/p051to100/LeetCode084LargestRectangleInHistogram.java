package leetcode.p051to100;

import java.util.Stack;

public class LeetCode084LargestRectangleInHistogram {

    private interface Method {
        int largestRectangleArea(int[] heights);
    }

    private static class Greedy implements Method {

        @Override
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }
            int max = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i <= heights.length; ) {
                if (stack.isEmpty() || i < heights.length && heights[i] >= heights[stack.peek()]) {
                    stack.push(i++);
                } else {
                    int hIndex = stack.pop();
                    int height = heights[hIndex];
                    int length = stack.isEmpty() ? i : i - 1 - stack.peek();
                    // area = height x length
                    // We pop and get a hIndex.
                    // hIndex may be contiguous or discontiguous to stack.peek(),
                    // because [stack.peek(), hIndex].length may larger than 2,
                    // there might be some n (stack.peek() < n && n < hIndex), heights[n] > heights[hIndex],
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
