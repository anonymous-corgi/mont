package jiuzhang.c6.arraylist;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 139. Subarray Sum Closest
 * Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.
 * <p>
 * Example
 * Example1
 * <p>
 * Input:
 * [-3,1,1,-3,5]
 * Output:
 * [0,2]
 * Explanation: [0,2], [1,3], [1,1], [2,2], [0,4]
 * Challenge
 * O(nlogn) time
 */
public class M139SubarraySumCloset {

    public int[] subarraySumClosest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[2];
        }

        int[] result = new int[2];
        Record[] records = new Record[nums.length + 1];
        records[0] = new Record(-1, 0);
        for (int i = 1; i < records.length; i++) {
            int sum = nums[i - 1] + records[i - 1].sum;
            records[i] = new Record(i - 1, sum);
        }

        Arrays.sort(records, Comparator.comparingInt(a -> a.sum));

        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int diff = records[i + 1].sum - records[i].sum;
            if (diff < minSum) {
                minSum = diff;
                result[0] = records[i].index;
                result[1] = records[i + 1].index;
                Arrays.sort(result);
                result[0]++;
            }
        }
        return result;
    }

    private class Record {
        private int index;
        private int sum;

        Record(int i, int s) {
            index = i;
            sum = s;
        }
    }
}
