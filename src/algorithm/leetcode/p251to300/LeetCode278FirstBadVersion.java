package algorithm.leetcode.p251to300;

@SuppressWarnings("unused")
public class LeetCode278FirstBadVersion {

    private interface Method {
        int firstBadVersion(int n);
    }

    private static class VersionControl {

        final int stub = 1;

        boolean isBadVersion(int version) {
            return version >= stub;
        }
    }

    private static class Recursive extends VersionControl implements Method {

        @Override
        public int firstBadVersion(int n) {
            return findFirstBadVersion(1, n, -1);
        }

        private int findFirstBadVersion(int head, int end, int lastResult) {
            if (head > end) {
                return lastResult;
            }

            int mid = head + (end - head) / 2;
            if (isBadVersion(mid)) {
                return findFirstBadVersion(head, mid - 1, mid);
            } else {
                return findFirstBadVersion(mid + 1, end, lastResult);
            }
        }
    }
}
