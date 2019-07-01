package leetcode.p601to650;

/**
 * 643. Maximum Average Subarray I
 * Easy
 * <p>
 * Given an array consisting of n integers,
 * find the contiguous subarray of given length k that has the maximum average value.
 * And you need to output the maximum average value.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 *
 * Note:
 * 1 <= k <= n <= 30,000.
 * Elements of the given array will be in the range [-10,000, 10,000].
 */
public class LeetCode643MaximumAverageSubarray {

    public double findMaxAverage(int[] nums, int k) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return 0d;
        }
        int sum = 0;
        int index = 0;
        while (index < k) {
            sum += nums[index++];
        }
        int max = sum;
        while (index < nums.length) {
            sum += (nums[index] - nums[index++ - k]);
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }
}
