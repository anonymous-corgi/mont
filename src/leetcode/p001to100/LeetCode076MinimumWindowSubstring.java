package leetcode.p001to100;

public class H76MinimumWindowSubstring {
	
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0) {
        		return "";
        }
        if (t == null || t.length() == 0) {
        		return "";
        }
        int start = 0;
        int end = 0;
        int head = 0;
        int deficiency = t.length();
        int len = s.length();
        int minLen = s.length();
        int[] map = new int[128];
        for (int i = 0; i < len; i++) {
        		map[t.charAt(i)]++;
        }
        for (; end < len; end++) {
        		if (map[s.charAt(end)]-- > 0) {
        			deficiency--;
        		}

        		while (deficiency == 0) {        		
        			if ((end - start) < minLen) {
	        			minLen = end - start;
	        			head = start;
	        		}
        			if (map[s.charAt(start++)]++ == 0) {
        				deficiency++;
        			}
        		}
        }
        return minLen < s.length() ? s.substring(head, head + minLen) : "";
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
