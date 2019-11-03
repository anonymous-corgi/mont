package algorithm.classic.tree;

public interface BinaryIndexedTree {

    void update(int i, int val);

    int getSum(int i);

    int getRangeSum(int i, int j);
}
