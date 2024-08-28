package com.anonymouscorgi.karakoram.jiuzhang.c5.graph;

import java.util.ArrayList;
import java.util.List;

public class M33NQueensDFS {
    private StringBuilder dots;
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n < 1) {
            return results;
        }
        
        dots = new StringBuilder(n + 1);
        for (int i = 0; i < n - 1; i++) {
            dots.append('.');
        }
        
        dfs(new ArrayList<Integer>(), n, results);
        return results;
    }
    
    private void dfs(List<Integer> pre, int length, List<List<String>> results) {
        if (pre.size() == length) {
            results.add(intToString(pre));
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
            dfs(pre, length, results);
            pre.remove(pre.size() - 1);
        }
    }
    
    private List<String> intToString(List<Integer> intList) {
        List<String> strList = new ArrayList<>();
        for (int i = 0, len = intList.size(); i < len; i++) {
            int current = intList.get(i);
            dots.insert(current, 'Q');
            strList.add(dots.toString());
            dots.deleteCharAt(current);
        }
        return strList;
    }

}
