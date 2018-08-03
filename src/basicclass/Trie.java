package basicclass;

public class Trie {

  private static class TrieNode {
    public boolean isInDict;
//    public String word;
    public TrieNode[] next;
    public TrieNode() {
      this.isInDict = false;
      this.next = new TrieNode[26];
    }
  }
  
  private TrieNode root;
  
  public Trie() {
    root = new TrieNode();
  }
  
  /*
   * @param word: a word
   * @return: nothing
   */
  public void insert(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      int pos = word.charAt(i) - 'a';
      if (cur.next[pos] == null) {
        cur.next[pos] = new TrieNode();
      }
      cur = cur.next[pos];
    }
    cur.isInDict = true;
  }
  
  /*
   * @param word: A string
   * @return: if the word is in the trie.
   */
  public boolean search(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      int pos = word.charAt(i) - 'a';
      if (cur.next[pos] == null) {
        return false;
      }
      cur = cur.next[pos];
    }
    return cur.isInDict;
  }
  
  /*
   * @param prefix: A string
   * @return: if there is any word in the trie that starts with the given prefix.
   */
  public boolean startsWith(String prefix) {
    TrieNode cur = root;
    for (int i = 0; i < prefix.length(); i++) {
      int pos = prefix.charAt(i) - 'a';
      if (cur.next[pos] == null) {
        return false;
      }
      cur = cur.next[pos];
    }
    return true;
  }
  
}
