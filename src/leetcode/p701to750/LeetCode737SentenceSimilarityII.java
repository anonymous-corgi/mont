package leetcode.p701to750;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * LeetCode 737. Sentence Similarity II
 * <p>
 * Given two sentences words1, words2 (each represented as an array of strings),
 * and a list of similar word pairs pairs, determine if two sentences are similar.
 * <p>
 * For example, “great acting skills” and “fine drama talent” are similar,
 * if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is transitive.
 * For example, if “great” and “good” are similar, and “fine” and “good” are similar,
 * then “great” and “fine” are similar.
 * <p>
 * Similarity is also symmetric. For example, “great” and “fine” being similar
 * is the same as “fine” and “great” being similar.
 * <p>
 * Also, a word is always similar with itself.
 * For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar,
 * even though there are no specified similar word pairs.
 * <p>
 * Finally, sentences can only be similar if they have the same number of words.
 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Note:
 * <p>
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class LeetCode737SentenceSimilarityII {

    private interface Method {
        boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs);
    }

    private static final class UnionFind implements Method {

        @Override
        public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
            if (words1 == null || words2 == null
                    || words1.length == 0 || words1.length != words2.length) {
                return false;
            }
            Map<String, String> ufp = new HashMap<>();
            for (String[] pair : pairs) {
                ufp.putIfAbsent(pair[0], pair[0]);
                ufp.putIfAbsent(pair[1], pair[1]);
                connect(ufp, pair[0], pair[1]);
            }

            boolean areSimilar = true;
            for (int i = 0; i < words1.length && areSimilar; i++) {
                areSimilar = words1[i].equals(words2[i])
                        || isConnected(ufp, words1[i], words2[i]);
            }
            return areSimilar;
        }

        private String find(Map<String, String> ufp, String str) {
            String strp = ufp.get(str);
            if (str.equals(strp)) {
                return str;
            }
            String strpp = find(ufp, strp);
            ufp.put(str, strpp);
            return strpp;
        }

        private void connect(Map<String, String> ufp, String a, String b) {
            String ap = find(ufp, a);
            String bp = find(ufp, b);
            if (!ap.equals(bp)) {
                ufp.put(ap, bp);
            }
        }

        private boolean isConnected(Map<String, String> ufp, String a, String b) {
            String ap = find(ufp, a);
            String bp = find(ufp, b);
            return ap.equals(bp);
        }
    }

    private static Method getMethod() {
        return new UnionFind();
    }

    private void test(String[] words1, String[] words2, String[][] pairs, boolean expected) {
        Method method = getMethod();
        boolean actual = method.areSentencesSimilar(words1, words2, pairs);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        String[] words1 = new String[]{"great", "acting", "skills",};
        String[] words2 = new String[]{"fine", "drama", "talent",};
        String[][] pairs = new String[][]{{"great", "fine"}, {"acting", "drama"}, {"skills", "talent"},};
        test(words1, words2, pairs, true);
    }

    @Test
    public void testcase2() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"good",};
        String[][] pairs = new String[][]{{"great", "fine"}, {"fine", "good"},};
        test(words1, words2, pairs, true);
    }

    @Test
    public void testcase3() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"good",};
        String[][] pairs = new String[][]{{"great", "good"},};
        test(words1, words2, pairs, true);
        test(words2, words1, pairs, true);
    }

    @Test
    public void testcase4() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"great",};
        String[][] pairs = new String[][]{};
        test(words1, words2, pairs, true);
    }

    @Test
    public void testcase5() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"doubleplus", "good",};
        String[][] pairs = new String[][]{{"great", "good"},};
        test(words1, words2, pairs, false);
    }
}
