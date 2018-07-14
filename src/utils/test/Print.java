package utils.test;

import java.util.LinkedList;
import java.util.Stack;

import BasicClass.TreeNode;

public class Print {
	
	public static void printArray(int[] nums) {
		printArray(nums, 0, nums.length - 1);
	}
	
	public static void printArray(int[] nums, int start, int end) {
		if (nums == null || nums.length == 0) {
			System.out.println("<Error> The input array is null or whose length is 0.");
			return;
		}
		if (start > end) {
			System.out.println("<Error> The startIndex: \"" + start + "\" and the endIndex: \"" + end + "\" are conflicted.");
			return;
		}		
		
		System.out.println("The Array or SubArray is: (length: "+ (end - start + 1) + ")");
		System.out.print("{");
		int breakIndex = 0;
		for (int i = start; i < end; i++) {
			System.out.print(nums[i] + ",");
			breakIndex++;
			if (breakIndex == 10) {
				System.out.println();
				breakIndex = 0;
			}
		}
		System.out.print(nums[end] + "}");
	}
		
	public static void printTree(TreeNode root) {
		LinkedList<TreeNode> list = treenodeIterator(root);
		System.out.print("\nThe tree is:\n");
		int len = list.size() - 1;
		for (int i = 0; i < len; i++) {
			System.out.print(list.get(i).val);
			System.out.print(',');
		}
		System.out.print(list.get(len).val);
	}
	
	public static LinkedList<TreeNode> treenodeIterator(TreeNode root) {
		LinkedList<TreeNode> result = new LinkedList<>();
		if (root == null) {
			return result;
		}
		
		Stack<TreeNode> taskList = new Stack<>();
		
		while (root != null) {
			taskList.push(root);
			root = root.left;
		}
		
		while (!taskList.isEmpty()) {
			TreeNode current = taskList.pop();
			result.add(current);
			
			current = current.right;			
			while (current != null) {
				taskList.push(current);
				current = current.left;
			}
		}		
		
		return result;
	}
	
	
}
