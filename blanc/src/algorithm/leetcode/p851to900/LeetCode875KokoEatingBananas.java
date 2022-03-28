package algorithm.leetcode.p851to900;

public class LeetCode875KokoEatingBananas {

    private interface Method {
        int minEatingSpeed(int[] piles, int H);
    }

    private static final class BinaryTrial implements Method {

        public int minEatingSpeed(int[] piles, int H) {
            int begin = 1;
            int end = 0;
            for (int pile : piles) {
                end = Math.max(end, pile);
            }

            while (begin < end) {
                int mid = begin + (end - begin) / 2;
                if (canFinish(piles, mid, H)) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            return end;
        }

        private boolean canFinish(int[] piles, int k, int h) {
            int t = 0;
            for (int pile : piles) {
                t += (pile % k == 0 ? pile / k : pile / k + 1);
            }
            return t <= h;
        }
    }
}
