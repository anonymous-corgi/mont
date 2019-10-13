package base.tree;

public class TrieTreeImpl implements TrieTree {

    private Node root;

    public TrieTreeImpl() {
        root = new Node();
    }

    @Override
    public boolean insert(String word) {
        boolean isNew = false;
        Node cursor = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (cursor.next[pos] == null) {
                isNew = true;
                cursor.next[pos] = new Node();
            }
            cursor = cursor.next[pos];
        }
        isNew |= !cursor.isInDict;
        cursor.isInDict = true;
        return isNew;
    }

    @Override
    public boolean search(String word) {
        Node cursor = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (cursor.next[pos] == null) {
                return false;
            }
            cursor = cursor.next[pos];
        }
        return cursor.isInDict;
    }

    @Override
    public boolean startsWith(String prefix) {
        Node cursor = root;
        for (int i = 0; i < prefix.length(); i++) {
            int pos = prefix.charAt(i) - 'a';
            if (cursor.next[pos] == null) {
                return false;
            }
            cursor = cursor.next[pos];
        }
        return true;
    }

    private class Node {
        boolean isInDict = false;
        Node[] next = new Node[26];
    }
}
