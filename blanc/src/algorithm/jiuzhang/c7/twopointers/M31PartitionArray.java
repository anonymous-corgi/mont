package algorithm.jiuzhang.c7.twopointers;

public class M31PartitionArray {
	
    public int partitionArray(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int left = 0, right = nums.length - 1;
        
        for(int i = 0; i < nums.length; i++){
        	System.out.print(nums[i] + "  ");
        }
        System.out.println();
        
        while (left <= right) {

            while (left <= right && nums[left] < k) {
                left++;
            }
//          'left' points to the one that is not qualified the judgment, which means the right hand side in the sorted array will be those.
//           In here, the right part in the sorted array will be those >= k

            while (left <= right && nums[right] > k) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                
                left++;
                right--;
            }
            
            for(int i = 0; i < nums.length; i++){
            	System.out.print(nums[i] + "  ");
            }
            System.out.println();
            
        }
        
        for(int i = 0; i < nums.length; i++){
        	System.out.print(nums[i] + "  ");
        }
        System.out.println();
        System.out.println("left: " + left + ". right: " + (right));
        
        return left;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {9,1,5,2,7,2,4,6,1};
//			{2,8,5, 4,2,3, 7,4,1};
		M31PartitionArray one = new M31PartitionArray();
		System.out.println(one.partitionArray(arr, 2));
	}
}
