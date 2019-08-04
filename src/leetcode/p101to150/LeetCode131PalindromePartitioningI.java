package leetcode.p101to150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Print;

/**
 * LeetCode 131. Palindrome Partitioning
 * Medium
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * Input: "aab"
 * Output:
 * [
 *  ["aa","b"],
 *  ["a","a","b"]
 * ]
 */
public class LeetCode131PalindromePartitioningI {

    private interface Method {
        List<List<String>> partition(String s);
    }

    private static class Manachers_method implements Method {

        private Map<Integer, List<List<String>>> cache;

        @Override
        public List<List<String>> partition(String s) {
            char[] chs = s.toCharArray();
            int[] p = getP(addBoundary(chs));
            cache = new HashMap<>();
            cache.put(chs.length, Collections.singletonList(Collections.emptyList()));
            return dfs(chs, p, 0);
        }

        private List<List<String>> dfs(char[] chs, int[] p, int start) {
            if (cache.containsKey(start)) {
                return cache.get(start);
            }
            List<List<String>> results = new ArrayList<>();
            // We try to pick the substring [start, end] end excluding.
            for (int end = start + 1; end <= chs.length; end++) {
                // (start + end) is center index of the substring
                // (end - start) is the length of the substring.
                // p[start + end] >= end - start means if the substring is a palindrome
                // with length longer than (end - start)
                if (p[start + end] >= end - start) {
                    for (List<String> followings : dfs(chs, p, end)) {
                        List<String> result = new ArrayList<>(followings.size() + 1);
                        String str = String.valueOf(chs, start, end - start);
                        result.add(str);
                        result.addAll(followings);
                        results.add(result);
                    }
                }
            }
            return results;
        }

        private int[] getP(char[] chs) {
            int[] p = new int[chs.length];
            int c = 0, m = 0;
            int l = 0, r = 0;
            for (int i = 1; i < p.length; i++) {
                if (i >= m) {
                    r = i + 1;
                    l = i - 1;
                } else {
                    int im = c * 2 - i;
                    if (i + p[im] >= m) {
                        p[i] = m - i;
                        r = m + 1;
                        l = i * 2 - r;
                    } else {
                        p[i] = p[im];
                        l = -1;
                    }
                }

                while (l >= 0 && r < chs.length && chs[l] == chs[r]) {
                    p[i]++;
                    l--;
                    r++;
                }

                if (i + p[i] > m) {
                    c = i;
                    m = i + p[i];
                }
            }
            return p;
        }

        private char[] addBoundary(char[] chs) {
            char[] res = new char[chs.length * 2 + 1];
            res[0] = '*';
            for (int i = 1; i < res.length; i += 2) {
                res[i] = chs[i / 2];
                res[i + 1] = '*';
            }
            return res;
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        LeetCode131PalindromePartitioningI.Manachers_method one =
                new LeetCode131PalindromePartitioningI.Manachers_method();
        Print.printListList(one.partition(s));
    }
}
