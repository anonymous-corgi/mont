package leetcode.p351to400;

import java.util.Stack;

public class LeetCode394DecodeString {
  
  public static class NonRecursive_mehod {
    
    public String decodeString(String s) {
      if (s == null || s.length() == 0) {
        return "";
      }
      char[] chs = s.toCharArray();
      Stack<String> strs = new Stack<>();
      Stack<Integer> times = new Stack<>();
      String res = "";
      int index = 0;
      int sLen = s.length();
      while (index < sLen) {
        if (Character.isDigit(chs[index])) {
          int tail = index;
          while (Character.isDigit(chs[tail])) { tail++; }
          times.push(Integer.parseInt(s.substring(index, tail)));
          index = tail;
        } else if (Character.isAlphabetic(chs[index])) {
          int tail = index;
          while (tail < sLen && Character.isAlphabetic(chs[tail])) { tail++; }
          res = res + s.substring(index, tail);
          index = tail;
        } else if (chs[index] == '[') {
          strs.push(res);
          res = "";
          index++;
        } else if (chs[index] == ']') {
          StringBuilder sb = new StringBuilder(strs.pop());
          int repeat = times.pop();
          for (int i = 0; i < repeat; i++) { sb.append(res); }
          res = sb.toString();
          index++;
        }
      }
      return res;
    }
    
  }
  
  public static class Recursive_mehod {
    
    int index = 0;
    
    public String decodeString(String s) {
      return decode(s);
    }
    
    private String decode(String s) {
      StringBuilder sb = new StringBuilder();
      int count = 0;
      while (index < s.length()) {
        char c = s.charAt(index);
        if (Character.isDigit(c)) {
          count = 10 * count + c - '0';
        } else if (Character.isLetter(c)) {
          sb.append(c);
        } else if (c == '[') {
          index++;
          String next = decode(s);
          for (int i = 0; i < count; i++) {
            sb.append(next);
          }
          count = 0;
        } else if (c == ']') {
          return sb.toString();
        }
        index++;
      }
      return sb.toString();
    }
    
  }

  public static void main(String[] args) {
    LeetCode394DecodeString.NonRecursive_mehod one = 
        new LeetCode394DecodeString.NonRecursive_mehod();
////  "aaabcbc"
//    String s = "3[a]2[bc]";
////  "accaccacc"
//    String s = "3[a2[c]]";
//  "abcabccdcdcdef"
    String s = "2[abc]3[cd]ef";
    System.out.println(one.decodeString(s));
  }

}
