package leetcode.p201to250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 244. Shortest Word Distance II
 * This is a follow up of Shortest Word Distance.
 * The only difference is now you are given the list of words
 * and your method will be called repeatedly many times with different parameters.
 * How would you optimize it?
 * <p>
 * Design a class which receives a list of words in the constructor,
 * and implements a method that takes two words word1 and word2
 * and return the shortest distance between these two words in the list.
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
@SuppressWarnings("unused")
public class LeetCode244ShortestWordDistanceII {

    private interface Method {
        int shortest(String word1, String word2);
    }

    private static class Greedy implements Method {

        private Map<String, List<Integer>> cache = new HashMap<>();

        Greedy(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                List<Integer> list = cache.computeIfAbsent(word, k -> new ArrayList<>());
                list.add(i);
            }
        }

        @Override
        public int shortest(String word1, String word2) {
            List<Integer> list1 = cache.get(word1);
            List<Integer> list2 = cache.get(word2);
            int min = Integer.MAX_VALUE;
            int i1 = 0;
            int i2 = 0;
            while (i1 < list1.size() && i2 < list2.size()) {
                int c1 = list1.get(i1);
                int c2 = list2.get(i2);
                min = Math.min(min, Math.abs(c1 - c2));
                if (c1 < c2) {
                    i1++;
                } else {
                    i2++;
                }
            }
            return min;
        }
    }
}
