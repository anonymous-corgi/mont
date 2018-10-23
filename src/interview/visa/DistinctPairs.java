package interview.visa;

import java.util.Arrays;

public class DistinctPairs {
  
  interface Method {
    int findDistinctPairs(int[] nums, int k);
  }
  
  public static class LogN_method implements Method {

    @Override
    public int findDistinctPairs(int[] nums, int k) {
      Arrays.sort(nums);
      int rEnd = nums.length - 1;
      int l = 0;
      int r = rEnd;
      int count = 0;
      while (l < r) {
        int sum = nums[l] + nums[r];
        if (sum > k) {
          do {
            r--;
          } while (l < r && nums[r] == nums[r + 1]);
        } else if (sum < k) {
          do {
            l++;
          } while (l < r && nums[l] == nums[l - 1]);
        } else {
          count++;
          do {
            l++;
          } while (l < r && nums[l] == nums[l - 1]);
          do {
            r--;
          } while (l < r && nums[r] == nums[r + 1]);
        }
      }
      return count;
    }

  }
  
  
  private Method getInstance() {
    return new LogN_method();
  }
  
  public int findDistinctPairs(int[] nums, int k) {
    return getInstance().findDistinctPairs(nums, k);
  }
  
  
  public static void main(String[] args) {
    int[] nums = {1,2,2,3,4};
    int k = 4;
    DistinctPairs one = new DistinctPairs();
    System.out.println(one.findDistinctPairs(nums, k));
  }

}
