package leetcode.p401to450;

public class LeetCode411ValidWordSquare {
  
  public boolean validWordSquare(String[] words) {
    // Write your code here
    if (words == null || words.length == 0) {
      return true;
    }
    for (int i = 0, iLen = words.length - 1; i < iLen; i++) {
      for (int j = i + 1, jLen = words[i].length(); j < jLen; j++) {
        if (words[i].charAt(j) != words[j].charAt(i)) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
