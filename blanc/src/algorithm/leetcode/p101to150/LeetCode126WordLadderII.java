package algorithm.leetcode.p101to150;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LeetCode126WordLadderII {

    private interface Method {
        List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList);
    }

    private static class Normal implements Method {

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return Collections.emptyList();
            }
            boolean reachEnd = false;
            List<List<String>> result = new ArrayList<>();
            Set<String> nextWordList = new HashSet<>(wordList);
            Queue<List<String>> taskQueue = new ArrayDeque<>();
            taskQueue.offer(Collections.singletonList(beginWord));
            while (!taskQueue.isEmpty()) {
                wordList = new ArrayList<>(nextWordList);
                for (int i = 0, len = taskQueue.size(); i < len; i++) {
                    List<String> candidate = taskQueue.poll();
                    String begin = candidate.get(candidate.size() - 1);
                    for (String next : wordList) {
                        if (isNextWord(begin, next)) {
                            List<String> newRes = new ArrayList<>(candidate.size() + 1);
                            newRes.addAll(candidate);
                            newRes.add(next);
                            if (endWord.equals(next)) {
                                reachEnd = true;
                                result.add(newRes);
                            } else {
                                taskQueue.offer(newRes);
                            }
                            nextWordList.remove(next);
                        }
                    }
                }
                if (reachEnd) {
                    break;
                }
            }
            return result;
        }

        private boolean isNextWord(String begin, String end) {
            int diff = 0;
            for (int i = begin.length() - 1; i >= 0; i--) {
                if (begin.charAt(i) != end.charAt(i) && ++diff > 1) {
                    break;
                }
            }
            return diff == 1;
        }
    }
}
