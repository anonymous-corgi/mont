package algorithm.leetcode.p151to200;

public class LeetCode151ReverseWordsInAString {
  
  public static class CharArray_method {
    
    public String reverseWords(String s) {
      if (s == null || s.length() == 0) {
        return "";
      }
      char[] chs = s.toCharArray();
      int len = move(chs);
      if (len == -1) {
        return "";
      }
      swapRange(chs, 0, len - 1);
      for (int i = 0, j = 0; i <= len; i++) {
        if (i == len || chs[i] == ' ') {
          swapRange(chs, j, i - 1);
          j = i + 1;
        }
      }
      String res = String.valueOf(chs, 0, len);
      return res;
    }
    
    private int move(char[] chs) {
      int last = -1;
      int cur = 0;      
      while (cur < chs.length && chs[cur] == ' ') {
        cur++;
      }
      while (cur < chs.length) {
        last++;
        while (cur < chs.length && chs[cur] != ' ') {
          swap(chs, last++, cur++);
        }  
        while (cur < chs.length && chs[cur] == ' ') {
          cur++;
        }
      }
      return last;
    }
    
    private void swap(char[] chs, int a, int b) {
      char t = chs[a];
      chs[a++] = chs[b];
      chs[b--] = t;
    }
    
    private void swapRange(char[] chs, int a, int b) {
      while (a < b) {
        swap(chs, a++, b--);
      }
    }
    
  }
  
  public static class Concat_method {
    
    public String reverseWords(String s) {
      String[] parts = s.trim().split("\\s+");
      String out = "";
      for (int i = parts.length - 1; i > 0; i--) {
        out += parts[i] + " ";
      }
      return out + parts[0];
    }
    
  }

  public static void main(String[] args) {
    LeetCode151ReverseWordsInAString.CharArray_method one = 
        new LeetCode151ReverseWordsInAString.CharArray_method();
//    String s = "the sky is blue ";
    String s = "hi!";
    System.out.println(one.reverseWords(s));
  }

}
