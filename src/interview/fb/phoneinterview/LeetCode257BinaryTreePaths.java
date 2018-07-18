package interview.fb.phoneinterview;

import java.util.LinkedList;
import java.util.List;

import basicclass.TreeNode;

public class LeetCode257BinaryTreePaths {
	
	public List<String> binaryTreePaths(TreeNode root) {
		
		List<String> paths = new LinkedList<>();
		
		if(root == null) return paths;
		
		if(root.left == null && root.right == null){
			paths.add(root.val + "");
			return paths;
		}
		
		for (String path : binaryTreePaths(root.left)) {
			paths.add(root.val + "->" + path);
		}
		
		for (String path : binaryTreePaths(root.right)) {
			paths.add(root.val + "->" + path);
		}
		
		return paths;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = TreeNode.deserialize("{1,2,3,4,5,6,7,8}");
		LeetCode257BinaryTreePaths one = new LeetCode257BinaryTreePaths();
		List<String> list = one.binaryTreePaths(root);
		for (int i = 0, len = list.size(); i < len; i++) {
			System.out.println(list.get(i));			
		}
	}

}
