package algorithm.leetcode.p1001to1050;

public class LeetCode1011CapacityToShipPackagesWithinDDays {

    private interface Method {
        int shipWithinDays(int[] weights, int D);
    }

    private static final class BinaryTrial implements Method {

        public int shipWithinDays(int[] weights, int D) {
            int left = weights[0];
            int right = 0;
            for (int weight : weights) {
                left = Math.max(left, weight);
                right += weight;
            }
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (canShip(weights, D, mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }

        private boolean canShip(int[] weights, int D, int guessCapacity) {
            int times = 1;
            int remainder = guessCapacity;
            for (int i = 0; i < weights.length && times <= D; i++) {
                if (remainder < weights[i]) {
                    times++;
                    remainder = guessCapacity;
                }
                remainder -= weights[i];
            }
            return times <= D;
        }
    }
}
