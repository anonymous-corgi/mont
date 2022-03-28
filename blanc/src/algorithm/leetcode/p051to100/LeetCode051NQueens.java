package algorithm.leetcode.p051to100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class LeetCode051NQueens {

    private interface Method {
        List<List<String>> solveNQueens(int n);
    }

    private static class DFS implements Method {

        @Override
        public List<List<String>> solveNQueens(int n) {
            if (n < 4) {
                return n != 1 ? Collections.emptyList()
                        : Collections.singletonList(Collections.singletonList("Q"));
            }
            List<List<String>> results = new ArrayList<>();
            dfs(0, new int[n], results, printRows(n));
            return results;
        }

        private void dfs(int layer, int[] selected, List<List<String>> results, String[] rows) {
            if (layer == selected.length) {
                results.add(printChessboard(selected, rows));
                return;
            }
            boolean[] invalid = new boolean[selected.length];
            for (int i = 0; i < layer; i++) {
                int f = selected[i] - (layer - i);
                int b = selected[i] + (layer - i);
                if (f >= 0) {
                    invalid[f] = true;
                }
                invalid[selected[i]] = true;
                if (b < invalid.length) {
                    invalid[b] = true;
                }
            }
            for (int i = 0; i < invalid.length; i++) {
                if (!invalid[i]) {
                    selected[layer] = i;
                    dfs(layer + 1, selected, results, rows);
                }
            }
        }

        private String[] printRows(int n) {
            String[] rows = new String[n];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.setLength(0);
                for (int j = 0; j < n; j++) {
                    sb.append(j == i ? 'Q' : '.');
                }
                rows[i] = sb.toString();
            }
            return rows;
        }

        private List<String> printChessboard(int[] selected, String[] rows) {
            List<String> chessboard = new ArrayList<>(selected.length);
            for (int i : selected) {
                chessboard.add(rows[i]);
            }
            return chessboard;
        }
    }
}
