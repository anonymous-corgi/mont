package algorithm.leetcode.p651to700;

@SuppressWarnings("unused")
public class LeetCode680ValidPalindromeII {

    private interface Method {
        boolean validPalindrome(String s);
    }

    private static class Normal implements Method {

        @Override
        public boolean validPalindrome(String s) {
            return validPalindrome(s, 0, s.length() - 1, 0);
        }

        private boolean validPalindrome(String s, int l, int r, int miss) {
            if (miss > 1) {
                return false;
            }
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    return validPalindrome(s, l, r - 1, miss + 1)
                            || validPalindrome(s, l + 1, r, miss + 1);
                }
                l++;
                r--;
            }
            return true;
        }
    }
}
