package leetcode.p001to050;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unused")
public class LeetCode031NextPermutation {

    private interface Method {
        void nextPermutation(int[] nums);
    }

    private static class Normal implements Method {

        @Override
        public void nextPermutation(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int largestHead = nums.length - 1;
            //Find the largestHead that makes subArray[largestHead, nums.length - 1]
            // the largest permutation of that subArray() - the longest descending subarray in the tail.
            while (largestHead > 0 && nums[largestHead - 1] >= nums[largestHead]) {
                largestHead--;
            }

            swapRange(nums, largestHead, nums.length - 1);
            if (largestHead != 0) {
                int pivot = nums[largestHead - 1];
                //Find the smallest number that is larger than nums[toBeChanged - 1]
                // and swap their positions.
                int firstLarger = largestHead;
                while (nums[firstLarger] <= pivot) {
                    firstLarger++;
                }
                swap(nums, largestHead - 1, firstLarger);
            }
        }

        private void swapRange(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start++, end--);
            }
        }

        private void swap(int[] nums, int start, int end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
    }

    private static Method getMethod() {
        return new Normal();
    }

    @Test
    public void testcase1() {
        int[] nums = new int[]{1, 2, 3};
        int[] expected = new int[]{1, 3, 2};
        test(nums, expected);
    }

    private void test(int[] nums, int[] expected) {
        getMethod().nextPermutation(nums);
        assertThat(nums, is(expected));
    }
}
