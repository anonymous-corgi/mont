package algorithm.interview.visa;

public class MergeStrings {
  
  public String mergeStrings(String a, String b) {
    int iA = 0;
    int iB = 0;
    int lenA = a.length();
    int lenB = b.length();
    StringBuilder sb = new StringBuilder();
    while (iA < lenA && iB < lenB) {
      sb.append(a.charAt(iA++));
      sb.append(b.charAt(iB++));
    }
    if (iA < lenA) {
      sb.append(a.substring(iA));
    }
    if (iB < lenB) {
      sb.append(b.substring(iB));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
