package algorithm.leetcode.p051to100;

/**
 * LeetCode 55. Jump Game
 * Medium
 * <p>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class LeetCode055JumpGame {

    private interface Method {
        boolean canJump(int[] nums);
    }

    private static class Greedy1 implements Method {

        @Override
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return true;
            }
            int farthest = 0;
            for (int pos = 0; pos < nums.length && farthest < nums.length - 1; pos++) {
                if (farthest < pos) {
                    return false;
                }
                farthest = Math.max(farthest, pos + nums[pos]);
            }
            return true;
        }
    }

    private static class Greedy2 implements Method {

        @Override
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return true;
            }
            int gap = 0;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] - 1 >= gap) {
                    gap = 0;
                } else {
                    gap++;
                }
            }
            return (gap == 0);
        }
    }
}
