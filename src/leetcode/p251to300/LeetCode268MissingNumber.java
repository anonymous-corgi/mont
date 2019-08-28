package leetcode.p251to300;

@SuppressWarnings("unused")
public class LeetCode268MissingNumber {

    private interface Method {
        int missingNumber(int[] nums);
    }

    private static class XOR implements Method {

        @Override
        public int missingNumber(int[] nums) {
            int res = nums.length;
            for (int i = 0; i < nums.length; i++) {
                res ^= i;
                res ^= nums[i];
            }
            return res;
        }
    }

    private static class Sum implements Method {

        @Override
        public int missingNumber(int[] nums) {
            int res = nums.length;
            for (int i = 0; i < nums.length; i++) {
                res += i;
                res -= nums[i];
            }
            return res;
        }
    }
}
