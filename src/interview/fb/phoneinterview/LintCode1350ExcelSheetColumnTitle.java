package interview.fb.phoneinterview;

public class LintCode1350ExcelSheetColumnTitle {
  //This problem is hard because the start index is different: A-Z is 0-algorithm.base But 1-26 is 1-algorithm.base
  public String convertToTitle(int n) {
    // write your code here
    if (n < 1) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      n--;
      char c = (char) ('A' + n % 26);
      sb.insert(0, c);
      n /= 26;
    }
    return sb.toString();
  }
  
  public int converToInt(String str) {
    if (str == null) {
      return 0;
    }
    int sLen = str.length();
    if (sLen == 0) {
      return 0;
    }
    int i = 0;
    int r = 0;
    while (i < sLen) {
      r *= 26;
      r += str.charAt(i) - 'A' + 1; 
      i++;
    }
    return r;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LintCode1350ExcelSheetColumnTitle one = new LintCode1350ExcelSheetColumnTitle();
    int num = 26;
    String sNum = one.convertToTitle(num);
    int sInt = one.converToInt(sNum);
    System.out.println("Input Num: " + num + ". Output string: " + sNum + ", int: " + sInt + ".");
  }

}
