package leetcode.p651to700;

public class LeetCode685RedundantConnectionII {
//  Solution modified from lh19900702's
  public int[] findRedundantDirectedConnection(int[][] edges) {
    int[] roots = new int[edges.length + 1];
    for (int i = 1; i <= edges.length; i++) {
      roots[i] = i;
    }
    
    int[] candidate1 = null;
    int[] candidate2 = null;
    for (int[] e : edges){
      int rootx = find(roots, e[0]);
      int rooty = find(roots, e[1]);
      if (rootx != rooty) {
        if (rooty != e[1]) {
          candidate1 = e; // record the last edge which results in "multiple parents" issue
        } else {
          roots[rooty] = rootx;
        }
      } else {
        candidate2 = e; // record last edge which results in "cycle" issue, if any.
      }
    }
    
    // if there is only one issue, return this one.
    if (candidate1 == null) return candidate2; 
    if (candidate2 == null) return candidate1;
    
    // If both issues present, then the answer should be the first edge which results in "multiple parents" issue
    // Could use map to skip this pass, but will use more memory.
    for (int[] e : edges) if (e[1] == candidate1[1]) return e;
    
    return new int[2];
  }
  //I only considered the "cycle" issue at first.
  private int find(int[] roots, int i){
    return roots[i] == i ? i : (roots[i] = find(roots, roots[i]));
  }
  
  public static void main(String[] args) {
//    int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
    int[][] edges = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
    LeetCode685RedundantConnectionII one =
        new LeetCode685RedundantConnectionII();
    int[] output = one.findRedundantDirectedConnection(edges);
    System.out.println(output[0] + ", " + output[1]);
  }

}
