package algorithm.classic;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Prime {

    private interface Method {
        // n is inclusive (1, n]
        int[] getPrime(int n);
    }

    private static class O_N implements Method {

        @Override
        public int[] getPrime(int n) {
            // Count the number of prime within n.
            int primeCount = 0;
            boolean[] isEliminated = new boolean[n + 1];
            for (int cursor = 2; cursor < isEliminated.length; cursor++) {
                if (isEliminated[cursor]) {
                    continue;
                }
                primeCount++;
                // Avoid (cursor * cursor) overflows Integer.MAX_VALUE
                long eliminationIndex = (long) cursor * cursor;
                // Eliminate all the numbers that has a factor as cursor.
                while (eliminationIndex < isEliminated.length) {
                    isEliminated[(int) eliminationIndex] = true;
                    eliminationIndex += cursor;
                }
            }

            int[] result = new int[primeCount];
            int resultIndex = 0;
            for (int i = 2; i < isEliminated.length; i++) {
                if (!isEliminated[i]) {
                    result[resultIndex] = i;
                    resultIndex++;
                }
            }
            return result;
        }
    }

    private static Method getMethod() {
        return new O_N();
    }

    @Test
    public void testcase1() {
        test(31, 11);
    }

    @Test
    public void testcase2() {
        test(499979, 41538);
    }

    private void test(int n, int expected) {
        int actual = getMethod().getPrime(n).length;
        assertThat(actual, equalTo(expected));
    }

    private static void testSpeed(int n) {
        long start = System.nanoTime();
        int[] primes = getMethod().getPrime(n);
        long end = System.nanoTime();
        System.out.println("The time consumption is: " + (end - start) + "ns.");
        System.out.println(Arrays.toString(primes));
    }

    public static void main(String[] args) {
        testSpeed(1);
    }
}
