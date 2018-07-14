package Jiuzhang.DP;

public class LintCodeM513PerfectSquares {
	
    private int[] source;
    private int[] record;
    public int numSquares(int n) {
        // write your code here
        if (n < 1) {
            return 0;
        }
        int sourceLen = (int) Math.sqrt((double) n) + 1;
        source = new int[sourceLen];
        for (int i = 0; i < sourceLen; i++) {
            source[i] = i * i;
        }
        
        record = new int[n + 1];
        for (int i = 1; i < sourceLen; i++) {
            record[source[i]] = 1;
        }
        for (int i = 2; i <= n; i++) {
            if (record[i] == 1) {
            	continue;
            }
            int sourceIndex = 1;
            while (sourceIndex < sourceLen && source[sourceIndex] < i) {
            	int prevRecord = record[i - source[sourceIndex++]] + 1;
            	if (record[i] == 0 || record[i] > prevRecord) {
            		record[i] =  prevRecord;
            	}
            }
        }
        return record[n];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LintCodeM513PerfectSquares one = new LintCodeM513PerfectSquares();
		int n = 15;
		System.out.println(one.numSquares(n));

	}

}
