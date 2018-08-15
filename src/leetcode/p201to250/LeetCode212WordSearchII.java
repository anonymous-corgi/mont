package leetcode.p201to250;

import java.util.ArrayList;
import java.util.List;

public class LeetCode212WordSearchII {
//  Based on [yavinci](leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00))
  private static class TrieNode{
    public TrieNode[] next = new TrieNode[26];
    String word;
  }
  
  private TrieNode root = new TrieNode();
  
  private void buildTree(String str) {
    TrieNode cursor = root;
    for (char c : str.toCharArray()) {
      int index = c - 'a';
      if (cursor.next[index] == null) {
        cursor.next[index] = new TrieNode();
      }
      cursor = cursor.next[index];
    }
    cursor.word= str;
  }
  
  public List<String> findWords(char[][] board, String[] words) {
    for (int i = 0; i <words.length; i++) {
      buildTree(words[i]);
    }
    List<String> res = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        dfs(board, i, j, root, res);
      }
    }
    return res;
  }
  
  private void dfs(char[][] board, int row, int col, TrieNode t, List<String> res) {
    char oriChar = board[row][col];
    int index =  oriChar - 'a';
    if (oriChar == '#' || t.next[index] == null) {
      return;
    }
    t = t.next[index];
    if (t.word != null) {
      res.add(t.word);
      t.word = null;
    }
    board[row][col] = '#';
    if (row > 0) {dfs(board, row - 1, col, t, res);}
    if (col > 0) {dfs(board, row, col - 1, t, res);}
    if (row < board.length - 1) {dfs(board, row + 1, col, t, res);}
    if (col < board[row].length - 1) {dfs(board, row, col + 1, t, res);}
    board[row][col] = oriChar;
  }

  public static void main(String[] args) {
    char[][] board = {
        {'o','a','a','n'},
        {'e','t','a','e'},
        {'i','h','k','r'},
        {'i','f','l','v'}
      };
    String[] words = {"oath","pea","eat","rain"};
    LeetCode212WordSearchII one = 
        new LeetCode212WordSearchII();
    List<String> res = one.findWords(board, words);
    for (int i = 0; i < res.size(); i++) {
      System.out.print(res.get(i) + "\t");
    }
  }

}
