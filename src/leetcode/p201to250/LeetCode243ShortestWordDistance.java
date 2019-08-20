package leetcode.p201to250;

/**
 * LeetCode 243. Shortest Word Distance
 * Given a list of words and two words word1 and word2,
 * return the shortest distance between these two words in the list.
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
        int shortestDistance(String[] words, String word1, String word2);
    }

    private static class Greedy implements Method {

        @Override
        public int shortestDistance(String[] words, String word1, String word2) {
            int l1 = -1;
            int l2 = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    l1 = i;
                    if (l2 != -1) {
                        min = Math.min(min, l1 - l2);
                    }
                } else if (word2.equals(words[i])) {
                    l2 = i;
                    if (l1 != -1) {
                        min = Math.min(min, l2 - l1);
                    }
                }
            }
            return min;
        }
    }

    private static class Greedy2 implements Method {

        @Override
        public int shortestDistance(String[] words, String word1, String word2) {
            int last = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i]) || word2.equals(words[i])) {
                    if (last != -1 && !words[last].equals(words[i])) {
                        min = Math.min(min, i - last);
                    }
                    last = i;
                }
            }
            return min;
        }
    }
}
