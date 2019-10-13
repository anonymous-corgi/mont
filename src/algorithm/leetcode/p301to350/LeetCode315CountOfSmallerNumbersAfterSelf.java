package algorithm.leetcode.p301to350;

import java.util.LinkedList;
import java.util.List;
import algorithm.base.utils.Print;

public class LeetCode315CountOfSmallerNumbersAfterSelf {
  
  public static class BinarySearchTree_Method {
    
    private Node root;
    private static class Node{
      int num;
      int dup = 1;
      int leftNum = 0;
      Node left;
      Node right;
      public Node(int num) {
        this.num = num;
      }
    }
    
    private Node insert(Node root, int num, int prev, List<Integer> res) {
      if (root == null) {
        root = new Node(num);
        res.add(0, prev);
      } else if (root.num > num){
        root.leftNum++;
        root.left = insert(root.left, num, prev, res);
      } else if (root.num < num) {
        root.right = insert(root.right, num, prev + root.dup + root.leftNum, res);
      } else {
        root.dup++;
        res.add(0, prev + root.leftNum);
      }
      return root;
    }
    
    public List<Integer> countSmaller(int[] nums) {
      List<Integer> res = new LinkedList<>();
      for (int i = nums.length - 1; i >= 0; i--) {
        root = insert(root, nums[i], 0, res);
      }
      return res;
    }
    
  }

  public static class IndexedTree_Method {
    
    public List<Integer> countSmaller(int[] nums) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int num : nums) {
        min = Math.min(min, num);
        max = Math.max(max, num);
      }
      int[] bit = new int[max - min + 2];
      List<Integer> res = new LinkedList<>();
      for (int i = nums.length - 1; i >= 0; i--) {
        int index = nums[i] - min + 1;
        for (int j = index; j < bit.length; j += (j & -j)) {
          bit[j]++;
        }
        res.add(0, getSum(bit, index - 1));
      }
      return res;
    }
    
    private int getSum(int[] bit, int i) {
      int res = 0;
      for (; i > 0; i -= (i & -i)) {
        res += bit[i];
      }
      return res;
    }
    
  }
  
  public static void main(String[] args) {
    LeetCode315CountOfSmallerNumbersAfterSelf.BinarySearchTree_Method one =
        new LeetCode315CountOfSmallerNumbersAfterSelf.BinarySearchTree_Method();
    int[] nums = {5,1,2,2,6,1};
    Print.printList(one.countSmaller(nums));
  }

}
