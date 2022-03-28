package algorithm.leetcode.p901to950;

@SuppressWarnings("unused")
public class LeetCode921MinimumAddToMakeParenthesesValid {

    private interface Method {
        int minAddToMakeValid(String S);
    }

    private static final class TwoPointers implements Method {

        @Override
        public int minAddToMakeValid(String S) {
            int left = 0;
            int right = 0;
            for (char c : S.toCharArray()) {
                if (c == '(') {
                    left++;
                } else {
                    if (left > 0) {
                        left--;
                    } else {
                        right++;
                    }
                }
            }
            return left + right;
        }
    }
}
