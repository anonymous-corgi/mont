package com.anonymouscorgi.karakoram.kb0000;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LintCode 33 · N-Queens
 * <p>
 * The N-queens puzzle is the problem of placing n queens on an n×n chessboard, and the queens can
 * not attack each other(Any two queens can't be in the same row, column, diagonal line). Given an
 * integer n, return all distinct solutions to the N-queens puzzle. Each solution contains a
 * distinct board configuration of the N-queens' placement, where 'Q' and '.' each indicate a queen
 * and an empty space respectively.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1 Output: "Q"
 * <p>
 * Example 2:
 * <p>
 * Input: n = 4 Output: [ // Solution 1 [".Q..", "...Q", "Q...", "..Q." ], // Solution 2 ["..Q.",
 * "Q...", "...Q", ".Q.." ] ]
 */
final class LintCode33NQueens {

  interface Algorithm {

    List<List<String>> solveNQueens(int n);
  }

  static Algorithm DFS = new Algorithm() {

    @Override
    public List<List<String>> solveNQueens(int n) {
      if (n == 1) {
        return List.of(List.of("Q"));
      } else if (n < 4) {
        return List.of();
      }

      List<List<String>> results = new ArrayList<>();
      dfs(n, new ArrayList<>(), results);
      return results;
    }

    private void dfs(int layer, List<Integer> selected, List<List<String>> results) {
      if (selected.size() == layer) {
        results.add(printChessboard(selected));
        return;
      }
      boolean[] isInvalid = new boolean[layer];
      for (int i = 0; i < selected.size(); i++) {
        int pos = selected.get(i);
        int f = pos - selected.size() + i;
        if (f >= 0) {
          isInvalid[f] = true;
        }
        isInvalid[pos] = true;
        int b = pos + selected.size() - i;
        if (b < layer) {
          isInvalid[b] = true;
        }
      }
      for (int i = 0; i < layer; i++) {
        if (!isInvalid[i]) {
          selected.add(selected.size(), i);
          dfs(layer, selected, results);
          selected.remove(selected.size() - 1);
        }
      }
    }

    private List<String> printChessboard(List<Integer> selected) {
      List<String> chessboard = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      for (int pos : selected) {
        for (int j = 0; j < selected.size(); j++) {
          sb.append(j == pos ? 'Q' : '.');
        }
        chessboard.add(sb.toString());
        sb.setLength(0);
      }
      return chessboard;
    }
  };

  static Algorithm BFS = new Algorithm() {

    @Override
    public List<List<String>> solveNQueens(int n) {
      if (n == 1) {
        return List.of(List.of("Q"));
      } else if (n < 4) {
        return List.of();
      }

      List<List<String>> results = new ArrayList<>();

      StringBuilder dots = new StringBuilder(n);
      dots.append(".".repeat(n - 1));

      Queue<List<Integer>> taskList = new LinkedList<>();

      taskList.offer(new ArrayList<>(n + 1));

      while (!taskList.isEmpty()) {
        List<Integer> current = taskList.poll();
        if (current.size() == n) {
          results.add(printChessboard(current, dots));
          continue;
        }
        boolean[] notAllowedArray = getNotAllowedArray(current, n);
        for (int i = 0; i < n; i++) {
          if (notAllowedArray[i]) {
            continue;
          }
          List<Integer> newList = new ArrayList<Integer>(current);
          newList.add(i);
          taskList.add(newList);
        }
      }
      return results;
    }

    private boolean[] getNotAllowedArray(List<Integer> pre, int length) {
      boolean[] notAllowed = new boolean[length];
      for (int level = 1, len = pre.size(); level <= len; level++) {
        int position = pre.get(len - level);
        if (position - level >= 0) {
          notAllowed[position - level] = true;
        }
        if (position + level < length) {
          notAllowed[position + level] = true;
        }
        notAllowed[position] = true;
      }
      return notAllowed;
    }

    private List<String> printChessboard(List<Integer> positionList, StringBuilder dots) {
      List<String> strList = new ArrayList<>();
      for (int position : positionList) {
        dots.insert(position, 'Q');
        strList.add(dots.toString());
        dots.deleteCharAt(position);
      }
      return strList;
    }
  };

  static Algorithm DFS2 = new Algorithm() {

    @Override
    public List<List<String>> solveNQueens(int n) {
      List<List<String>> results = new ArrayList<>();
      if (n < 1) {
        return results;
      }

      StringBuilder dots = new StringBuilder(n + 1);
      dots.append(".".repeat(n - 1));

      dfs(new ArrayList<>(), n, results, dots);
      return results;
    }

    private void dfs(List<Integer> pre, int length, List<List<String>> results,
        StringBuilder dots) {
      if (pre.size() == length) {
        results.add(printChessboard(pre, dots));
        return;
      }
      boolean[] notAllowed = new boolean[length];
      for (int level = 1, len = pre.size(); level <= len; level++) {
        int position = pre.get(len - level);
        if (position - level >= 0) {
          notAllowed[position - level] = true;
        }
        if (position + level < length) {
          notAllowed[position + level] = true;
        }
        notAllowed[position] = true;
      }
      for (int i = 0; i < length; i++) {
        if (notAllowed[i]) {
          continue;
        }
        pre.add(i);
        dfs(pre, length, results, dots);
        pre.remove(pre.size() - 1);
      }
    }

    private List<String> printChessboard(List<Integer> intList, StringBuilder dots) {
      List<String> strList = new ArrayList<>();
      for (int current : intList) {
        dots.insert(current, 'Q');
        strList.add(dots.toString());
        dots.deleteCharAt(current);
      }
      return strList;
    }
  };
}