package algorithm.leetcode.p401to450;

public class LeetCode443StringCompression {
  
  public int compress(char[] chars) {
    char prev = chars[0];
    int index = 0;
    int count = 1;
    for (int i = 1; i <= chars.length; i++) {
      char cur = i == chars.length ? '0' : chars[i];
      if (cur == prev) {
        count++;
      } else {
        chars[index++] = prev;
        if (count != 1) {
          for (char c : ("" + count).toCharArray()) {
            chars[index++] = c;
          }
        }
        prev = cur;
        count = 1;
      }
    }
    return index;
  }

  public static void main(String[] args) {
    LeetCode443StringCompression one = 
        new LeetCode443StringCompression();
    char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
    one.compress(chars);
  }

}
