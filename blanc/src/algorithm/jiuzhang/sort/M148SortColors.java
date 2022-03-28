package algorithm.jiuzhang.sort;

public class M148SortColors {
	
    public void sortColors(int[] nums) {
        if(nums == null){
            return ;
        }
        int left = 0;
        int right = nums.length - 1;
        int cursor = 0;
        while(cursor <= right){
            if(nums[cursor] == 0){
            	if(cursor != left){
            		swap(nums, cursor, left);
            	}
                left++;
                cursor++;
            }else if(nums[cursor] == 2){
            	if(cursor != right){
            		swap(nums, cursor, right);
            	}
                right--;
            }else{
                cursor++;
            }
        }
    }
    private void swap(int[] nums, int a, int b){
        nums[b] += nums[a];
        nums[a] = nums[b] - nums[a];
        nums[b] = nums[b] - nums[a];
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M148SortColors one = new M148SortColors();
		int[] arr = {2,0,0,1,2,0,2};
		one.sortColors(arr);
	}
}
