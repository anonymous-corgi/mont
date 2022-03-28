package algorithm.leetcode.p601to650;

public class LeetCode605CanPlaceFlowers {

    private interface Method {
        boolean canPlaceFlowers(int[] flowerbed, int n);
    }

    private static final class On implements Method {

        @Override
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int last = -2;
            for (int i = 0; i < flowerbed.length && n > 0; i++) {
                if (flowerbed[i] == 1) {
                    last = i;
                } else if (i - last > 1 && (i + 1 == flowerbed.length || flowerbed[i + 1] == 0)) {
                    n--;
                    last = i;
                }
            }
            return n <= 0;
        }
    }
}
