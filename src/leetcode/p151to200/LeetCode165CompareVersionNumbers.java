package leetcode.p151to200;

public class LeetCode165CompareVersionNumbers {
  
  public int compareVersion(String version1, String version2) {
    if (version1 == null) {
      return -1;
    }
    if (version2 == null) {
      return 1;
    }
    String[] vs1 = version1.split("\\.");
    String[] vs2 = version2.split("\\.");
    int index = 0;
    while (index < vs1.length || index < vs2.length) {
      int v1 = index < vs1.length ? Integer.parseInt(vs1[index]) : 0;
      int v2 = index < vs2.length ? Integer.parseInt(vs2[index]) : 0;
      if (v1 < v2) {
        return -1;
      } else if (v1 > v2) {
        return 1;
      }
      index++;
    }
    return 0;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode165CompareVersionNumbers one =
        new LeetCode165CompareVersionNumbers();
    String version1 = "1.0.1";
    String version2 = "1";
    System.out.println(one.compareVersion(version1, version2));
    
  }

}
