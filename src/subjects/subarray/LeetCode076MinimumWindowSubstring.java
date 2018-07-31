package subjects.subarray;

public class LeetCode076MinimumWindowSubstring {
	
  public String minWindow(String s, String t) {
    if (s == null || s.length() == 0) {
      return "";
    }
    if (t == null || t.length() == 0) {
      return "";
    }
    int start = 0;
    int minStrHead = 0;
    int minLen = Integer.MAX_VALUE;
    int deficiency = t.length();
    int[] map = new int[128];
    for (int i = 0, tLen = t.length(); i < tLen; i++) {
      map[t.charAt(i)]++;
    }
    for (int end = 0, sLen = s.length(); end < sLen; end++) {
      if (map[s.charAt(end)]-- > 0) {
        deficiency--;
      }
      while (deficiency == 0) {               
        if ((end - start + 1) < minLen) {
          minLen = end - start + 1;
          minStrHead = start;
        }
        if (map[s.charAt(start++)]++ == 0) {
          deficiency++;
        }
      }
    }
    return minLen <= s.length() ? s.substring(minStrHead, minStrHead + minLen) : "";
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
  }

}
