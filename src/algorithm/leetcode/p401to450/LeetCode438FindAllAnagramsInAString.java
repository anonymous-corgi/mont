package algorithm.leetcode.p401to450;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode438FindAllAnagramsInAString {

    private interface Method {
        List<Integer> findAnagrams(String s, String p);
    }

    private static class Greedy implements Method {

        public List<Integer> findAnagrams(String s, String p) {
            if (s == null || p == null || s.isEmpty() || p.isEmpty()) {
                return Collections.emptyList();
            }

            int deficiency = 0;
            int[] stub = new int[128];
            List<Integer> res = new ArrayList<>();
            for (char c : p.toCharArray()) {
                stub[c]++;
                deficiency++;
            }
            for (int i = 0; i < s.length(); i++) {
                if (stub[s.charAt(i)]-- > 0) {
                    deficiency--;
                }
                int head = i - p.length() + 1;
                if (head >= 0) {
                    if (deficiency == 0) {
                        res.add(head);
                    }
                    if (stub[s.charAt(head)]++ >= 0) {
                        deficiency++;
                    }
                }
            }
            return res;
        }
    }
}
