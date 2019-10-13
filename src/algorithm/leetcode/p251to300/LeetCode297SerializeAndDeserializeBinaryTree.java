package algorithm.leetcode.p251to300;

import org.junit.Test;

import algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LeetCode297SerializeAndDeserializeBinaryTree {

    private interface Method {

        // Encodes a tree to a single string.
        String serialize(TreeNode root);

        // Decodes your encoded data to tree.
        TreeNode deserialize(String data);
    }

    private static final class BFS implements Method {

        @Override
        public String serialize(TreeNode root) {
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

        @Override
        public TreeNode deserialize(String data) {
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
    }

    private static Method getMethod() {
        return new BFS();
    }

    @Test
    public void testcase1() {
        String data = "[1,2,3,null,null,4,5]";
        test(data);
    }

    @Test
    public void testcase2() {
        String data = "[1]";
        test(data);
    }

    @Test
    public void testcase3() {
        String data = "[5,2,3,null,null,2,4,3,1]";
        test(data);
    }

    private void test(String data) {
        Method method = getMethod();
        TreeNode root = method.deserialize(data);
        String dataRecovered = method.serialize(root);
        System.out.println(dataRecovered);
        assertThat(dataRecovered, equalTo(data));
    }
}
