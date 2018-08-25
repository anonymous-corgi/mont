package leetcode.p851to900;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeetCode857MinimumCostToHireKWorkers {
  
  private class Node {
    int q;
    double r;
    public Node(int q, int w) {
      this.q = q;
      this.r = (double) w / q;
    }
  }
  
  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    Node[] nodes = new Node[quality.length];
    for (int i = 0; i < quality.length; i++) {
      nodes[i] = new Node(quality[i], wage[i]);
    }
    Arrays.sort(nodes, (a, b) -> {return Double.compare(a.r, b.r);});
    PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> (b.q - a.q));
    int qSum = 0;
    double minW = Double.MAX_VALUE;
    for (int i = 0; i < quality.length; i++) {
      que.offer(nodes[i]);
      qSum += nodes[i].q;
      
      if (i >= K - 1) {
        minW = Math.min(minW, qSum * nodes[i].r);
        qSum -= que.poll().q;
      }
    }
    return minW;
  }

  public static void main(String[] args) {

  }

}
