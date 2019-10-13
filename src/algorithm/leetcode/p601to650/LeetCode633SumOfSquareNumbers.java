package algorithm.leetcode.p601to650;

public class LeetCode633SumOfSquareNumbers {

    private interface Method {
        boolean judgeSquareSum(int c);
    }

    private static class TwoSumMethod implements Method {

        @Override
        public boolean judgeSquareSum(int c) {
            int start = 0;
            int end = (int) Math.sqrt(c);
            while (start <= end) {
                int sum = start * start + end * end;
                if (sum < c) {
                    start++;
                } else if (sum > c) {
                    end--;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
}
