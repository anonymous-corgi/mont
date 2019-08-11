package leetcode.p151to200;

import java.util.Stack;

/**
 * @see leetcode.p701to750.LeetCode716MaxStack
 */
public class LeetCode155MinStack {

    private interface MinStack {

        void push(int x);

        void pop();

        int top();

        int getMin();
    }

    private static class OneStack implements MinStack {

        private int mMin = Integer.MAX_VALUE;
        private Stack<Long> mStack = new Stack<>();

        @Override
        public void push(int x) {
            mStack.push((long) x - mMin);
            mMin = Math.min(mMin, x);
        }

        @Override
        public void pop() {
            if (mStack.isEmpty()) {
                return;
            }
            long num = mStack.pop();
            if (num < 0) {
                mMin -= num;
            }
        }

        @Override
        public int top() {
            long num = mStack.peek();
            if (num < 0) {
                return mMin;
            } else {
                return (int) (num + mMin);
            }
        }

        @Override
        public int getMin() {
            return mMin;
        }
    }

    private static class TwoStack implements MinStack {

        private Stack<Integer> mOriStack = new Stack<>();
        private Stack<Integer> mMinStack = new Stack<>();

        @Override
        public void push(int x) {
            mOriStack.push(x);
            if (mMinStack.isEmpty()) {
                mMinStack.push(x);
            } else {
                mMinStack.push(Math.min(mMinStack.peek(), x));
            }
        }

        @Override
        public void pop() {
            mOriStack.pop();
            mMinStack.pop();
        }

        @Override
        public int top() {
            return mOriStack.peek();
        }

        @Override
        public int getMin() {
            return mMinStack.peek();
        }
    }
}

