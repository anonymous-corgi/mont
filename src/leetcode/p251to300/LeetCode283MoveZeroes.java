package leetcode.p251to300;

public class LeetCode283MoveZeroes {

    private interface Method {
        void moveZeroes(int[] nums);
    }

    private static class Normal implements Method {

        @Override
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int left = -1;
            for (int cursor = 0; cursor < nums.length; cursor++) {
                if (nums[cursor] != 0) {
                    int temp = nums[++left];
                    nums[left] = nums[cursor];
                    nums[cursor] = temp;
                }
            }
        }
    }
}
