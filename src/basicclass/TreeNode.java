package basicclass;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode implements Iterable<TreeNode> {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        int nullTailIndex = 0;
        StringBuilder sb = new StringBuilder().append('[');
        Queue<TreeNode> taskQueue = new LinkedList<>();
        taskQueue.offer(root);
        while (!taskQueue.isEmpty()) {
            TreeNode cursor = taskQueue.poll();
            if (cursor != null) {
                nullTailIndex = 0;
                sb.append(cursor.val).append(',');
                taskQueue.offer(cursor.left);
                taskQueue.offer(cursor.right);
            } else {
                sb.append("null,");
                nullTailIndex++;
            }
        }
        sb.delete(sb.length() - nullTailIndex * 5 - 1, sb.length()).append(']');
        return sb.toString();
    }

    public static TreeNode deserialize(String data) {
        if (data == null || data.length() < 3) {
            return null;
        }
        String[] values = data.substring(1, data.length() - 1).split(",");
        int childIndex = 1;
        Queue<TreeNode> taskList = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        taskList.offer(root);
        while (!taskList.isEmpty()) {
            TreeNode cursor = taskList.poll();
            int end = Math.min(childIndex + 2, values.length);
            while (childIndex < end) {
                String childData = values[childIndex];
                if (!"null".equals(childData)) {
                    TreeNode child = new TreeNode(Integer.parseInt(childData));
                    if (childIndex % 2 == 1) {
                        cursor.left = child;
                    } else {
                        cursor.right = child;
                    }
                    taskList.offer(child);
                }
                childIndex++;
            }
        }
        return root;
    }

    public static TreeNode deserializeSortedArray(String data) {
        if (data == null || data.length() < 3) {
            return null;
        }
        String[] values = data.substring(1, data.length() - 1).split(",");
        int[] nums = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            nums[i] = Integer.parseInt(values[i]);
        }
        return buildTree(nums);
    }

    public static TreeNode buildTree(int[] nums) {
        return buildTree(nums, 0, nums.length);
    }

    private static TreeNode buildTree(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode cursor = new TreeNode(nums[mid]);
        cursor.left = buildTree(nums, start, mid - 1);
        cursor.right = buildTree(nums, mid + 1, end);
        return cursor;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new TreeNodeIterator(this);
    }

    public static class TreeNodeIterator implements Iterator<TreeNode> {

        Stack<TreeNode> stack;

        private TreeNodeIterator(TreeNode root) {
            stack = new Stack<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            TreeNode cursor = stack.pop();
            TreeNode root = cursor.right;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            return cursor;
        }
    }
}
