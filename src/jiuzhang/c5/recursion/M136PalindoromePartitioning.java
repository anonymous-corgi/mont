package jiuzhang.c5.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class M136PalindoromePartitioning {
	
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if(s == null){
            return results;
        }
        
        searcher(s, 0, new LinkedList<String>(), results);
        return results;
    }
    
    private void searcher(String s,
                          int startIndex,
                          LinkedList<String> combination,
                          List<List<String>> results){
        if(startIndex >= s.length()){
            results.add(combination);
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            String sub = s.substring(startIndex, i + 1);
            if(isPalindrome(sub)){
                LinkedList<String> newCombination = new LinkedList<>(combination);
                newCombination.add(sub);
                searcher(s, i + 1, newCombination, results);
            }
        }
    }
    
    private boolean isPalindrome(String words){
        int size = words.length();
        for(int i = 0; i < (size / 2); i++){
            if(words.charAt(i) != words.charAt(size - 1 - i)){
                return false;
            }
        }
        return true;
    }
    
    
	public static void main(String[] args) {
		M136PalindoromePartitioning one = new  M136PalindoromePartitioning();
		one.partition("ab");
	}
}
