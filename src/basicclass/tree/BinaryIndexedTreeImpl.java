package basicclass.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Fenwick (Binary Indexed) Trees
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/fenwick-binary-indexed-trees/tutorial/
 */
public class BinaryIndexedTreeImpl implements BinaryIndexedTree {

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
        int res = 0;
        int index = i + 1;
        for (; index > 0; index -= (index & -index)) {
            res += bit[index];
        }
        return res;
    }

    private static BinaryIndexedTree getImpl(int[] nums) {
        return new BinaryIndexedTreeImpl(nums);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        BinaryIndexedTree impl = getImpl(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(impl.getSum(i));
        }
    }

    @Override
    public int getRangeSum(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    @Test
    public void verify() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        BinaryIndexedTree impl = getImpl(nums);

        Assert.assertEquals(1, impl.getSum(0));
        Assert.assertEquals(6, impl.getSum(2));
        Assert.assertEquals(66, impl.getSum(10));
        Assert.assertEquals(153, impl.getSum(16));
    }
}
