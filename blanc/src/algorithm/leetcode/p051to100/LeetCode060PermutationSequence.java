package algorithm.leetcode.p051to100;

import java.util.LinkedList;

public class LeetCode060PermutationSequence {

    private interface Method {
        String getPermutation(int n, int k);
    }

    private static class NonRecursive implements Method {

        @Override
        public String getPermutation(int n, int k) {
            int pos = 1;
            StringBuilder sb = new StringBuilder();
            LinkedList<Integer> source = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                pos *= i;
                source.add(i);
            }
            k--;
            for (int digitIndex = n; digitIndex > 0; digitIndex--) {
                pos /= digitIndex;
                int i = k / pos;
                k %= pos;
                sb.append(source.remove(i));
            }
            return sb.toString();
        }
    }
}
