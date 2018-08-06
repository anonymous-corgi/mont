package leetcode.p001to050;

public class LeetCode043MultiplyStrings {
  
  public String multiply(String num1, String num2) {
    if (num1 == null || num2 == null) {
      return "";
    }
    if ("0".equals(num1) || "0".equals(num2)) {
      return "0";
    }
    int l1 = num1.length();
    int l2 = num2.length();
    int[] n1 = toIntArray(num1);
    int[] n2 = toIntArray(num2);
    int[] tRes = new int[l1 + l2];
    StringBuilder sb = new StringBuilder();
    for (int i = l2 - 1; i >= 0; i--) {
      for (int j = l1 - 1; j >= 0; j--) {
        int m = n1[j] * n2[i] + tRes[i + j + 1];
        tRes[i + j + 1] = m % 10;
        tRes[i + j] += m / 10;
      }
    }
    if (tRes[0] != 0) {
      sb.append(tRes[0]);
    }
    for (int i = 1, len = l1 + l2; i < len; i++) {
      sb.append(tRes[i]);
    }
    return sb.toString();
  }
  
  private int[] toIntArray(String s) {
    int[] res = new int[s.length()];
    for (int i = s.length() - 1; i >= 0; i--) {
      res[i] = s.charAt(i) - '0';
    }
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode043MultiplyStrings one = new LeetCode043MultiplyStrings();
    String num1 = "123";    String num2 = "456";
    System.out.println(one.multiply(num1, num2));
  }

}
