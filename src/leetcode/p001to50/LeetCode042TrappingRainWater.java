package leetcode.p001to50;

public class LeetCode042TrappingRainWater {
	
  public int trap(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int left = 0;
    int right = height.length - 1;
    int lHeight = height[0];
    int rHeight = height[right];
    int totalCapacity = 0;
    
    while (left < right) {
      lHeight = Math.max(lHeight, height[left]);
      rHeight = Math.max(rHeight, height[right]);
      
      if (height[left] <= height[right]) {
        while (left < right && height[left] <= lHeight) {
          totalCapacity += lHeight - height[left++];
        }
      } else {
        while (left < right && height[right] <= rHeight) {
          totalCapacity += rHeight - height[right--];
        }
      }
    }
    return totalCapacity;
  }
  
  //This method is similar to the LeetCode135 Candy 
  public int trap2(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int total = 0;
    int[] leftMaxHeight = new int[height.length];
    int[] rightMaxHeight = new int[height.length];
    leftMaxHeight[0] = height[0];
    rightMaxHeight[height.length - 1] = height[height.length - 1];
    for (int i = 1; i < height.length; i++) {
      leftMaxHeight[i] = Math.max(leftMaxHeight[i - 1], height[i]);
    }
    for (int i = height.length - 2; i >= 0; i--) {
      rightMaxHeight[i] = Math.max(rightMaxHeight[i + 1], height[i]);
    }
    for (int i = 0; i < height.length; i++) {
      total += Math.min(rightMaxHeight[i], leftMaxHeight[i]) - height[i];
    }
    return total;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode042TrappingRainWater one = new LeetCode042TrappingRainWater();
    int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
    System.out.println(one.trap2(heights));
  }

}
