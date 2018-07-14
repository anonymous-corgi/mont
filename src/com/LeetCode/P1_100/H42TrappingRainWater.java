package com.LeetCode.P1_100;

public class H42TrappingRainWater {
	
    public int trap(int[] height) {
        int index = 0;
        int len = height.length;
        int leftWallHeight = 0;
        int totalCapacity = 0;
        int subCapacity = 0;
        for (; index < len; index++) {
            if (height[index] >= leftWallHeight) {
                totalCapacity += subCapacity;
                subCapacity = 0;
                leftWallHeight = height[index];
            } else {
                subCapacity += (leftWallHeight - height[index]);
            }
        }
        return totalCapacity;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		H42TrappingRainWater one = new H42TrappingRainWater();
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		one.trap(height);
	}

}
