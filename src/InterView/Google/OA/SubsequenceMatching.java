package InterView.Google.OA;

/**
 * Write a function that, given a string S and a string T, 
 * return 1 if it's possible to convert string S into string T 
 * by deleting some(possible zero) characters from string S, 
 * and otherwise returns 0
 * 
 * For example, given S="abcd" and T="abd" the function should return 1. 
 * We can delete 'c' from string S to convert string S into string T. 
 * However, given S="ab" and T="ba" the function should return 0
 * 
 * Assume that:
 * 
 * the length of ('S' , 'T') is within the range [1..1,000]
 * strings S and T consist only of lower-case letters (a-z).
 */
public class SubsequenceMatching {
	
	public boolean match(String S, String T) {
		if (S == null || T == null) {
			return false;
		}
		if (S.length() < T.length()) {
			return false;
		}
		int tIndex = 0;
		int tLen = T.length();
		for (int i = 0, sLen = S.length(); i < sLen; i++) {
			if (S.charAt(i) == T.charAt(tIndex)) {
				tIndex++;
				if (tIndex == tLen) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubsequenceMatching one = new SubsequenceMatching();
		String s1 = "abcd";
		String t1 = "abe";
		System.out.println(one.match(s1, t1));
	}

}
