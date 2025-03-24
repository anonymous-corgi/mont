package com.anonymouscorgi.karakoram.kb0000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface LintCode017LetterCombinationsOfPhoneNumber {

  List<String> letterCombinations(String digits);

  String[] DIAL_MAP = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  LintCode017LetterCombinationsOfPhoneNumber DFS =
      new LintCode017LetterCombinationsOfPhoneNumber() {

        @Override
        public List<String> letterCombinations(String digits) {
          if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
          }
          List<String> results = new ArrayList<>();
          int[] convertedDigits = new int[digits.length()];
          for (int i = 0, len = digits.length(); i < len; i++) {
            convertedDigits[i] = Integer.parseInt(digits.substring(i, i + 1));
          }

          dfs(convertedDigits, 0, new StringBuffer(), results);
          return results;
        }

        private void dfs(
            int[] digits, int digitIndex, StringBuffer resultBuilder, List<String> results) {
          if (digitIndex == digits.length) {
            results.add(resultBuilder.toString());
            return;
          }

          String dial = DIAL_MAP[digits[digitIndex]];
          for (int i = 0; i < dial.length(); i++) {
            resultBuilder.append(dial.charAt(i));
            dfs(digits, digitIndex + 1, resultBuilder, results);
            resultBuilder.deleteCharAt(resultBuilder.length() - 1);
          }
        }
      };
}
