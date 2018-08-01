package leetcode.p401to450;

import java.util.ArrayList;
import java.util.List;
import utils.Print;

public class LeetCode438FindAllAnagramsInAString {
  
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> list = new ArrayList<>();
    if (s == null || p == null 
        || s.length() == 0 || p.length() == 0) {
      return list;
    }
    int head = 0;
    int deficiency = 0;
    int[] stub = new int[128];
    for (char c : p.toCharArray()) {
      stub[c]++;
      deficiency++;
    }
    for (int i = 0, end = s.length(); i < end; i++) {
      if (stub[s.charAt(i)]-- > 0) {
        deficiency--;
      }
      while (deficiency == 0) {
        if (i - head + 1 == p.length()) {
          list.add(head);
        }
        if (stub[s.charAt(head++)]++ >= 0) {
          deficiency++;
        }
      }
    }
    return list;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode438FindAllAnagramsInAString one 
    = new LeetCode438FindAllAnagramsInAString();
//    String s = "cbaebabacd";
//    String p = "abc";
    String s = "abab";
    String p = "ab";
    Print.printList(one.findAnagrams(s, p));
  }

}
