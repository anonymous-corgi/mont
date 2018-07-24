package jiuzhang.dp.backpack;

//BackPackIII contains repeated object
public class BackPackII {
  //Count Max value can be stored in m capacity WITHOUT repeated use
  public int backPackII(int m, int[] A, int V[]) {
    if (A == null || A.length == 0) {
      return -1;
    }
    
    int obsLen = A.length;
    int[] dp = new int[m + 1];
    for (int i = 0; i < obsLen; i++) {
      for (int j = m; j >= A[i]; j--) {
        dp[j] = Math.max(dp[j - 1], dp[j - A[i]] + V[i]);
      }
    }
    return dp[m];
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
  }
  
}
