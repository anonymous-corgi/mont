package algorithm.leetcode.p201to250;

public class LeetCode204CountPrimes {

    private interface Method {
        int countPrimes(int n);
    }

    private static class O_N implements Method {

        @Override
        public int countPrimes(int n) {
            // n is exclusive (0, n), not (0, n]
            if (n <= 2) {
                return 0;
            }
            int primeCount = 0;
            boolean[] isEliminated = new boolean[n];
            for (int cursor = 2; cursor < isEliminated.length; cursor++) {
                if (isEliminated[cursor]) {
                    continue;
                }
                primeCount++;
                long eliminationIndex = (long) cursor * cursor;
                while (eliminationIndex < isEliminated.length) {
                    isEliminated[(int) eliminationIndex] = true;
                    eliminationIndex += cursor;
                }
            }
            return primeCount;
        }
    }
}
