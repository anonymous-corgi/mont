package leetcode.p201to250;

@SuppressWarnings("unused")
public class LeetCode209MinimumSizeSubarraySum {

    private interface Method {
        int minSubArrayLen(int s, int[] nums);
    }

    private static class Greedy implements Method {

        @Override
        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int sum = 0;
            int start = 0;
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                while (sum >= s && start <= i) {
                    res = Math.min(res, i - start + 1);
                    sum -= nums[start++];
                }
            }
            return res != Integer.MAX_VALUE ? res : 0;
        }
    }

    private static class Binary implements Method {

        @Override
        public int minSubArrayLen(int s, int[] nums) {
            int res = Integer.MAX_VALUE;
            int[] prefix = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
            for (int i = 0; i < nums.length; i++) {
                int target = s + prefix[i];
                int index = findFirst(prefix, i, target);
                if (index != -1) {
                    res = Math.min(res, index - i);
                }
            }
            return res != Integer.MAX_VALUE ? res : 0;
        }

        private int findFirst(int[] prefix, int start, int target) {
            int end = prefix.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (prefix[mid] >= target) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return prefix[end] >= target ? end : -1;
        }
    }
}
