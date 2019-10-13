package algorithm.leetcode.p651to700;

public class LeetCode684RedundantConnection {
  
  public int[] findRedundantConnection(int[][] edges) {
    int[] ufp = new int[edges.length + 1];
    for (int i = 1; i < ufp.length; i++) {
      ufp[i] = i;
    }
    for (int[] edge : edges) {
      if (find(ufp, edge[0]) == find(ufp, edge[1])) {
        return edge;
      }
      connect(ufp, edge[0], edge[1]);
    }
    return new int[2];
  }
  
  private int find(int[] ufp, int num) {
    return ufp[num] == num ? num : (ufp[num] = find(ufp, ufp[num]));
  }
  
  private void connect(int[] ufp, int a, int b) {
    int ap = find(ufp, a);
    int bp = find(ufp, b);
    ufp[bp] = ap;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
