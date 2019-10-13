package algorithm.leetcode.p201to250;

@SuppressWarnings("unused")
public class LeetCode208ImplementTrie {

    private interface Trie {

        /**
         * Inserts a word into the trie.
         */
        void insert(String word);

        /**
         * Returns if the word is in the trie.
         */
        boolean search(String word);

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        boolean startsWith(String prefix);
    }

    private static class Impl implements Trie {

        private final Node root = new Node();

        @Override
        public void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node();
                }
                node = node.next[index];
            }
            node.isWord = true;
        }

        @Override
        public boolean search(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.next[index] == null) {
                    return false;
                }
                node = node.next[index];
            }
            return node.isWord;
        }

        @Override
        public boolean startsWith(String prefix) {
            Node node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.next[index] == null) {
                    return false;
                }
                node = node.next[index];
            }
            return true;
        }

        private static class Node {
            Node[] next = new Node[26];
            boolean isWord = false;
        }
    }
}
