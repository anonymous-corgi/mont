package leetcode.p201to250;

public class LeetCode248StrobogrammaticNumberIII {
  
  public int strobogrammaticInRange(String low, String high) {
    int[] res = new int[1];
    int[] lenLimit = new int[]{low.length(), high.length()};
    int[] valueLimit = new int[]{Integer.parseInt(low), Integer.parseInt(high)};
    dfs("", lenLimit, valueLimit, res);
    dfs("0", lenLimit, valueLimit, res);
    dfs("1", lenLimit, valueLimit, res);
    dfs("8", lenLimit, valueLimit, res);
    return res[0];
  }
  
  private void dfs(String str, int[] lenLimit, int[] valueLimit, int[] res) {
    if (str.length() > lenLimit[1]) {
      return;
    }
    if (str.length() >= lenLimit[0] && (str.length() == 1 || str.charAt(0) != '0')) {
      int value = Integer.parseInt(str);
      if (value >= valueLimit[0] && value <= valueLimit[1]) {
        res[0]++;
      }
    }

    dfs("0" + str + "0", lenLimit, valueLimit, res);
    dfs("1" + str + "1", lenLimit, valueLimit, res);
    dfs("6" + str + "9", lenLimit, valueLimit, res);
    dfs("8" + str + "8", lenLimit, valueLimit, res);
    dfs("9" + str + "6", lenLimit, valueLimit, res);
  }
  
  public static void main(String[] args) {
    String low = "0";
    String high = "100";
    LeetCode248StrobogrammaticNumberIII one = 
        new LeetCode248StrobogrammaticNumberIII();
    int num = one.strobogrammaticInRange(low, high);
    System.out.println(num);
  }
  
}
