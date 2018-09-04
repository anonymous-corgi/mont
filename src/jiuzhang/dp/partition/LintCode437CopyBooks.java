package jiuzhang.dp.partition;

public class LintCode437CopyBooks {
  
  public static class Classic_DP_method {
    
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
      dp[person][pagesNum] = min;
    }
    
  }
  
  public static class Improved_DP_method {
    
    public int copyBooks(int[] pages, int persons) {
      int LEN = pages.length;
      int[] f = new int[LEN + 1];
      int[] prefix = new int[LEN + 1];
      for (int i = 1; i <= LEN; i++) {
        prefix[i] = prefix[i - 1] + pages[i - 1];
        f[i] = prefix[i];
      }
      for (int p = 1; p < persons; p++) {
        for (int load = LEN; load > p; load--) {
          f[load] = Integer.MAX_VALUE;
          for (int split = load - 1; split >= p; split--) {
            int t = Math.max(f[split], prefix[load] - prefix[split]);
            if (t > f[load]) {
              break;
            }
            f[load] = t;
          }
        }
      }
      return f[LEN];
    }
    
  }
  
  public static void main(String[] args) {
    int[] arr = {1,2};
    int k = 2;
    LintCode437CopyBooks.Improved_DP_method one = 
        new LintCode437CopyBooks.Improved_DP_method();
    System.out.println(one.copyBooks(arr, k));
  }
  
}
