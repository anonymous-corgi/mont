package leetcode.p051to100;

public class LeetCode076MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        int start = 0;
        int minStart = 0;
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
                    minStart = start;
                }
                if (map[s.charAt(start++)]++ == 0) {
                    deficiency++;
                }
            }
        }
        return minLen <= s.length() ? s.substring(minStart, minStart + minLen) : "";
    }
}
