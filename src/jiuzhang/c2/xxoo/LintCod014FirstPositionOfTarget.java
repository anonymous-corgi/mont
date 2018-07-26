package jiuzhang.c2.xxoo;

public class LintCod014FirstPositionOfTarget {

  public int binarySearch(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return -1;
    }
    
    return binarySearch(nums, target, 0, nums.length - 1, -1);
  }
  
  private int binarySearch(int[] nums, int target, int head, int tail, int lastResult) {
    if(head > tail) {
      return lastResult;
    }
    
    int mid = head + (tail - head) / 2;
    if(nums[mid] < target) {
      return binarySearch(nums, target, mid + 1, tail, lastResult);
    }else if(nums[mid] > target) {
      return binarySearch(nums, target, head, mid - 1, lastResult);
    }else{
      return binarySearch(nums, target, head, mid - 1, mid);
    }
  }

  public int binarySearch2(int[] nums, int target) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < target) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return nums[end] == target ? end : -1;
  }
  
//  public int binarySearch3(int[] nums, int target) {
//    if(nums == null || nums.length == 0){
//      return -1;
//    }
//    
//    int start = 0;
//    int end = nums.length - 1;
//    while(start + 1 < end) {
//      int mid = start + (end - start) / 2;
//      if(nums[mid] < target) {
//        start = mid;
//      }else if(nums[mid] > target) {
//        end = mid;
//      }else {
//        end = mid;
//      }
//    }
//    if(nums[start] == target) {
//      return start;
//    }
//    if(nums[end] == target) {
//      return end;
//    }
//    return -1;
//  }
  
  public static void main(String[] args) {
    int[] nums = {1,4,4,5,7,7,8,9,9,10};
    int target = 4;
    LintCod014FirstPositionOfTarget one = new LintCod014FirstPositionOfTarget();
    System.out.println(one.binarySearch2(nums, target));
  }
  
}
