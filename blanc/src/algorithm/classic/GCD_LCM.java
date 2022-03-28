package algorithm.classic;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GCD_LCM {

    // Greatest Common Divisor
    private interface GCD {
        int getGCD(int a, int b);
    }

    // Lowest Common Multiple
    private interface LCM {
        int getLCM(int a, int b);
    }

    // It needs a wrapper method to handler corner cases like: a or b is 0;
    private static final class Recursive_GCD implements GCD {

        @Override
        public int getGCD(int a, int b) {
            return b == 0 ? a : getGCD(b, a % b);
        }
    }

    private static final class NonRecursive_GCD implements GCD {

        @Override
        public int getGCD(int a, int b) {
            while (b != 0) {
                int remainder = a % b;
                a = b;
                b = remainder;
            }
            return a;
        }
    }

    private static final class Recursive_LCM implements LCM {

        @Override
        public int getLCM(int a, int b) {
            if (a == 0 || b == 0) {
                return 0;
            }
            return a * b / getGCDMethod().getGCD(a, b);
        }
    }

    private static GCD getGCDMethod() {
        return new Recursive_GCD();
    }

    private static LCM getLCMMethod() {
        return new Recursive_LCM();
    }

    @Test
    public void testGCD() {
        GCD method1 = new Recursive_GCD();
        GCD method2 = new NonRecursive_GCD();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int a = random.nextInt(Integer.MAX_VALUE);
            int b = random.nextInt(Integer.MAX_VALUE);
            int r1 = method1.getGCD(a, b);
            int r2 = method2.getGCD(a, b);
            assertEquals(r1, r2);
        }
    }
}
