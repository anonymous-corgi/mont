package jiuzhang.dp.partition;

public class LintCode437CopyBooks2 {
	
    private int[][] dp;
    
    public int copyBooks(int[] pages, int persons) {
        // write your code here
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int pagesLen = pages.length;
        dp = new int[persons + 1][pagesLen + 1];
        //Initiate the start status
        for (int i = 1; i <= persons; i++) {
            dp[i][1] = pages[0];
        }
        for (int j = 1; j <= pagesLen; j++) {
            dp[1][j] = pages[j - 1] + dp[1][j - 1];
        }
        
        for (int i = 2; i <= persons; i++) {
            for (int j = 1; j <= pagesLen; j++) {
                findMin(i, j);
            }
        }
        return dp[persons][pagesLen];
    }
    
//    private void findMin(int person, int pagesNum) {
//        int min = Integer.MAX_VALUE;
//        for (int i = 1; i <= pagesNum; i++) {
//            int sum = Math.max(dp[person - 1][i], dp[1][pagesNum] - dp[1][i]);
//            min = Math.min(min, sum);
//        }
//        dp[person][pagesNum] = min;
//    }
    
    //Improve performance by Binary search
    private void findMin(int person, int pagesNum) {
      int min = Integer.MAX_VALUE;
      int start = 1;
      int end = pagesNum;
      while (start < end) {
        int mid = start + (end - start) / 2;
        int secondPerson = (dp[1][pagesNum] - dp[1][mid]);
        int diff = secondPerson - dp[person - 1][mid];
        if (diff > 0) {
          start = mid + 1;
        } else if (diff < 0) {
          end = mid - 1;
        } else {
          dp[person][pagesNum] = dp[person - 1][mid];
          return;
        }
        min = Math.min(min, Math.max(dp[person - 1][mid], secondPerson));
      }
//      min = Math.min(min, Math.max(dp[person - 1][start], (dp[1][pagesNum] - dp[1][start])));
//      min = Math.min(min, Math.max(dp[person - 1][end], (dp[1][pagesNum] - dp[1][end])));
      dp[person][pagesNum] = min;
    }
    
//    private void findMin(int person, int pagesNum) {
//      int min = Integer.MAX_VALUE;
//      int start = 1;
//      int end = pagesNum;
//      while (start + 1 < end) {
//        int mid = start + (end - start) / 2;
//        int secondPerson = (dp[1][pagesNum] - dp[1][mid]);
//        int diff = dp[person - 1][mid] - secondPerson;
//        if (diff > 0) {
//          end = mid;
//        } else if (diff < 0) {
//          start = mid;
//        } else {
//          dp[person][pagesNum] = dp[person - 1][mid];
//          return;
//        }
//        min = Math.min(min, Math.max(dp[person - 1][mid], secondPerson));
//      }
//      min = Math.min(min, Math.max(dp[person - 1][start], (dp[1][pagesNum] - dp[1][start])));
//      min = Math.min(min, Math.max(dp[person - 1][end], (dp[1][pagesNum] - dp[1][end])));
//      dp[person][pagesNum] = min;
//    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LintCode437CopyBooks2 one = new LintCode437CopyBooks2();
		int[] arr = {1,2};
		int k = 2;
		System.out.println(one.copyBooks(arr, k));
	}

}
