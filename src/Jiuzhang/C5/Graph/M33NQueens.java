package Jiuzhang.C5.Graph;

import java.util.ArrayList;
import java.util.List;


public class M33NQueens {
	
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> results = new ArrayList<>();
        if(n < 1){
            return results;
        }
        size = n;
        
        searcher(0, new ArrayList<Integer>(), results);
        return results;
    }
    
    private int size;
    
    private void searcher(int layer,
                          ArrayList<Integer> selected,
                          List<List<String>> results){
        if(layer == size){
//            ArrayList<String> oneSolution = new ArrayList<>();
//            for(int i = 0; i < size; i++){
//                oneSolution.add(printQ(selected.get(i)));
//            }
            results.add(printChessboard(selected));
        }
        boolean[] isAttacked = new boolean[size];
//        for(int i = 0; i < size; i++){
//            isAttacked[i] = false;
//        }
        for(int i = 0; i < selected.size(); i++){
            int current = selected.get(i);
            isAttacked[current] = true;
            if(inBound(current - layer + i)){
                isAttacked[current - layer + i] = true;
            }
            if(inBound(current + layer - i)){
                isAttacked[current + layer - i] = true;
            }
        }
        for(int i = 0; i < size; i++){
            if(!isAttacked[i]){
                selected.add(selected.size(), i);
                searcher(layer + 1, selected, results);
                selected.remove(selected.size() - 1);
            }
        }
        
    }
    
    private boolean inBound(int num){
        if(num < 0 || num >= size){
            return false;
        }
        return true;
    }
//    private String printQ(int num){
//        StringBuilder str = new StringBuilder();
//        for(int i = 0; i < size; i++){
//            if(i == num){
//                str.append('Q');
//            }else{
//                str.append('+');
//            }
//        }
//        return str.toString();
//    }
    
    private List<String> printChessboard(ArrayList<Integer> selected){
        ArrayList<String> oneChessboard = new ArrayList<>();
        StringBuilder str;
        for(int i = 0; i < size; i++){
        	str = new StringBuilder();
        	for(int j = 0; j < size; j++){
        		if(j == selected.get(i)){
        			str.append('Q');
        		}else{
        			str.append('.');
        		}
        	}
        	oneChessboard.add(str.toString());
        }
        return oneChessboard;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M33NQueens one = new  M33NQueens();
		List<List<String>> result = one.solveNQueens(5);
		for(int i = 0; i < result.size(); i++){
			List<String> oneResult = result.get(i);
			for(int j = 0; j < oneResult.size(); j++){
				System.out.println(oneResult.get(j));
			}
			System.out.println();
		}

	}

}
