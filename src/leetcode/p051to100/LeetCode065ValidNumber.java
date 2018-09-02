package leetcode.p051to100;

public class LeetCode065ValidNumber {
  
  public boolean isNumber(String s) {
    if (s == null || s.length() == 0) {
      return false;
    }
    int start = 0;
    int end = s.length() - 1;
    boolean hasE = false;
    boolean hasNum = false;
    boolean hasPoint = false;
    char[] chs = s.toCharArray();
    while (start < chs.length && chs[start] == ' ') { start++; }
    while (end >= 0 && chs[end] == ' ') { end--;}
    for (; start <= end; start++) {
      if (Character.isDigit(chs[start])) {
        hasNum = true;
      } else if (chs[start] == '.') {
        if (hasE || hasPoint) {
          return false;
        }
        hasPoint = true;
      } else if (chs[start] == 'e') {
        if (!hasNum || hasE) {
          return false;
        }
        hasE = true;
        hasNum = false;
      } else if (chs[start] == '+' || chs[start] == '-') {
        if((hasPoint || hasNum) && (start != 0 && chs[start - 1] != 'e')) {
          return false;
        }
      } else {
        return false;
      }
    }
    return hasNum;
  }

  public static void main(String[] args) {
    LeetCode065ValidNumber one = new  LeetCode065ValidNumber();
    String s = "32.e-80123";
    System.out.println(one.isNumber(s));
  }

}
