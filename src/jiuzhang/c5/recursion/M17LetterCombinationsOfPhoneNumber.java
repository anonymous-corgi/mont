package jiuzhang.c5.recursion;

import java.util.ArrayList;
import java.util.List;

public class M17LetterCombinationsOfPhoneNumber {
	
    private final String[] stubs = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        List<Integer> source = new ArrayList<>();
        if (digits == null ) {
            return results;
        }
        for (int i = 0, len = digits.length(); i < len; i++) {
            source.add(Integer.parseInt(digits.substring(i, i + 1)));
        }
        dfs(source, 0, new StringBuffer(), results);
        return results;
    }
    
    private void dfs(List<Integer> digits, int start, StringBuffer subResult, List<String> results) {
        if (start == digits.size()) {
            results.add(subResult.toString());
            return;
        }
        String stub = stubs[digits.get(start)];
        for (int i = 0; i < stub.length(); i++) {
            subResult.append(stub.charAt(i));
            dfs(digits, start + 1, subResult, results);
            subResult.deleteCharAt(subResult.length() - 1);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M17LetterCombinationsOfPhoneNumber one = new M17LetterCombinationsOfPhoneNumber();
		String digits = "23";
		System.out.println(one.letterCombinations(digits));

	}

}
