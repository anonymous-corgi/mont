package leetcode.p451to500;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import utils.Print;

public class LeetCode496NextGreaterElementI {
  
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
    int[] res = new int[nums1.length];
    for (int i = 0; i < nums2.length; i++) {
      if (stack.isEmpty() || nums2[stack.peek()] >= nums2[i]) {
        stack.push(i);
      } else {
        map.put(nums2[stack.pop()], nums2[i]);
        i--;
      }
    }
    for (int i = 0; i < nums1.length; i++) {
      res[i] = map.getOrDefault(nums1[i], -1);
    }
    return res;
  }

  public static void main(String[] args) {
    LeetCode496NextGreaterElementI one =
        new LeetCode496NextGreaterElementI();
    int[] nums1 = {4,1,2};
    int[] nums2 = {1,3,4,2};
    Print.printArray(one.nextGreaterElement(nums1, nums2));

  }

}
