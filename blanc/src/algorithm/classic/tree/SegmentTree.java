package algorithm.classic.tree;

public class SegmentTree {

    private static final class Node {
        int start;
        int end;
        int sum;
        Node left;
        Node right;

        Node(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        Node(int start, int end, Node l, Node r) {
            this.start = start;
            this.end = end;
            this.left = l;
            this.right = r;
            if (l != null) {
                this.sum += l.sum;
            }
            if (r != null) {
                this.sum += r.sum;
            }
        }
    }

    private int size;
    private Node root;

    public SegmentTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        size = nums.length;
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        if (i < 0 || j >= size) {
            return Integer.MIN_VALUE;
        }
        return sumRange(root, i, j);
    }

    private Node buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new Node(start, end, nums[start]);
        } else {
            int mid = start + (end - start) / 2;
            Node l = buildTree(nums, start, mid);
            Node r = buildTree(nums, mid + 1, end);
            return new Node(start, end, l, r);
        }
    }

    private void update(Node root, int i, int val) {
        if (i < root.start || i > root.end) {
            return;
        }
        if (root.start == root.end && root.start == i) {
            root.sum = val;
            return;
        }
        if (i <= root.left.end) {
            update(root.left, i, val);
        } else {
            update(root.right, i, val);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    private int sumRange(Node root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.sum;
        }
        if (start >= root.right.start) {
            return sumRange(root.right, start, end);
        } else if (end <= root.left.end) {
            return sumRange(root.left, start, end);
        } else {
            int lSum = sumRange(root.left, start, root.left.end);
            int rSum = sumRange(root.right, root.right.start, end);
            return lSum + rSum;
        }
    }
}
