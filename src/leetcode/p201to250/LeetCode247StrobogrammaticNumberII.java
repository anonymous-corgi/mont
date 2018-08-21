package leetcode.p201to250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.Print;

public class LeetCode247StrobogrammaticNumberII {
  
  public List<String> findStrobogrammatic(int n) {
    if (n < 0) {
      return new ArrayList<String>();
    }
    return find(n, n);
  }

  private List<String> find(int n, int len) {
    if (n == 0) {
      return new ArrayList<>(Arrays.asList(""));
    }
    if (n == 1) {
      return new ArrayList<>(Arrays.asList("0", "1", "8"));
    }
    
    List<String> res = new ArrayList<>();
    List<String> prev = find(n - 2, len);
    for (String str : prev) {
      if (n != len) {
        res.add("0" + str + "0");
      }
      res.add("1" + str + "1");
      res.add("6" + str + "9");
      res.add("8" + str + "8");
      res.add("9" + str + "6");
    }
    return res;
  }

  public static void main(String[] args) {
    LeetCode247StrobogrammaticNumberII one = 
        new LeetCode247StrobogrammaticNumberII();
    int n = 4;
    List<String> list = one.findStrobogrammatic(n);
    Print.printList(list);
  }

}
