package leetcode.p201to250;

public class LeetCode205IsomorphicStrings {
  
  public boolean isIsomorphic(String s1, String s2) {
    int[] m = new int[512];
    for (int i = 0; i < s1.length(); i++) {
      if (m[s1.charAt(i)] != m[s2.charAt(i) + 256]) {
        return false;
      }
      m[s1.charAt(i)] = m[s2.charAt(i) + 256] = i + 1;
    }
    return true;
  }

  public static void main(String[] args) {
    String s1 = "egg";
    String s2 = "add";
    LeetCode205IsomorphicStrings one =
        new LeetCode205IsomorphicStrings();
    System.out.println(one.isIsomorphic(s1, s2));
  }

}
