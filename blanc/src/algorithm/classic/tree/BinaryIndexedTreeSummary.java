package algorithm.classic.tree;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BinaryIndexedTreeSummary {

    interface BinaryIndexedTree {

        void update(int i, int val);

        int getSum(int i);

        int getRangeSum(int i, int j);
    }

    /**
     * Fenwick (Binary Indexed) Trees
     * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/fenwick-binary-indexed-trees/tutorial/
     */
    private static final class BinaryIndexedTreeImpl implements BinaryIndexedTree {

        /**
         * bit[0b0001_0100] = sum of (stub[0b0001_0001], stub[0b0001_0010], stub[0b0001_0011], stub[0b0001_0100])
         * The sum is of (i & -i) numbers.
         * <p>
         * stub[0b0001_0010] will be added to (bit[0b0001_0010], bit[0b0001_0100], bit[0b0001_1000], ...)
         */
        private final int[] bit;
        private final int[] stub;

        BinaryIndexedTreeImpl(int[] nums) {
            this.bit = new int[nums.length + 1];
            this.stub = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                update(i, nums[i]);
            }
        }

        @Override
        public void update(int i, int val) {
            int index = i + 1;
            int diff = val - stub[index];
            stub[index] = val;
            for (; index < bit.length; index += (index & -index)) {
                bit[index] += diff;
            }
        }

        @Override
        public int getSum(int i) {
            int sum = 0;
            int index = i + 1;
            for (; index > 0; index -= (index & -index)) {
                sum += bit[index];
            }
            return sum;
        }

        @Override
        public int getRangeSum(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }
    }

    private static BinaryIndexedTree getImpl(int[] nums) {
        return new BinaryIndexedTreeImpl(nums);
    }

    @Test
    public void verify() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        BinaryIndexedTree impl = getImpl(nums);

        assertThat(impl.getSum(0), equalTo(1));
        assertThat(impl.getSum(2), equalTo(6));
        assertThat(impl.getSum(10), equalTo(66));
        assertThat(impl.getSum(16), equalTo(153));
    }
}
