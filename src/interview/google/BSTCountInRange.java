package interview.google;

import algorithm.base.TreeNode;

public class BSTCountInRange {
  //һ��BST�������������Ҫ��дһ���㷨��������������(low, high)֮������ֵĺ�
  //(low < val <= high)��
  
  public int count(TreeNode root, int low, int high) {
    if (root == null) {
      return 0;
    }
    int sum = 0;
    if (root.val <= high && root.val >= low) {
      sum += count(root.left, low, high);
      sum += count(root.right, low, high);
      sum += root.val;
    } else if (root.val < low) {
      sum += count(root.right, low, high);
    } else if (root.val > high) {
      sum += count(root.left, low, high);
    }
    return sum;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String data = "[1,2,3,4,5,6]";
    TreeNode root = TreeNode.deserializeSortedArray(data);
    int low = 2;
    int high = 3;
    BSTCountInRange one = new BSTCountInRange();
    System.out.println(one.count(root, low, high));
  }

}
