package utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import basicclass.TreeNode;

public class Print {
	
	public static void printArray(int[] nums) {
		printArray(nums, 0, nums.length);
	}
	
	public static void printArray(int[] nums, int start, int end) {
		if (nums == null || nums.length == 0) {
			System.out.println("<Error> The input array is null.");
			return;
		}
		int len = nums.length;
		if (len == 0) {
			System.out.println("<Error> The input array's length is 0.");
			return;			
		}
		end = len > end ? end : len;
		if (start >= end) {
			System.out.println("<Error> The startIndex and the endIndex are invalid.");
			return;
		}
		System.out.println("The Array or SubArray: (length: "+ (end - start) + ")");
		System.out.print("{");
		
		int breakIndex = 0;
		for (int i = start, length = end - 1; i < length; i++) {
			System.out.print(nums[i] + ",");
			breakIndex++;
			if (breakIndex == 10) {
				System.out.println();
				breakIndex = 0;
			}
		}
		System.out.println(nums[end - 1] + "}");
	}
	
	public static void printList(List<?> list, int start, int end) {
		if (list == null) {
			System.out.println("<Error> The input list is null.");
			return;
		}
		int len = list.size();
		if (len == 0) {
			System.out.println("<Error> The input list's length is 0.");
			return;
		}
		end = len > end ? end : len;
		if (start >= end) {
			System.out.println("<Error> The startIndex and the endIndex are invalid.");
			return;
		}
		System.out.println("The Array or SubArray: (length: "+ (end - start) + ")");
		
		System.out.print("{");		
		int breakIndex = 0;
		for (int i = start, length = end - 1; i < length; i++) {
			System.out.print(list.get(i) + ",");
			breakIndex++;
			if (breakIndex == 10) {
				System.out.println();
				breakIndex = 0;
			}
		}
		System.out.println(list.get(end - 1) + "}");
		
		
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
