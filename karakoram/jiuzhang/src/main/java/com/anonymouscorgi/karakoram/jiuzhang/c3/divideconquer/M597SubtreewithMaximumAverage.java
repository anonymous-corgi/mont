package com.anonymouscorgi.karakoram.jiuzhang.c3.divideconquer;

public class M597SubtreewithMaximumAverage {
	public class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
    public TreeNode findSubtree2(TreeNode root) {
        if(root == null){
            return null;
        }
        return findMax(root).maxNode;
    }
    
    private class Result{
        // 不单记录�??小Tree 还要记录这个Tree的信�??
        TreeNode maxNode ;
        double maxAve;
        int theNum;
        int theSum;
        
        public Result(TreeNode t, double ma, int n, int s){
            this.maxNode = t;
            this.maxAve = ma;
            this.theNum = n;
            this.theSum = s;
        }
    }
    
    private Result findMax(TreeNode t){
        if(t == null){
            return new Result(null, Double.NEGATIVE_INFINITY, 0, 0);
        }
        
        Result lt = findMax(t.left);
        Result rt = findMax(t.right);
        
        int number = lt.theNum + rt.theNum + 1;
        int sum = lt.theSum + rt.theSum + t.val;
        double average = (double) sum / number;
        
        if(average > Math.max(lt.maxAve, rt.maxAve)){
            return new Result(t, average, number, sum);
        }else if(lt.maxAve > rt.maxAve){
            return new Result(lt.maxNode, lt.maxAve, number, sum);
        }else{
            return new Result(rt.maxNode, rt.maxAve, number, sum);
        }
        
    }
}
