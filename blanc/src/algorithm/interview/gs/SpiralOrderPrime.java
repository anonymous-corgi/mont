package algorithm.interview.gs;

import java.util.Arrays;

public class SpiralOrderPrime {

    private boolean[] isPrime = new boolean[1000002];

    private static int[] spiralOrderPrimes(int[][] grid) {
        return null;
    }

    private void computePrime() {
        Arrays.fill(isPrime, 2, isPrime.length, true);
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                int index = i * i;
                while (index < isPrime.length) {
                    isPrime[index] = false;
                    index *= i;
                }
            }
        }
    }
}
