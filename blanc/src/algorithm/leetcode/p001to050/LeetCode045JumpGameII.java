package algorithm.leetcode.p001to050;

public class LeetCode045JumpGameII {

    private interface Method {
        int jump(int[] nums);
    }

    private static class Greedy1 implements Method {

        @Override
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            // The start and end of each jump.
            int start = 0;
            int end = 0;
            int jump = 0;
            while (end < nums.length - 1) {
                jump++;
                int farthest = end;
                for (int i = start; i <= end; i++) {
                    farthest = Math.max(farthest, nums[i] + i);
                }
                if (farthest == end) {
                    return -1;
                }
                start = end + 1;
                end = farthest;
            }
            return jump;
        }
    }
}
