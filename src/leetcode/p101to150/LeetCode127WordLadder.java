package leetcode.p101to150;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class LeetCode127WordLadder {

    private interface Method {
        int ladderLength(String beginWord, String endWord, List<String> wordList);
    }

    private static class Normal implements Method {

        @Override
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            int res = 0;
            Queue<String> taskQueue = new ArrayDeque<>();
            taskQueue.offer(beginWord);
            while (!taskQueue.isEmpty()) {
                res++;
                for (int i = 0, len = taskQueue.size(); i < len; i++) {
                    String begin = taskQueue.poll();
                    Iterator<String> iterator = wordList.iterator();
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        if (isNextWord(begin, next)) {
                            if (endWord.equals(next)) {
                                return res + 1;
                            } else {
                                taskQueue.offer(next);
                                iterator.remove();
                            }
                        }
                    }
                }
            }
            return 0;
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
