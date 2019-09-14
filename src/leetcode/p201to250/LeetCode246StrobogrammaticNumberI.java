package leetcode.p201to250;

/**
 * 246. Strobogrammatic Number
 * Easy
 * rammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * Example 1:
 * Input:  "69"
 * Output: true
 * <p>
 * Example 2:
 * Input:  "88"
 * Output: true
 * <p>
 * Example 3:
 * Input:  "962"
 * Output: false
 */
@SuppressWarnings("unused")
public class LeetCode246StrobogrammaticNumberI {

    private interface Method {
        boolean isStrobogrammatic(String num);
    }

    private static final class TwoPointers implements Method {

        private static final int[] STRO = {0, 1, -2, -3, -4, -5, 9, -7, 8, 6};

        @Override
        public boolean isStrobogrammatic(String num) {
            if (num == null || num.isEmpty()) {
                return false;
            }
            int left = 0;
            int right = num.length() - 1;
            while (left <= right) {
                int l = num.charAt(left++) - '0';
                int r = num.charAt(right--) - '0';
                if (STRO[l] != r) {
                    return false;
                }
            }
            return true;
        }
    }
}
