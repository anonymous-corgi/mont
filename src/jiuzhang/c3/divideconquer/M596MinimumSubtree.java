package jiuzhang.c3.divideconquer;

public class M596MinimumSubtree {
	public class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
    public TreeNode findSubtree(TreeNode root) {
        if(root == null){
            return null;
        }
        return findMin(root).minTree;
    }
    
    private class ResultType{
        TreeNode minTree;
        int minSum;
        int sum;
        public ResultType(TreeNode t, int m, int s){
            this.minTree = t;
            this.minSum = m;
            this.sum = s;
        }
    }
    
    private ResultType findMin(TreeNode t){
        if(t == null){
            return new ResultType(t, Integer.MAX_VALUE, 0);
        }
        
        ResultType leftMin = findMin(t.left);
        ResultType rightMin = findMin(t.right);
        
        int sum = t.val + leftMin.sum + rightMin.sum;
        if(sum < Math.min(leftMin.minSum, rightMin.minSum)){
            return new ResultType(t, sum, sum);
        }else if(leftMin.minSum < rightMin.minSum){
            return new ResultType(leftMin.minTree, leftMin.minSum, sum);
        }else{
            return new ResultType(rightMin.minTree, rightMin.minSum, sum);
        }
    }
}
