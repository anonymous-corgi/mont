package leetcode.p101to150;

import java.util.*;

public class LeetCode140WordBreakII {

    private interface Method {
        List<String> wordBreak(String s, List<String> wordDict);
    }

    private static final class Memorization implements Method {

        public List<String> wordBreak(String s, List<String> wordDict) {
            List<Integer> lenList = getLenList(wordDict);
            return dfs(s, lenList, new HashSet<>(wordDict), new HashMap<>());
        }

        private List<String> dfs(String s, List<Integer> lenList, Set<String> wordDict,
                                 Map<String, List<String>> cache) {
            if (cache.containsKey(s)) {
                return cache.get(s);
            }
            List<String> result = new ArrayList<>();
            for (int len : lenList) {
                if (len > s.length()) {
                    break;
                }
                String subStr = s.substring(0, len);
                if (wordDict.contains(subStr)) {
                    if (len < s.length()) {
                        List<String> rest = dfs(s.substring(len), lenList, wordDict, cache);
                        for (String restStr : rest) {
                            result.add(subStr + " " + restStr);
                        }
                    } else {
                        result.add(subStr);
                        break;
                    }
                }
            }
            cache.put(s, result);
            return result;
        }

        private List<Integer> getLenList(List<String> wordDict) {
            Set<Integer> lenSet = new HashSet<>();
            for (String word : wordDict) {
                lenSet.add(word.length());
            }
            List<Integer> lenList = new ArrayList<>(lenSet);
            Collections.sort(lenList);
            return lenList;
        }
    }
}
