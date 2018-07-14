package Jiuzhang.C4.LevelOrderTraversal;


import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class E69BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> result = new ArrayList<>();

    	if(root == null){
    		return result;
    	}
    	
    	Queue<TreeNode> taskList = new LinkedList<>();
    	taskList.offer(root);
    	
    	while(!taskList.isEmpty()){
    		List<Integer> level = new LinkedList<>();
    		
    		int size = taskList.size();
    		for(int i = 0; i < size; i++){
    			TreeNode each = taskList.poll();
    			level.add(each.val);
    			
    			if(each.left != null){
    				taskList.offer(each.left);
    			}    			
    			if(each.right != null){
    				taskList.offer(each.right);
    			}
    		}
    		result.add(level);
    	}
    	return result;
    }
//    TreeNode
	public class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
}
