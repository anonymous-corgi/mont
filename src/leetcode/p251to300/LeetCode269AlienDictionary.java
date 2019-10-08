package leetcode.p251to300;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 269. Alien Dictionary
 * Hard
 * 
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary,
 * where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 * 
 * 
 * Example 1:
 * Input:
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * Output: "wertf"
 * 
 * 
 * Example 2:
 * Input:
 * [
 * "z",
 * "x"
 * ]
 * Output: "zx"
 * 
 * 
 * Example 3:
 * Input:
 * [
 * "z",
 * "x",
 * "z"
 * ]
 * Output: ""
 * 
 * Explanation: The order is invalid, so return "".
 * Note:
 * 
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
@RunWith(Parameterized.class)
public class LeetCode269AlienDictionary {
    private final String[] words;
    private final String expected;

    public LeetCode269AlienDictionary(String[] words, String expected) {
        this.words = words;
        this.expected = expected;
    }

    private interface Method {
        String alienOrder(String[] words);
    }

    private static final class BFS implements Method {

        public String alienOrder(String[] words) {
            Map<Character, Set<Character>> successors = new HashMap<>();
            Map<Character, Integer> inDegrees = new HashMap<>();
            Queue<Character> taskQueue = new PriorityQueue<>();
            StringBuilder res = new StringBuilder();

            // Collect all the Characters shown.
            for (String word : words) {
                for (char ch : word.toCharArray()) {
                    inDegrees.putIfAbsent(ch, 0);
                    successors.putIfAbsent(ch, new HashSet<>());
                }
            }

            // Build connection: successors and inDegrees.
            for (int i = 1; i < words.length; i++) {
                String f = words[i - 1];
                String b = words[i];
                for (int j = 0, jLen = Math.min(f.length(), b.length()); j < jLen; j++) {
                    if (f.charAt(j) != b.charAt(j)) {
                        if (successors.get(b.charAt(j)).contains(f.charAt(j))) {
                            return "";
                        }
                        // Important: Avoid Duplication.
                        if (successors.get(f.charAt(j)).add(b.charAt(j))) {
                            inDegrees.compute(b.charAt(j), (k, v) -> v == null ? 1 : v + 1);
                        }
                        break;
                    }
                }
            }

            // Find initial characters.
            for (Map.Entry<Character, Integer> inDegree : inDegrees.entrySet()) {
                if (inDegree.getValue() == 0) {
                    taskQueue.offer(inDegree.getKey());
                }
            }

            while (!taskQueue.isEmpty()) {
                Character cursor = taskQueue.poll();
                res.append(cursor);
                for (Character successor : successors.get(cursor)) {
                    int inDegree = inDegrees.compute(successor, (k, v) -> v - 1);
                    if (inDegree == 0) {
                        taskQueue.offer(successor);
                    }
                }
                successors.remove(cursor);
            }

            // Important: Make sure that all characters are processed.
            return successors.isEmpty() ? res.toString() : "";
        }
    }

    private static Method getMethod() {
        return new BFS();
    }

    private void test(String[] words, String expected) {
        String actual = getMethod().alienOrder(words);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase() {
        test(words, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new String[]{"wrt", "wrf", "er", "ett", "rftt"}, "wertf"},
                {new String[]{"zy", "zx"}, "yxz"},
                {new String[]{"ab", "adc"}, "abcd"},
                {new String[]{"za", "zb", "ca", "cb"}, "abzc"},
                {new String[]{"bsusz", "rhn", "gfbrwec", "kuw", "qvpxbexnhx", "gnp", "laxutz", "qzxccww"}, ""},
        };
    }
}
