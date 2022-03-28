package algorithm.leetcode.p001to050;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode022GenerateParentheses {

    //Use Stack to accomplish
    private static class Node {
        String str;
        int open;
        int close;
        boolean[] neis;

        private Node(String s, int o, int c) {
            this.str = s;
            this.open = o;
            this.close = c;
            this.neis = new boolean[2];
        }
    }

    public List<String> generateParenthesis(int n) {
        Stack<Node> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        stack.push(new Node("(", 1, 0));

        while (!stack.isEmpty()) {
            Node cursor = stack.peek();

            if (!cursor.neis[0] && cursor.open < n) {
                cursor.neis[0] = true;
                stack.push(new Node(cursor.str + '(', cursor.open + 1, cursor.close));
                continue;
            }
            if (!cursor.neis[1] && cursor.close < cursor.open) {
                cursor.neis[1] = true;
                stack.push(new Node(cursor.str + ')', cursor.open, cursor.close + 1));
                continue;
            }
            if (cursor.open == n && cursor.close == n) {
                list.add(cursor.str);
            }
            stack.pop();
        }
        return list;
    }
}
