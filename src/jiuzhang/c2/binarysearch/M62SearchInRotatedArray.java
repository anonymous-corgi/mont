package jiuzhang.c2.binarysearch;

public class M62SearchInRotatedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        
        return search(nums, target, 0, nums.length - 1);
    }
    
    private int search(int[] nums, int target, int head, int end){
        if(head > end){
            return -1;
        }
        
        int mid = head + (end - head) / 2;
        if(nums[mid] == target){
            return mid;
        }
        if(nums[mid] >= nums[head]){
            if(nums[head] <= target && target < nums[mid]){
                return search(nums, target, head, mid - 1);
            }else{
                return search(nums, target, mid + 1, end);
            }
        }else{
            if(nums[mid] < target && target <= nums[end]){
                return search(nums, target, mid + 1, end);
            }else{
                return search(nums, target, head, mid - 1);
            }
        }
    }
}
