package leetcode.p651to700;

public class LeetCode676ImplementMagicDictionary {
  
  public static class Trie_method {
    
    private class Trie {
      //Array is much faster than LinkedHashMap.
      Trie[] next;
      boolean isWord;
      public Trie() {
        this.next = new Trie[26];
        this.isWord = false;
      }
    }
    
    private Trie root = new Trie();
    
    public void buildDict(String[] dict) {
      for (String str : dict) {
        Trie node = root;
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
          int index = chs[i] - 'a';
          if (node.next[index] == null) {
            node.next[index] = new Trie();
          }
          node = node.next[index];
        }
        node.isWord = true;
      }
    }
    
    public boolean search(String word) {
      return search(root, word, 0, 0);
    }
    
    private boolean search(Trie node, String word, int pos, int diff) {
      if (diff > 1) {
        return false;
      }
      if (pos == word.length()) {
        return node.isWord && (diff == 1);
      }
      char c = word.charAt(pos);        
      for (int i = 0; i < 26; i++) {
        if (node.next[i] != null && 
            search(node.next[i], word, pos + 1, i == c - 'a' ? diff : diff + 1)) {
          return true;
        }
      }
      return false;
    }
    
  }

  public static void main(String[] args) {
    String[] dict = {"hello","hallo","leetcode"};
    LeetCode676ImplementMagicDictionary.Trie_method one =
        new LeetCode676ImplementMagicDictionary.Trie_method();
    one.buildDict(dict);
    String[] searchList = {"hello", "hhllo", "hell", "leetcoded"};
    for (String str : searchList) {
      System.out.println("Has " + str + ": " + one.search(str));
    }
  }
  
}
