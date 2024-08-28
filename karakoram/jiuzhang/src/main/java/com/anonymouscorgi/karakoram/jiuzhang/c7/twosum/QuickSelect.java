package com.anonymouscorgi.karakoram.jiuzhang.c7.twosum;

public class QuickSelect {
	
    public int quickSelect(int[] nums, int k) {
		if (nums == null || nums.length < k) {
			return -1;
		}
		return sort(k, nums, 0, nums.length - 1);
	}
	
	private int sort(int k, int[] nums, int start, int end) {
		int pivot = nums[end];
		int pointer = start - 1;
		for (int i = start; i <= end; i++) {
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//if (nums[i] <= pivot) is for smallest
			//if (nums[i] <= pivot) is for largest!
			if (nums[i] >= pivot) {
				pointer++;
				int temp = nums[pointer];
				nums[pointer] = nums[i];
				nums[i] = temp;
			}
		}
		
		/* */
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i] + "  ");
		}
		System.out.print("\t" + "start: " + start + ", end: " + end + ", pivot: " + pivot + ", pointer : " + pointer);
		System.out.println();
		/* */
		//pointerָ�����nums[end]���ڵ�λ�ã���Ϊpointerǰ����<=nums[end]������
		//���ǰ�ߵ�����С��nums[end],��ô��pointer+1���С����ǡ�þ���nums[end]��
		//���ǰ�ߵ����е���nums[end],��ô��oiner+1���С��������nums[end]��ֻ������ֵͬ����������ǰ�� 
		if (pointer < k - 1) {
			return sort(k, nums, pointer + 1, end);
		} else if (pointer >  k - 1) {
			return sort(k, nums, start, pointer - 1); 
		} else {
			return nums[k - 1];
		}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSelect one = new QuickSelect();
		int[] nums = {3,4,1,2,5};
		int k = 1;
		
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i] + "  ");
		}
		System.out.println();
		System.out.print(one.quickSelect(nums, k));
	}

}
