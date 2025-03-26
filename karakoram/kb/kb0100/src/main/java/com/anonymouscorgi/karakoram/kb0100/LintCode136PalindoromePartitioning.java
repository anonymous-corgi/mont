package com.anonymouscorgi.karakoram.kb0100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface LintCode136PalindoromePartitioning {

  List<List<String>> partition(String s);

  LintCode136PalindoromePartitioning DFS = new LintCode136PalindoromePartitioning() {

    @Override
    public List<List<String>> partition(String s) {
      if (s == null) {
        return Collections.emptyList();
      }

      List<List<String>> results = new ArrayList<>();
      searcher(s, 0, new ArrayList<>(), results);
      return results;
    }

    private void searcher(
        String s,
        int startIndex,
        List<String> resultBuilder,
        List<List<String>> results) {
      if (startIndex >= s.length()) {
        results.add(new ArrayList<>(resultBuilder));
        return;
      }

      for (int i = startIndex; i < s.length(); i++) {
        String sub = s.substring(startIndex, i + 1);
        if (isPalindrome(sub)) {
          resultBuilder.add(sub);
          searcher(s, i + 1, resultBuilder, results);
          resultBuilder.remove(resultBuilder.size() - 1);
        }
      }
    }

    private boolean isPalindrome(String words) {
      int size = words.length();
      for (int i = 0; i < (size / 2); i++) {
        if (words.charAt(i) != words.charAt(size - 1 - i)) {
          return false;
        }
      }
      return true;
    }
  };
}
