package algorithm.leetcode.p851to900;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode893GroupsOfSpecialEquivalentStrings {
  
  public int numSpecialEquivGroups(String[] A) {
    Set<String> set = new HashSet<String>();
    for (String str : A) {
      int mid = (str.length() + 1) / 2;
      char[] even = new char[mid];
      char[] back = new char[str.length() - mid];
      for (int i = 0; i < mid; i++) {
        even[i] = str.charAt(i * 2);
      }
      for (int i = 0; i < back.length; i++) {
        back[i] = str.charAt(i * 2 + 1);
      }
      Arrays.sort(even);
      Arrays.sort(back);
      String res = String.valueOf(even) + String.valueOf(back);
      set.add(res);
    }
    return set.size();
  }

  public static void main(String[] args) {
    
  }

}
