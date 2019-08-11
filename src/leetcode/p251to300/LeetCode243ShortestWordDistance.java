package leetcode.p251to300;

import java.util.List;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * <p>
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * <p>
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class LeetCode243ShortestWordDistance {

    private interface Method {
        int shortestDistance(List<String> words, String word1, String word2);
    }

    private static class Greedy implements Method {

        @Override
        public int shortestDistance(List<String> words, String word1, String word2) {
            int i1 = -1;
            int i2 = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                if (word.equals(word1)) {
                    i1 = i;
                } else if (word.equals(word2)) {
                    i2 = i;
                }
                if (i1 != -1 && i2 != -1) {
                    min = Math.min(min, Math.abs(i1 - i2));
                }
            }
            return min;
        }
    }
}
