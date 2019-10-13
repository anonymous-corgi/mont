package leetcode.p301to350;

import algorithm.base.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

@SuppressWarnings("unused")
public class LeetCode341FlattenNestedListIterator {

    private static class NestedIterator implements Iterator<Integer> {

        private final Stack<NestedInteger> mStack = new Stack<>();
        private Integer mNext = null;

        public NestedIterator(List<NestedInteger> nestedList) {
            pushList(nestedList);
        }

        @Override
        public boolean hasNext() {
            return mNext != null;
        }

        @Override
        public Integer next() {
            Integer next = mNext;
            mNext = null;
            retrieve();
            return next;
        }

        private void retrieve() {
            if (mNext == null && !mStack.isEmpty()) {
                NestedInteger node = mStack.pop();
                if (node.isInteger()) {
                    mNext = node.getInteger();
                } else {
                    pushList(node.getList());
                }
            }
        }

        private void pushList(List<NestedInteger> list) {
            for (int i = list.size() - 1; i >= 0; i--) {
                mStack.push(list.get(i));
            }
            retrieve();
        }
    }
}
