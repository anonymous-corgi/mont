package algorithm.leetcode.p101to150;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LeetCode139WordBreak {

    private interface Method {
        boolean wordBreak(String s, List<String> wordDict);
    }

    private static final class DP implements Method {

        @Override
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return false;
            }
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            List<Integer> lenList = getLengthList(wordDict);
            for (int i = 1; i <= s.length(); i++) {
                for (Integer wLen : lenList) {
                    if (wLen > i) {
                        break;
                    }
                    if (!dp[i - wLen]) {
                        continue;
                    }
                    String word = s.substring(i - wLen, i);
                    dp[i] |= wordDict.contains(word);
                }
            }
            return dp[dp.length - 1];
        }

        private List<Integer> getLengthList(List<String> dict) {
            Set<Integer> set = new HashSet<>();
            for (String s : dict) {
                set.add(s.length());
            }
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            return list;
        }
    }

    private Method getMethod() {
        return new DP();
    }

    @Test
    public void testcase1() {
        String s = "abcd";
        List<String> wordDict = Arrays.asList("a", "abc", "b", "cd");
        test(s, wordDict, true);
    }

    @Test
    public void testcase2() {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        test(s, wordDict, true);
    }

    @Test
    public void testcase3() {
        String s = "applepenappla";
        List<String> wordDict = Arrays.asList("apple", "pen");
        test(s, wordDict, false);
    }

    private void test(String s, List<String> wordDict, boolean expected) {
        Method method = getMethod();
        boolean actual = method.wordBreak(s, wordDict);
        assertThat(actual, is(expected));
    }
}
