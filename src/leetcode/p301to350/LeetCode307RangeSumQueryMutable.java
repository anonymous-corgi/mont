package leetcode.p301to350;

public class LeetCode307RangeSumQueryMutable {

    private static abstract class NumArray {

        abstract public void update(int i, int val);

        abstract public int sumRange(int i, int j);
    }

    private static final class BinaryIndexedTree extends NumArray {

        private final int[] bits;
        private final int[] stub;

        public BinaryIndexedTree(int[] nums) {
            this.bits = new int[nums.length + 1];
            this.stub = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int i, int val) {
            int index = i + 1;
            int diff = val - stub[index];
            stub[index] = val;
            for (; index < bits.length; index += (index & -index)) {
                bits[index] += diff;
            }
        }

        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }

        private int getSum(int i) {
            int index = i + 1;
            int sum = 0;
            for (; index > 0; index -= (index & -index)) {
                sum += bits[index];
            }
            return sum;
        }
    }
}
