package leetcode.p451to500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 465. Optimal Account Balancing
 * Hard
 *
 * A group of friends went on holiday and sometimes lent each other money.
 * For example, Alice paid for Bill's lunch for $10.
 * Then later Chris gave Alice $5 for a taxi ride.
 * We can model each transaction as a tuple (x, y, z) which means person x gave person y $z.
 * Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID),
 * the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 *
 * Given a list of transactions between a group of people,
 * return the minimum number of transactions required to settle the debt.
 *
 * Note:
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g.
 * we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 *
 *
 * Example 1:
 * Input:
 * [[0,1,10], [2,0,5]]
 * Output:
 * 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 *
 *
 * Example 2:
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 * Output:
 * 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class LeetCode465OptimalAccountBalancing {

    private interface Method {
        int minTransfers(int[][] transactions);
    }

    public static final class NonRecursive implements Method {

        public int minTransfers(int[][] transactions) {
            if (transactions.length == 0 || transactions[0].length == 0) {
                return 0;
            }
            int[] amount = getUnbalancedAmount(transactions);
            int num = amount.length;
            if (num == 0) {
                return 0;
            }
            int[] dp = new int[1 << num];
            Arrays.fill(dp, Integer.MAX_VALUE >> 1);
            // dp[set] (suppose the byte value of set is 00000101,
            // then dp[set] means the sum of the first(00000001) and the third(00000100) elements.)
            for (int set = 1; set < dp.length; set++) {
                int sum = 0;
                // count means the number of 1-bits or elements.
                // If sum == 0, count also means the max possible value for dp[set].
                int count = 0;
                for (int j = 0; j < num; j++) {
                    if ((1 << j & set) != 0) {
                        sum += amount[j];
                        count++;
                    }
                }
                if (sum == 0) {
                    dp[set] = count - 1;
                    //Search if there two qualified subsets of i
                    //that can make a smaller result.
                    for (int j = 1; j < set; j++) {
                        //((i & j)  == j) means j is the subset of i
                        if ((set & j) == j) {
                            dp[set] = Math.min(dp[set], dp[j] + dp[set - j]);
                        }
                    }
                }
            }
            return dp[dp.length - 1];
        }

        private int[] getUnbalancedAmount(int[][] transactions) {
            Map<Integer, Integer> map = new HashMap<>(transactions.length);
            for (int[] transaction : transactions) {
                map.put(transaction[0], map.getOrDefault(transaction[0], 0) - transaction[2]);
                map.put(transaction[1], map.getOrDefault(transaction[1], 0) + transaction[2]);
            }
            int num = 0;
            int[] account = new int[map.size()];
            for (Integer each : map.values()) {
                if (each != 0) {
                    account[num++] = each;
                }
            }
            return Arrays.copyOf(account, num);
        }
    }

    public static final class Recursive implements Method {

        public int minTransfers(int[][] transactions) {
            if (transactions.length == 0 || transactions[0].length == 0) {
                return 0;
            }
            int[] unbalancedAmount = getUnbalancedAmount(transactions);
            return helper(unbalancedAmount, 0, unbalancedAmount.length, 0);
        }

        private int helper(int[] amount, int start, int end, int num) {
            int res = Integer.MAX_VALUE;
            while (start < end && amount[start] == 0) {
                start++;
            }
            for (int i = start + 1; i < end; i++) {
                // If amount[start] and amount[i] are opposite symbol.
                if ((amount[i] < 0 && amount[start] > 0) || (amount[i] > 0 && amount[start] < 0)) {
                    amount[i] += amount[start];
                    res = Math.min(res, helper(amount, start + 1, end, num + 1));
                    amount[i] -= amount[start];
                }
            }
            return res == Integer.MAX_VALUE ? num : res;
        }

        private int[] getUnbalancedAmount(int[][] transactions) {
            Map<Integer, Integer> map = new HashMap<>(transactions.length);
            for (int[] transaction : transactions) {
                map.put(transaction[0], map.getOrDefault(transaction[0], 0) - transaction[2]);
                map.put(transaction[1], map.getOrDefault(transaction[1], 0) + transaction[2]);
            }
            int num = 0;
            int[] account = new int[map.size()];
            for (Integer each : map.values()) {
                if (each != 0) {
                    account[num++] = each;
                }
            }
            return Arrays.copyOf(account, num);
        }
    }

    private static Method getMethod() {
        return new NonRecursive();
    }

    private void test(int[][] transactions, int expected) {
        int actual = getMethod().minTransfers(transactions);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[][] transactions = {{0, 1, 10}, {2, 0, 5}};
        test(transactions, 2);
    }

    @Test
    public void testcase2() {
        int[][] transactions = {{7, 6, 1}, {4, 6, 59}, {8, 9, 46}, {7, 5, 92}, {14, 13, 92}, {2, 1, 93}, {9, 8, 19}, {14, 13, 72}, {9, 8, 68}, {12, 16, 4}, {14, 15, 74}, {1, 3, 54}, {3, 0, 63}, {5, 7, 24}, {5, 6, 17}, {12, 14, 89}, {8, 10, 65}, {2, 1, 91}, {6, 5, 94}, {1, 3, 85}, {8, 10, 77}, {15, 16, 40}, {11, 9, 39}, {10, 9, 42}, {7, 6, 5}, {9, 10, 74}, {9, 8, 73}, {9, 8, 87}, {9, 8, 56}, {12, 16, 32}, {2, 1, 25}, {10, 11, 92}, {14, 15, 84}, {5, 6, 22}, {2, 1, 69}, {3, 2, 56}, {11, 8, 38}, {3, 1, 3}, {11, 8, 75}, {0, 1, 49}};
        test(transactions, 13);
    }
}
