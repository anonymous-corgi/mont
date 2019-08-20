package leetcode.p201to250;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode215KthLargestElementInAnArray {

    private int res;
    private int[] nums;
    private int k;

    public LeetCode215KthLargestElementInAnArray(int res, int[] nums, int k) {
        this.res = res;
        this.nums = nums;
        this.k = k;
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
        assertThat(getMethod().findKthLargest(nums, k), is(res));
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{5, new int[]{3, 2, 1, 5, 6, 4}, 2}});
    }
}
