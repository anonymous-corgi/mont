package leetcode.p001to050;

public class LeetCode004MedianOfTwoSortedArrays {
  
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
    if (s1 >= nums1.length) { return nums2[s2 + numToExclude]; }
    if (s2 >= nums2.length) { return nums1[s1 + numToExclude]; }
    if (numToExclude == 0) { return Math.min(nums1[s1], nums2[s2]);}
    
    int ex = (numToExclude + 1) / 2;
    int mid1 = s1 + ex - 1 < nums1.length ? nums1[s1 + ex - 1] : Integer.MAX_VALUE;
    int mid2 = s2 + ex - 1 < nums2.length ? nums2[s2 + ex - 1] : Integer.MAX_VALUE;
    if (mid1 < mid2) {
      return helper(nums1, s1 + ex, nums2, s2, numToExclude - ex);
    } else {
      return helper(nums1, s1, nums2, s2 + ex, numToExclude - ex);
    }
  }
  
//  public double findMedianSortedArrays(int[] A, int[] B) {
//    int m = A.length, n = B.length;
//    int l = (m + n + 1) / 2;
//    int r = (m + n + 2) / 2;
//    return l == r ? getkth(A, 0, B, 0, l) 
//        : (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
//  }
//  
//  public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
//    if (aStart > A.length - 1) return B[bStart + k - 1];            
//    if (bStart > B.length - 1) return A[aStart + k - 1];                
//    if (k == 1) return Math.min(A[aStart], B[bStart]);
//    
//    int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
//    if (aStart + k / 2 - 1 < A.length) aMid = A[aStart + k / 2 - 1]; 
//    if (bStart + k / 2 - 1 < B.length) bMid = B[bStart + k / 2 - 1];        
//    
//    if (aMid < bMid) 
//      return getkth(A, aStart + k / 2,  B, bStart,         k - k / 2);// Check: aRight + bLeft 
//    else 
//      return getkth(A, aStart,          B, bStart + k / 2, k - k / 2);// Check: bRight + aLeft
//  }
//
//  public static void main(String[] args) {
//    // TODO Auto-generated method stub
//    LeetCode004MedianOfTwoSortedArrays one
//      = new LeetCode004MedianOfTwoSortedArrays();
//    int[] A = {3,5,8};
//    int[] B = {4,6,7,9};
//    System.out.println(one.findMedianSortedArrays(A, B));
//
//  }

  public static void main(String[] args) {
    int[] nums1 = {1, 3};
    int[] nums2 = {2, 3, 4, 5, 7};
    LeetCode004MedianOfTwoSortedArrays one =
        new LeetCode004MedianOfTwoSortedArrays();
    System.out.println(one.findMedianSortedArrays(nums1, nums2));
  }

}