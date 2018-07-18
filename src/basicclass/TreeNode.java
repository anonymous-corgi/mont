package basicclass;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode implements Iterable<TreeNode> {	
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
	
	public static String serialize(TreeNode root) {
		if (root == null) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		
		Queue<TreeNode> taskList = new LinkedList<>();
		taskList.offer(root);
		while (!taskList.isEmpty()) {
			TreeNode current = taskList.poll();
			if (current != null) {
				sb.append(current.val + ",");
				taskList.offer(current.left);
				taskList.offer(current.right);				
			} else {
				sb.append("#,");
			}
		}
		int len;
		while (sb.charAt((len = sb.length()) - 2) == '#') {
			sb.delete(len - 2, len);
		}
		sb.setCharAt(len - 1, '}');
		
		return sb.toString();
	}
	
	public static TreeNode deserialize(String data) {
		if (data == null || data.length() < 3) {
			return null;
		}
		String[] datas = data.substring(1, data.length() - 1).split(",");
		int index = 1;
		int len = datas.length;
		Queue<TreeNode> taskList = new LinkedList<>();		
		TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
		taskList.offer(root);
		
		while (index < len) {
			TreeNode current = taskList.poll();
			
			String nextData = datas[index++];			
			if (!"#".equals(nextData)) {
				TreeNode left = new TreeNode(Integer.parseInt(nextData));
				current.left = left;
				taskList.offer(left);
			}
			if (index >= len) {
				break;
			}
			nextData = datas[index++];
			if (!"#".equals(nextData)) {
				TreeNode right = new TreeNode(Integer.parseInt(nextData));
				current.right = right;
				taskList.offer(right);
			}
		}
		return root;
	}

	@Override
	public Iterator<TreeNode> iterator() {
		// TODO Auto-generated method stub
		return new TreeNodeIterator(this);
	}
	
	public class TreeNodeIterator implements Iterator<TreeNode>  {
		
		Stack<TreeNode> stack;
		
		private TreeNodeIterator(TreeNode root) {
			stack = new Stack<>();
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public TreeNode next() {
			TreeNode cursor = stack.pop();
			TreeNode root = cursor.right;
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			return cursor;
		}
		
	}
	
}
