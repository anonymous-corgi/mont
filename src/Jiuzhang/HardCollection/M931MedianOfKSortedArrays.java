package Jiuzhang.HardCollection;

public class M931MedianOfKSortedArrays {

   public double findMedian(int[][] nums) {
       // write your code here
       if (nums == null || nums.length == 0) {
           return 0d;
       }
       if (nums[0] == null || nums[0].length == 0) {
           return 0d;
       }
       int totalNum = 0;
       for (int i = 0, len = nums.length; i < len; i++) {
           totalNum += nums[i].length;
       }
       
       if (totalNum % 2 == 1) {
           return (double) countSum(nums,  totalNum / 2 + 1);
       } else {
           int temp = countSum(nums, totalNum / 2);
           temp += countSum(nums, totalNum / 2 + 1);
           return (double) temp / 2;
       }
   }
   
   private int countSum(int[][] nums, int target) {
       int start  = 0;
       int end = Integer.MAX_VALUE;
       while (start + 1 < end) {
           int mid = start + (end - start) / 2;
           int count = getCount(null, mid);
           if (count < target) {
               start = mid;
           } else {
               end = mid;
           }
       }
       if (getCount(nums, start) >= target) {
    	   return start;
       }
       return end;
   }
   
   private int getCount(int[][] nums, int target) {
       int count = 0;
       for (int i = 0, len = nums.length - 1; i < len; i++) {
           count += binarySearch(nums[i], target);
       }
       return count;
   }
   
   //Count how many numbers are smaller or equal to target
   private int binarySearch(int[] nums, int target) {
       if (nums == null || nums.length == 0) {
           return 0;
       }
       int start = 0;
       int end = nums.length - 1;
       while (start + 1 < end) {
           int mid = start + (end - start) / 2;
           if (nums[mid] <= target) {
               start = mid;
           } else {
               end = mid;
           }
       }
       if (nums[end] <= target) {
           return end + 1;
       }
       if (nums[start] <= target) {
           return start + 1;
       }
       return 0;
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M931MedianOfKSortedArrays one = new M931MedianOfKSortedArrays();
		int[][] arrs = {{5,7,15,27,37,47,50,52,56,100},{29,62},{1,8,66},{},{2,5,37},{},{25,60,80},{48,54,61,69},{19,28,34,50,53,55,66,76,94}};
		System.out.println(one.findMedian(arrs));

	}

}
