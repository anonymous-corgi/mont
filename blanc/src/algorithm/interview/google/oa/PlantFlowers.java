package algorithm.interview.google.oa;

public class PlantFlowers {
	
	public boolean canPlant(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return k == 0;
		}
		int count = 0;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 1) {
				if (i + 1 < len && nums[i + 1] != 1) {
					nums[i + 1] = 2;
				}
			} else if (nums[i] == 0) {
				if (i + 1 < len && nums[i + 1] != 1) {
					
				}
			}
		}
		return k <= count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
