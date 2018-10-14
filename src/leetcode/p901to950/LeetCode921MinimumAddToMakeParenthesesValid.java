package leetcode.p901to950;

public class LeetCode921MinimumAddToMakeParenthesesValid {

  public int minAddToMakeValid(String S) {
    int left = 0;
    int right = 0;
    for (char c : S.toCharArray()) {
      if (c == '(') {
        left++;
      } else {
        if (left > 0) {
          left--;
        } else {
          right++;
        }
      }
    }
    return left + right;
  }

}
