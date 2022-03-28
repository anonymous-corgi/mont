package algorithm.interview.fb;

public class LintCode1398KDecimalAddition {
  
  public String addition(int k, String a, String b) {
    // Write your code here
    if (a == null && b == null) {
      return "";
    } else if (a == null) {
      return b;
    } else if (b == null) {
      return a;
    }
    
    int i = 1;
    int r = 0;
    a = validString(a, a.length());
    b = validString(b, b.length());
    int aLen = a.length();
    int bLen = b.length();
    StringBuilder sb = new StringBuilder();
    while (i <= aLen && i <= bLen) {
      r = helper(a.charAt(aLen - i) - '0', b.charAt(bLen - i) - '0', r, k, sb);
      i++;
    }
    while (i <= aLen) {
      r = helper(a.charAt(aLen - i) - '0', 0, r, k, sb);
      i++;
    }
    while (i <= bLen) {
      r = helper(0, b.charAt(bLen - i) - '0', r, k, sb);
      i++;
    }
    if (r != 0) {
      sb.insert(0, r);
    }
    return sb.toString();
  }
  
  private String validString(String str, int len) {
    int index = 0;
    while (index < len - 1 && str.charAt(index) == '0') {
      index++;
    }
    return str.substring(index);
  }
  
  private int helper(int aV, int bV, int r, int k, StringBuilder sb) {
    int sum = aV + bV + r;
    r = sum / k;
    sb.insert(0, sum % k);
    return r;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
