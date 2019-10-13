package algorithm.leetcode.p101to150;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Solutions of LeetCode 116 Populating Next Right Pointers In Each Node
 * are identical to or simpler than LeetCode 117
 */
@SuppressWarnings("unused")
public class LeetCode117PopulatingNextRightPointersInEachNodeII {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

    private interface Method {
        Node connect(Node root);
    }

    private static class DFS implements Method {

        @Override
        public Node connect(Node root) {
            dfs(root, 0, new HashMap<>());
            return root;
        }

        private void dfs(Node root, int layer, Map<Integer, Node> lasts) {
            if (root == null) {
                return;
            }
            Node last = lasts.get(layer);
            if (last != null) {
                root.next = last;
            }
            lasts.put(layer, root);
            dfs(root.right, layer + 1, lasts);
            dfs(root.left, layer + 1, lasts);
        }
    }

    private static class BFS implements Method {

        @Override
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            Queue<Node> taskQueue = new ArrayDeque<>();
            taskQueue.offer(root);
            while (!taskQueue.isEmpty()) {
                Node last = null;
                for (int i = 0, len = taskQueue.size(); i < len; i++) {
                    Node cursor = taskQueue.poll();
                    cursor.next = last;
                    last = cursor;
                    if (cursor.right != null) {
                        taskQueue.offer(cursor.right);
                    }
                    if (cursor.left != null) {
                        taskQueue.offer(cursor.left);
                    }
                }
            }
            return root;
        }
    }

    private static class Supreme implements Method {

        @Override
        public Node connect(Node root) {
            Node layerHead = root;
            while (layerHead != null) {
                Node cursor = layerHead;
                Node last = null;
                layerHead = null;
                while (cursor != null) {
                    if (cursor.left != null) {
                        if (layerHead == null) {
                            layerHead = cursor.left;
                        }
                        if (last != null) {
                            last.next = cursor.left;
                        }
                        last = cursor.left;
                    }
                    if (cursor.right != null) {
                        if (layerHead == null) {
                            layerHead = cursor.right;
                        }
                        if (last != null) {
                            last.next = cursor.right;
                        }
                        last = cursor.right;
                    }
                    cursor = cursor.next;
                }
            }
            return root;
        }
    }
}
