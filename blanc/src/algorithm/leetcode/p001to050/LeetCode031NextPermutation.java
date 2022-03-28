package algorithm.leetcode.p001to050;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeetCode031NextPermutation {

    private interface LastPermutation {
        void lastPermutation(int[] nums);
    }

    private interface NextPermutation {
        void nextPermutation(int[] nums);
    }

    private static class LastImpl implements LastPermutation {

        /**
         * For an Ascending array, the last permutation is the reversed. (For 1,2,3,4 is 4,3,2,1)
         * For a random permutation array (1,3,2,4), we should:
         * 1. Find the range of the tail ascending array. ([2,3], tailAscendingHead = 2(index))
         * 2. Find the largest number in the tail range that is smaller than nums[tailAscendingHead - 1],
         * and swap the position.
         * 3. Reverse the entire tail range.
         */
        @Override
        public void lastPermutation(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int tailAscendingHead = nums.length - 1;
            // 1. Find the tailAscendingHead.
            while (tailAscendingHead > 0 && nums[tailAscendingHead - 1] <= nums[tailAscendingHead]) {
                tailAscendingHead--;
            }

            swapRange(nums, tailAscendingHead, nums.length - 1);
            if (tailAscendingHead != 0) {
                int pivot = nums[tailAscendingHead - 1];

                // 2. Find the largest number that is smaller than pivot (nums[smallestHead - 1])
                // and swap their positions.
                int nearestSmaller = tailAscendingHead;
                while (nums[nearestSmaller] >= pivot) {
                    nearestSmaller++;
                }

                swap(nums, tailAscendingHead - 1, nearestSmaller);
            }
        }
    }

    private static class NextImpl implements NextPermutation {

        @Override
        public void nextPermutation(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int tailDescendingHead = nums.length - 1;
            //Find the tailDescendingHead that makes subArray[tailDescendingHead, nums.length - 1]
            //the largest permutation of that subArray() - the longest descending subarray in the tail.
            while (tailDescendingHead > 0 && nums[tailDescendingHead - 1] >= nums[tailDescendingHead]) {
                tailDescendingHead--;
            }

            swapRange(nums, tailDescendingHead, nums.length - 1);
            if (tailDescendingHead != 0) {
                int pivot = nums[tailDescendingHead - 1];
                //Find the smallest number that is larger than nums[toBeChanged - 1]
                // and swap their positions.
                int firstLarger = tailDescendingHead;
                while (nums[firstLarger] <= pivot) {
                    firstLarger++;
                }

                swap(nums, tailDescendingHead - 1, firstLarger);
            }
        }
    }

    private static void swapRange(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private static void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    private static LastPermutation getLastPermutation() {
        return new LastImpl();
    }

    private static NextPermutation getNextPermutation() {
        return new NextImpl();
    }

    private void test(int[] nums, int[] expectedNext) {
        int[] origin = Arrays.copyOf(nums, nums.length);
        getNextPermutation().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        assertThat(nums, is(expectedNext));
        getLastPermutation().lastPermutation(nums);
        System.out.println(Arrays.toString(nums));
        assertThat(nums, is(origin));
    }

    @Test
    public void testcase1() {
        int[] nums = new int[]{1, 3, 2, 4};
        int[] expected = new int[]{1, 3, 4, 2};
        test(nums, expected);
    }

    @Test
    public void testcase2() {
        int[] nums = new int[]{4, 3, 2, 1};
        int[] expected = new int[]{1, 2, 3, 4};
        test(nums, expected);
    }

    @Test
    public void testcase3() {
        int[] nums = new int[]{1, 4, 3, 3, 2};
        int[] expected = new int[]{2, 1, 3, 3, 4};
        test(nums, expected);
    }
}
