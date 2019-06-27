package algorithm;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BitOperation {

    /**
     * Set the last (right) 1-bit of a positive integer to 0-bit
     * Examples:
     * 00100101 -> 00100100
     * 00100100 -> 00100000
     * 00100000 -> 00000000
     */
    public static int op1(int num) {
        return num > 0 ? num & (num - 1) : 0;
    }

    /**
     * Set all 1-bits after the last 0-bit to 0-bit
     * Examples:
     * 00100101 -> 00100100
     * 00100100 -> 00100100
     * 00100111 -> 00100000
     */
    public static int op2(int num) {
        return num > 0 ? num & (num + 1) : 0;
    }

    /**
     * Set all 1-bits except the last one to 0-bit
     * Examples:
     * 00100101 -> 00000001
     * 00100100 -> 00000100
     * 00100111 -> 00000001
     *
     * Say x = a1b(in binary) is the number whose last set bit we want to isolate.
     *
     * Here a is some binary sequence of any length of 1’s and 0’s and
     * b is some sequence of any length but of 0’s only.
     * Remember we said we want the LAST set bit, so for that tiny intermediate 1 bit sitting
     * between a and b to be the last set bit, b should be a sequence of 0’s only of length zero or more.
     *
     * -x = 2’s complement of x = (a1b)’ + 1 = a’0b’ + 1 = a’0(0….0)’ + 1 = a’0(1...1) + 1 = a’1(0…0) = a’1b
     */
    public static int op3(int num) {
        return num > 0 ? (num & -num) : 0;
    }

    @Test
    public void op4() {
        int num = new Random().nextInt();
        int n = new Random().nextInt();
        assertEquals(((num ^ n) ^ n), num);
    }

    @Test
    public void op5() {
        int n = new Random().nextInt();
        assertEquals((n & -n) + (n & (n - 1)), n);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int n = new Random().nextInt();
            int total = (n & -n) + (n & (n - 1));
            System.out.println(total == n);
        }
    }
}
