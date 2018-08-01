package leetcode.p051to100;

import java.util.LinkedList;

public class LeetCode060PermutationSequence {
  
  public String getPermutation(int n, int k) {
    if (n < 1 || k < 1) {
      return "";
    }
    
    int pos = 1;
    LinkedList<Integer> source = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= n; i++) {
      pos *= i;
      source.add(i);
    }
    k--;
    int digitIndex = n;
    while (digitIndex > 0) {
      pos /= digitIndex--;
      int i = k / pos;
      k %= pos;
      sb.append(source.remove(i));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
