package jiuzhang.dp.twoarrays;

public class LeetCode072EditDistance {
  
  public int minDistance(String word1, String word2) {
    // write your code here
    if (word1 == null || word2 == null) {
      return 0;
    }
    int wLen1 = word1.length();
    int wLen2 = word2.length();
    int[][] dp = new int[wLen1 + 1][wLen2 + 1];
    for (int i = 1; i <= wLen1; i++) {
      dp[i][0] = i;
    }
    for (int j = 1; j <= wLen2; j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= wLen1; i++) {
      for (int j = 1; j <= wLen2; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
//          dp[i][j] = dp[i - 1][j - 1] + 1;
          dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
        }
      }
    }
    return dp[wLen1][wLen2];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode072EditDistance one = new LeetCode072EditDistance();
    String word1 = "horse";
    String word2 = "ros";
    System.out.println(one.minDistance(word1, word2));
  }

}
