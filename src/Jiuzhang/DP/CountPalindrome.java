package Jiuzhang.DP;

public class CountPalindrome {
	
	public static int count_palindromes(String S) {
		if (S == null || S.length() == 0) {
			return 0;
		}
		int count = 0;
		int len = S.length();
		boolean[][] record = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			record[i][i] = true;
			count++;
		}
		for (int i = 0; i < len - 1; i++) {
			if (S.charAt(i) == S.charAt(i + 1)) {
				record[i][i + 1] = true;
				count++;
			}
		}
		for (int i = len - 3; i >= 0; i--) {
			for (int j = i + 2; j < len; j++) {
				if (record[i + 1][j - 1] && S.charAt(i) == S.charAt(j)) {
					record[i][j] = true;
					if (count == Integer.MAX_VALUE) {
						System.out.println("The count reached Integer.MAX_VALUE");
					}
					count++;
				}
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "wowpurerocks";
		System.out.println(count_palindromes(string));

	}

}
