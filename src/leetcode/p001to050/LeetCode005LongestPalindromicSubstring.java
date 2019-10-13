package leetcode.p001to050;

public class LeetCode005LongestPalindromicSubstring {

    private interface Method {
        String longestPalindrome(String s);
    }

    private static class ManachersAlgorithm implements Method {

        @Override
        public String longestPalindrome(String s) {
            return algorithm.classic.ManachersAlgorithm.longestPalindrome(s);
        }
    }
}
