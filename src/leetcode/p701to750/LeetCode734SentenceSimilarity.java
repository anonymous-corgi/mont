package leetcode.p701to750;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * LeetCode 734. Sentence Similarity
 * <p>
 * Given two sentences words1, words2 (each represented as an array of strings),
 * and a list of similar word pairs pairs, determine if two sentences are similar.
 * <p>
 * For example, “great acting skills” and “fine drama talent” are similar,
 * if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is not transitive.
 * For example, if “great” and “fine” are similar, and “fine” and “good” are similar,
 * “great” and “good” are not necessarily similar.
 * <p>
 * However, similarity is symmetric. For example, “great” and “fine” being similar
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
public class LeetCode734SentenceSimilarity {

    private interface Method {
        boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs);
    }

    private static final class Normal implements Method {

        @Override
        public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
            if (words1.length != words2.length) {
                return false;
            }
            HashMap<String, Set<String>> map = new HashMap<>();
            for (List<String> pair : pairs) {
                String w1 = pair.get(0);
                String w2 = pair.get(1);
                if (!map.containsKey(w1)) {
                    map.put(w1, new HashSet<>());
                    map.get(w1).add(w1);
                }
                if (!map.containsKey(w2)) {
                    map.put(w2, new HashSet<>());
                    map.get(w2).add(w2);
                }
                map.get(w1).add(w2);
                map.get(w2).add(w1);
            }
            boolean areSimilar = true;
            for (int i = 0; i < words1.length && areSimilar; i++) {
                areSimilar = words1[i].equals(words2[i])
                        || (map.containsKey(words2[i]) && map.get(words2[i]).contains(words1[i]));
            }
            return areSimilar;
        }
    }

    private static Method getMethod() {
        return new Normal();
    }

    private void test(String[] words1, String[] words2, List<List<String>> pairs, boolean expected) {
        Method method = getMethod();
        boolean actual = method.areSentencesSimilar(words1, words2, pairs);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        String[] words1 = new String[]{"great", "acting", "skills",};
        String[] words2 = new String[]{"fine", "drama", "talent",};
        List<List<String>> pairs = Arrays.asList(Arrays.asList("great", "fine"), Arrays.asList("acting", "drama"), Arrays.asList("skills", "talent"));
        test(words1, words2, pairs, true);
    }

    @Test
    public void testcase2() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"good",};
        List<List<String>> pairs = Arrays.asList(Arrays.asList("great", "fine"), Arrays.asList("fine", "good"));
        test(words1, words2, pairs, false);
    }

    @Test
    public void testcase3() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"good",};
        List<List<String>> pairs = Collections.singletonList(Arrays.asList("great", "good"));
        test(words1, words2, pairs, true);
        test(words2, words1, pairs, true);
    }

    @Test
    public void testcase4() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"great",};
        List<List<String>> pairs = Collections.emptyList();
        test(words1, words2, pairs, true);
    }

    @Test
    public void testcase5() {
        String[] words1 = new String[]{"great",};
        String[] words2 = new String[]{"doubleplus", "good",};
        List<List<String>> pairs = Collections.singletonList(Arrays.asList("great", "good"));
        test(words1, words2, pairs, false);
    }
}
