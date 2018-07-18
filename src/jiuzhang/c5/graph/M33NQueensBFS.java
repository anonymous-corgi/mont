package jiuzhang.c5.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M33NQueensBFS {
	
    private StringBuilder dots;
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n < 1) {
            return results;
        }
        
        dots = new StringBuilder(n);
        for (int i = 0; i < n - 1; i++) {
            dots.append('.');
        }
        
        Queue<List<Integer>> taskList = new LinkedList<>();
        
        taskList.offer(new ArrayList<Integer>(n + 1));
        
        while (!taskList.isEmpty()) {
            List<Integer> current = taskList.poll();
            if (current.size() == n) {
                results.add(intToString(current));
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
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M33NQueensBFS one = new M33NQueensBFS();
		List<List<String>> results = one.solveNQueens(4);
		for (int i = 0; i < results.size(); i++) {
			List<String> aResult = results.get(i);
			System.out.println("Solution " + (i + 1) + "/" + (results.size())+ ": ");
			for (int j = 0; j < aResult.size(); j++) {
				System.out.println(aResult.get(j));
			}
		}
		
	}
}
