package algorithm.leetcode.p051to100;

public class LeetCode076MinimumWindowSubstring {

    private interface Method {
        String minWindow(String s, String t);
    }

    private static class Greedy implements  Method {

        @Override
        public String minWindow(String s, String t) {
            if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
                return "";
            }
            int start = 0;
            int minStart = 0;
            int minLen = Integer.MAX_VALUE;
            int deficiency = t.length();
            int[] map = new int[128];
            for (char c : t.toCharArray()) {
                map[c]++;
            }
            for (int cursor = 0; cursor < s.length(); cursor++) {
                if (map[s.charAt(cursor)]-- > 0) {
                    deficiency--;
                }
                while (deficiency == 0) {
                    if ((cursor - start + 1) < minLen) {
                        minLen = cursor - start + 1;
                        minStart = start;
                    }
                    if (map[s.charAt(start++)]++ >= 0) {
                        deficiency++;
                    }
                }
            }
            return minLen <= s.length() ? s.substring(minStart, minStart + minLen) : "";
        }
    }
}
