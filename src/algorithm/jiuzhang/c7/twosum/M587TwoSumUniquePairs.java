package algorithm.jiuzhang.c7.twosum;

public class M587TwoSumUniquePairs {
	
    public int twoSum6(int[] nums, int target) {
        // write your code here
        if(nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        int head = 0;
        int tail = nums.length - 1;
        while(head < tail){
            int sum = nums[head] + nums[tail];
            if(sum > target){
                do{
                    tail--;
                }while(tail > 0 && nums[tail] == nums[tail + 1]);
            }else if(sum < target){
                do{
                    head++;
                }while(head < nums.length - 1 && nums[head] == nums[head - 1]);
            }else{
                count++;
                head++;
                tail--;
            }
        }
        return count;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,1,2,45,46,46};
		int target = 47;
		M587TwoSumUniquePairs one = new M587TwoSumUniquePairs();
		System.out.println(one.twoSum6(arr, target));

	}

}
