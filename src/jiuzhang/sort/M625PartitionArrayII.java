package Jiuzhang.Sort;

public class M625PartitionArrayII {
	
    public void partition2(int[] nums, int low, int high) {
        // write your code here
        if(nums == null || nums.length == 0){
            return;
        }
        int cursor = 0;
        int left = 0;
        int right = nums.length - 1;
        while(cursor <= right){
            if(nums[cursor] < low){
                if(cursor != left){
                    swap(nums, cursor, left);
                }
                cursor++;
                left++;
            }else if(nums[cursor] > high){
                if(cursor != high){
                    swap(nums, cursor, right);
                }
                right--;
            }else{
                cursor++;
            }
        }
    }
    
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {4,3,4,1,2,3,1,2};
		M625PartitionArrayII one = new M625PartitionArrayII();
		one.partition2(arr, 3, 3);

	}

}
