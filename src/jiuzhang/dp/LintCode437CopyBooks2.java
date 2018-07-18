package jiuzhang.dp;

public class LintCode437CopyBooks2 {
	
    private int[][] dp;
    
    public int copyBooks(int[] pages, int k) {
        // write your code here
        if (pages == null || pages.length == 0) {
            return -1;
        }
        int len = pages.length;
        dp = new int[k][len];
        //init the start status
        for (int i = 0; i < k; i++) {
            dp[i][0] = pages[0];
        }
        for (int j = 1; j < len; j++) {
            dp[0][j] = pages[j] + dp[0][j - 1];
        }
        
        for (int i = 1; i < k; i++) {
            for (int j = 1; j < len; j++) {
                findMin(i, j);
            }
        }
        return dp[k - 1][len - 1];
    }
    
    private void findMin(int person, int total) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= total; i++) {
            int sum = Math.max(dp[person - 1][i], dp[0][total] - dp[0][i]);
            min = Math.min(min, sum);
        }
        dp[person][total] = min;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LintCode437CopyBooks2 one = new LintCode437CopyBooks2();
		int[] arr = {1,2};
		int k = 2;
		System.out.println(one.copyBooks(arr, k));
	}

}
