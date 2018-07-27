package jiuzhang.dp.longestincreasingsubsequence;

public class M76LongestIncreasingSubsequence {
	
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] leastNum = new int[nums.length + 1];//The least number in a sequence of the corresponding number of item
        leastNum[0] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length + 1; i++) {
        	leastNum[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(leastNum, nums[i]);
            leastNum[index] = nums[i];
        }
        
        for (int i = nums.length; i >= 0; i--) {
            if (leastNum[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return 0;
    }
    
    private int binarySearch(int[] leastNum, int target) {
        int start = 0;
        int end = leastNum.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (leastNum[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }
	
//    public int longestIncreasingSubsequence(int[] nums) {
//        int[] minLast = new int[nums.length + 1];
//        minLast[0] = Integer.MIN_VALUE;
//        for (int i = 1; i <= nums.length; i++) {
//            minLast[i] = Integer.MAX_VALUE;
//        }
//        
//        for (int i = 0; i < nums.length; i++) {
//            // find the first number in minLast >= nums[i]
//            int index = binarySearch(minLast, nums[i]);
//            minLast[index] = nums[i];
//        }
//        
//        for (int i = nums.length; i >= 1; i--) {
//            if (minLast[i] != Integer.MAX_VALUE) {
//                return i;
//            }
//        }
//        
//        return 0;
//    }
//    
//    // find the first number > num
//    private int binarySearch(int[] minLast, int num) {
//        int start = 0, end = minLast.length - 1;
//        while (start + 1 < end) {
//            int mid = (end - start) / 2 + start;
//            if (minLast[mid] < num) {
//                start = mid;
//            } else {
//                end = mid;
//            }
//        }
//        
//        return end;
//    }
	
//    public int longestIncreasingSubsequence(int[] nums) {
//        // write your code here
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int[] record = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            record[i] = 1;
//        }
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[j] < nums[i]) {
//                    record[i] = Math.max(record[j] + 1, record[i]);
//                }
//            }
//        }
//        return record[nums.length - 1];
//    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M76LongestIncreasingSubsequence one = new M76LongestIncreasingSubsequence();
		int[] arr = {88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59};
		one.longestIncreasingSubsequence(arr);
	}

}
