package leetcode.p351to400;

public class LeetCode389FindTheDifference {
  
  public char findTheDifference(String s, String t) {
    int CodeS = 0;
    int CodeT = 0;

    for (int i = 0; i < s.length(); i++) {
      CodeS += (int) s.charAt(i);
    }
    for (int i = 0; i < t.length(); i++) {
      CodeT += (int) t.charAt(i);
    }
    // Return the difference between 2 strings as char
    return (char)(CodeT - CodeS);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
