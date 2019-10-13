package algorithm.leetcode.p201to250;

@SuppressWarnings("unused")
public class LeetCode211AddAndSearchWord {

    private interface WordDictionary {

        /**
         * Adds a word into the data structure.
         */
        void addWord(String word);

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        boolean search(String word);
    }

    private static class Impl implements WordDictionary {

        private final Node root = new Node();

        @Override
        public void addWord(String word) {
            Node cursor = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cursor.next[index] == null) {
                    cursor.next[index] = new Node();
                }
                cursor = cursor.next[index];
            }
            cursor.isWord = true;
        }

        @Override
        public boolean search(String word) {
            return search(word, 0, root);
        }

        private boolean search(String word, int pos, Node root) {
            if (root == null) {
                return false;
            }
            if (pos == word.length()) {
                return root.isWord;
            }
            if (word.charAt(pos) == '.') {
                boolean found = false;
                for (int i = 0; i < 24 && !found; i++) {
                    found = search(word, pos + 1, root.next[i]);
                }
                return found;
            } else {
                int i = word.charAt(pos) - 'a';
                return search(word, pos + 1, root.next[i]);
            }
        }

        private static class Node {
            Node[] next = new Node[26];
            boolean isWord = false;
        }
    }
}
