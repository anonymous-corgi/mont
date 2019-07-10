package leetcode.p001to050;

public class LeetCode004MedianOfTwoSortedArrays {

    private interface Method {
        double findMedianSortedArrays(int[] nums1, int[] nums2);
    }

    // Beats 100% submissions on Jul. 01 2019
    private static class Binary_Method implements Method {

        @Override
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
                return 0d;
            }
            int total = nums1.length + nums2.length;
            return ((total & 1) == 1) ? helper(nums1, 0, nums2, 0, total / 2) :
                    (helper(nums1, 0, nums2, 0, total / 2 - 1)
                            + helper(nums1, 0, nums2, 0, total / 2)) / 2.0;
        }

        private int helper(int[] nums1, int s1, int[] nums2, int s2, int numToExclude) {
            if (s1 == nums1.length) {
                return nums2[s2 + numToExclude];
            }
            if (s2 == nums2.length) {
                return nums1[s1 + numToExclude];
            }
            if (numToExclude == 0) {
                return Math.min(nums1[s1], nums2[s2]);
            }

            int ex = (numToExclude + 1) / 2;
            // Each nums can exclude nums.length at most.
            int ex1 = Math.min(ex, nums1.length - s1);
            int ex2 = Math.min(ex, nums2.length - s2);
            // The key is that: If you want to exclude ex number from a sub-array nums start from s1,
            // you should take nums[s1 + ex -1]. Because nums[s1] to nums[s1 + ex -1] has ex number.
            if (nums1[s1 + ex1 - 1] < nums2[s2 + ex2 - 1]) {
                return helper(nums1, s1 + ex1, nums2, s2, numToExclude - ex1);
            } else {
                return helper(nums1, s1, nums2, s2 + ex2, numToExclude - ex2);
            }
        }
    }

    private static class Linear_Method implements Method {

        @Override
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
                return 0d;
            }
            int total = nums1.length + nums2.length;
            return ((total & 1) == 1) ? helper(nums1, nums2, total / 2) :
                    (helper(nums1, nums2, total / 2 - 1)
                            + helper(nums1, nums2, total / 2)) / 2.0;
        }

        private int helper(int[] nums1, int[] nums2, int k) {
            int i1 = 0;
            int i2 = 0;
            while (i1 < nums1.length && i2 < nums2.length && k > 0) {
                if (nums1[i1] < nums2[i2]) {
                    i1++;
                } else {
                    i2++;
                }
                k--;
            }

            if (i1 == nums1.length) {
                return nums2[i2 + k];
            }
            if (i2 == nums2.length) {
                return nums1[i1 + k];
            }
            return Math.min(nums1[i1], nums2[i2]);
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 4, 5, 7};
        LeetCode004MedianOfTwoSortedArrays.Linear_Method one =
                new LeetCode004MedianOfTwoSortedArrays.Linear_Method();
        System.out.println(one.findMedianSortedArrays(nums1, nums2));
    }
}