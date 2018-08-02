package leetcode.p351to400;

public class LeetCode388LongestAbsoluteFilePath {
  
  public int lengthLongestPath(String input) {
    // Write your code here
    if (input.length() == 0) {
      return 0;
    }
    int ans = 0;
    int[] sum = new int[input.length() + 1];
    
    for (String line : input.split("\n")) {
      int level = line.lastIndexOf('\t') + 2;
      int len = line.length() - (level - 1);
      if (line.contains(".")) {
        ans = Math.max(ans, sum[level - 1] + len);
      } else {
        sum[level] = sum[level - 1] + len + 1;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
