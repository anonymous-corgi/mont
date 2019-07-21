package jiuzhang.dp.coinschange;

/**
 * LeetCode 279. Perfect Squares
 * Medium
 * <p>
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * <p>
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class LeetCode279PerfectSquares {

    public int numSquares(int n) {
        if (n < 1) {
            return 0;
        }
        int sourceLen = (int) Math.sqrt((double) n) + 1;
        int[] record = new int[n + 1];
        int[] source = new int[sourceLen];
        for (int i = 0; i < sourceLen; i++) {
            source[i] = i * i;
        }
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
                    record[i] = prevRecord;
                }
            }
        }
        return record[n];
    }
}
