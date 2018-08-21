package leetcode.p251to300;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class LeetCode269AlienDictionary {
	
  private class Node {
    public int ins;
    public Set<Integer> next;
    public Node () {
      ins = 0;
      next = new HashSet<>();
    }
  }
  
  public String alienOrder(String[] words) {
    // Write your code here
    if (words == null || words.length == 0) {
      return "";
    }
    Node[] nodes = new Node[26];
    int size = 0;
    
    for (int i = 0, wLen = words.length; i < wLen; i++) {
      for (int j = 0, sLen = words[i].length(); j < sLen; j++) {
        int cursor = toInt(words[i].charAt(j));
        if (nodes[cursor] == null) {
          nodes[cursor] = new Node();
          size++;
        }
      }
    }
    
    for (int i = 0, wLen = words.length - 1; i < wLen; i++) {
      char[] s1 = words[i].toCharArray();
      char[] s2 = words[i + 1].toCharArray();
      int minLen = Math.min(s1.length , s2.length);
      for (int j = 0; j < minLen; j++) {
        if (s1[j] != s2[j]) {
          int i1 = toInt(s1[j]);
          int i2 = toInt(s2[j]);
          if (nodes[i1].next.add(i2)) {
            nodes[i2].ins++;
          }
          break;
        }
      }
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //Using PriorityQueue can get the smallest in lexicographical order!
    Queue<Integer> taskList = new PriorityQueue<>();
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (nodes[i] != null && nodes[i].ins == 0) {
        taskList.offer(i);
      }
    }
    
    while (!taskList.isEmpty()) {
      Integer cursor = taskList.poll();
      res.append(toChar(cursor));
      for (Integer nei : nodes[cursor].next) {
        if (nodes[nei].ins-- == 1) {
          taskList.offer(nei);
        }
      }
    }
    return res.length() == size ? res.toString() : "";
  }
  
  private int toInt(char c) {
    return c - 'a';
  }
  
  private char toChar(int n) {
    return (char) (n + 'a');
  }

  public static void main(String[] args) {
    LeetCode269AlienDictionary one = new LeetCode269AlienDictionary();
//    //"wertf"
//    String[] words = {"wrt","wrf","er","ett","rftt"};
//    //"yxz"
//    String[] words = {"zy","zx"};
//    //"abcd"
    String[] words = {"ab","adc"};
    System.out.println(one.alienOrder(words));
  }

}
