package algorithm.jiuzhang.c2.xxoorange;

public class M585MaximumInMountainSequence {
    public int mountainSequence(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        
        return searchMax(nums, 0, nums.length - 1, 0);
    }
    private int searchMax(int[] nums, int head, int end, int lastResult){
        if(head > end){
            return lastResult;
        }
        
        int mid = head + (end - head) / 2;
        if(mid > 0){
            if(nums[mid] > nums[mid - 1]){
                return searchMax(nums, mid, end, nums[mid]);
            }else{
                return searchMax(nums, head, mid, lastResult);
            }
        }else if(mid < nums.length){
            if(nums[mid] > nums[mid + 1]){
                return searchMax(nums, head, mid, nums[mid]);
            }else{
                return searchMax(nums, mid, end, lastResult);
            }
        }else{
            return nums[0];
        }
    }
}
