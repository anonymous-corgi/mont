package jiuzhang.dp.backpack;

//BackPackII doesn't contain repeated object
public class BackPackIII {
  //Count Max value can be stored in m capacity WITH repeated use
  public int backPackIII(int m, int[] A, int[] V) {
    if (A == null || A.length == 0) {
      return -1;
    }
    
    int obsLen = A.length;
    int[] dp = new int[m + 1];
    for (int i = 0; i < obsLen; i++) {
      for (int j = A[i]; j <= m; j++) {
        dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
      }
    }
    return dp[m];
  }
  
  //Method2: Loop from weight first.
  public int backPackII(int m, int[] A, int V[]) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int obsLen = A.length;
    int[] dp = new int[m + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 0; j < obsLen; j++) {
        if (A[j] > i) {
          continue;
        }
        dp[i] = Math.max(dp[i], dp[i - A[j]] + V[j]);
      }
    }
    return dp[m];
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int m = 30;
    int[] A = {1,2,3,4,5,6,7,8,9,10};
    int[] V = {1,5,8,9,10,17,17,20,24,30};
    BackPackIII one = new BackPackIII();
    System.out.println(one.backPackIII(m, A, V));
  }
  
}
