package leetcode.p701to750;

import java.util.Stack;

/**
 * @see leetcode.p151to200.LeetCode155MinStack
 * <p>
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * <p>
 * push(x) – Push element x onto stack.
 * pop() – Remove the element on top of the stack and return it.
 * top() – Get the element on the top.
 * peekMax() – Retrieve the maximum element in the stack.
 * popMax() – Retrieve the maximum element in the stack, and remove it.
 * If you find more than one maximum elements, only remove the top-most one.
 * <p>
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 */
public class LeetCode716MaxStack {

    private interface MaxStack {

        void push(int x);

        int pop();

        int top();

        int peekMax();

        int popMax();
    }

    private static class OneStack implements MaxStack {

        private int mMax = Integer.MIN_VALUE;
        private Stack<Long> mStack = new Stack<>();

        @Override
        public void push(int x) {
            mStack.push((long) x - mMax);
            mMax = Math.max(mMax, x);
        }

        @Override
        public int pop() {
            long diff = mStack.pop();
            int num;
            if (diff > 0) {
                num = mMax;
                mMax -= diff;
            } else {
                num = (int) (mMax + diff);
            }
            return num;
        }

        @Override
        public int top() {
            long diff = mStack.peek();
            int num;
            if (diff > 0) {
                num = mMax;
                mMax -= diff;
            } else {
                num = (int) (mMax + diff);
            }
            return num;
        }

        @Override
        public int peekMax() {
            return mMax;
        }

        @Override
        public int popMax() {
            long diff;
            while ((diff = mStack.pop()) < 0) ;
            int num = mMax;
            mMax -= diff;
            return num;
        }
    }
}
