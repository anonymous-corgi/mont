package jiuzhang.c5.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class M33NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n < 4) {
            if (n == 1) {
                results.add(Arrays.asList("Q"));
            }
            return results;
        }
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

    public static void main(String[] args) {
        M33NQueens one = new M33NQueens();
        List<List<String>> result = one.solveNQueens(4);
        for (int i = 0; i < result.size(); i++) {
            List<String> oneResult = result.get(i);
            for (int j = 0; j < oneResult.size(); j++) {
                System.out.println(oneResult.get(j));
            }
            System.out.println();
        }

    }
}
