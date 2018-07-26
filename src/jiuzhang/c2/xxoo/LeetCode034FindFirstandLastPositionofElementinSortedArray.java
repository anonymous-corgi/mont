package jiuzhang.c2.xxoo;

public class LeetCode034FindFirstandLastPositionofElementinSortedArray {
  
  public int[] searchRange(int[] nums, int target) {
    int[] res = {-1, -1};
    if (nums == null || nums.length == 0) {
      return res;
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
    
    if (nums[end] != target) {
      return res;
    }
    res[0] = end;
    
    end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start + 1) / 2;
      if (nums[mid] > target) {
        end = mid - 1;
      } else {
        start = mid;
      }
    }
    res[1] = start;
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode034FindFirstandLastPositionofElementinSortedArray one =
        new LeetCode034FindFirstandLastPositionofElementinSortedArray();
    int[] nums = {};
    int target = 1;
    int[] res = one.searchRange(nums, target);
    System.out.println("[" + res[0] + "," + res[1] + "]");
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
  
  public int lastPosition(int[] nums, int target) {
    // write your code here
    // write your code here
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start + 1) / 2;
      if (nums[mid] > target) {
        end = mid - 1;
      } else {
        start = mid;
      }
    }
    return nums[start] == target ? start : -1;
  }

}
