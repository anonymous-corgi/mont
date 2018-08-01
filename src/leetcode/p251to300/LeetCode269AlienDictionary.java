package leetcode.p251to300;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LeetCode269AlienDictionary {
	
  private class Node {
    public int ins;
    public Set<Character> nxt;
    public Node () {
      ins = 0;
      nxt = new HashSet<>();
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
      String s1 = words[i];
      String s2 = words[i + 1];
      int minLen = Math.min(s1.length() , s2.length());
      for (int j = 0; j < minLen; j++) {
        char c1 = s1.charAt(j);
        char c2 = s2.charAt(j);
        if (c1 != c2) {
          if (!nodes[toInt(c1)].nxt.contains(c2)) {
            nodes[toInt(c1)].nxt.add(c2);
            nodes[toInt(c2)].ins++;
          }
          break;
        }
      }
    }
    
    Queue<Character> taskList = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (nodes[i] != null && nodes[i].ins == 0) {
        taskList.offer(toChar(i));
      }
    }
    
    while (!taskList.isEmpty()) {
      Character cursor = taskList.poll();
      sb.append(cursor);
      for (Character nei : nodes[toInt(cursor)].nxt) {
        int in = nodes[toInt(nei)].ins;
        if (in > 1) {
          nodes[toInt(nei)].ins--;
        } else if (in == 1) {
          nodes[toInt(nei)].ins = 0;
          taskList.offer(nei);
        }
      }
    }
    return sb.length() == size ? sb.toString() : "";
  }
  
  private int toInt(char c) {
    return c - 'a';
  }
  
  private char toChar(int n) {
    return (char) (n + 'a');
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    LeetCode269AlienDictionary one = new LeetCode269AlienDictionary();
//    String[] words = {"ze","yf","xd","wd","vd","ua","tt","sz","rd", "qd","pz","op","nw","mt","ln","ko","jm","il", "ho","gk","fa","ed","dg","ct","bb","ba"};
//    String[] words = {"wrt","wrf","er","ett","rftt"};
    String[] words = {"zy","zx"};
    System.out.println(one.alienOrder(words));
  }

}
