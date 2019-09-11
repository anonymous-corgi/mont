package leetcode.p201to250;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode215KthLargestElementInAnArray {

    private int[] nums;
    private int k;
    private int expected;

    public LeetCode215KthLargestElementInAnArray(int[] nums, int k, int expected) {
        this.nums = nums;
        this.k = k;
        this.expected = expected;
    }

    private interface Method {
        int findKthLargest(int[] nums, int k);
    }

    private static class QuickSelect implements Method {

        @Override
        public int findKthLargest(int[] nums, int k) {
            return findKth(nums, 0, nums.length, k - 1);
        }

        private int findKth(int[] nums, int start, int end, int k) {
            int left = start - 1;
            int pivot = nums[end - 1];
            for (int i = start; i < end; i++) {
                if (nums[i] >= pivot) {
                    swap(nums, ++left, i);
                }
            }
            if (left > k) {
                return findKth(nums, start, left, k);
            } else if (left < k) {
                return findKth(nums, left + 1, end, k);
            } else {
                return nums[left];
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    private static Method getMethod() {
        return new QuickSelect();
    }

    @Test
    public void test() {
        int actual = getMethod().findKthLargest(nums, k);
        assertThat(actual, is(expected));
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{new int[]{3, 2, 1, 5, 6, 4}, 2, 5}, {new int[]{3, 2}, 1, 3}});
    }
}
