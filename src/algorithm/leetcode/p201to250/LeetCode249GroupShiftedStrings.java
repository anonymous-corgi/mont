package algorithm.leetcode.p201to250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import algorithm.base.utils.Print;

public class LeetCode249GroupShiftedStrings {
  
  public List<List<String>> groupStrings(String[] strs) {
    List<List<String>> res = new ArrayList<>();
    if (strs == null || strs.length == 0) {
      return res;
    }
    Map<Long, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] sca = str.toCharArray();
      long n = 0;
      for (int i = 1; i < sca.length; i++) {
        n *= 26;
        int diff = sca[i] - sca[0];
        if (diff < 0) {
          diff += 26;
        }
        n += diff;
      }
      List<String> list = map.getOrDefault(n, new ArrayList<String>());
      list.add(str);
      map.put(n, list);
    }
    for (List<String> each : map.values()) {
      res.add(each);
    }
    return res;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode249GroupShiftedStrings one
    = new LeetCode249GroupShiftedStrings();
    String[] strs = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
    List<List<String>> res = one.groupStrings(strs);
    Print.printListList(res);
  }

}
