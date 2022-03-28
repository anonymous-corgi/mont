package algorithm.leetcode.p351to400;

import java.util.HashMap;
import java.util.Map;

public class LeetCode399EvaluateDivision {
  
  /**
   * Union Find Summary:
   * 1. Define ufp to store position, ufv to store other properties.
   * 2. Define find() and connect() method
   *    find() is to find root position of this key,
   *    according to the existed relationship.
   *    connect() is to build relationship between two keys,
   *    that have no connection previously.
   * 3. Initiate the ufp and ufv
   * 4. Look below.
   */
  private Map<String, String> ufp;
  private Map<String, Double> ufv;
  
  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    if (equations == null || equations.length == 0) {
      return new double[0];
    }
    ufp = new HashMap<>();
    ufv = new HashMap<>();
    
    //Remember to initiate the ufp and ufv
    for (int i = 0; i < equations.length; i++) {
      if (!ufp.containsKey(equations[i][0])) {
        ufp.put(equations[i][0], equations[i][0]);
        ufv.put(equations[i][0], 1.0);
      }
      if (!ufp.containsKey(equations[i][1])) {
        ufp.put(equations[i][1], equations[i][1]);
        ufv.put(equations[i][1], 1.0);
      }
      if (!find(equations[i][0]).equals(find(equations[i][1]))) {
        connect(equations[i][0], equations[i][1], values[i]);
      }
    }
    double[] res = new double[queries.length];
    for (int i = 0; i < queries.length; i++) {
      if (!ufv.containsKey(queries[i][0]) || !ufv.containsKey(queries[i][1])) {
        res[i] = -1.0d;
      } else if (find(queries[i][0]).equals(find(queries[i][1]))) {
        res[i] = ufv.get(queries[i][0]) / ufv.get(queries[i][1]);
      } else {
        res[i] = -1.0d;
      }
    }
    return res;
  }
  
  /**Task for method find():
   * 1. Find the position where this key points to, and return it.
   * 2. If this key is point to other position p and 
   *    the position p is pointing to another position pp,
   *    update this key pointing to root position(where p is pointing to).
   * 3. If this key has another property with the position it points to,
   *    also update this property making this property relates to the root position.
   */
  private String find(String s) {
    String sP = ufp.get(s);
    if (s.equals(sP)) {
      return sP;
    } else {
      String sPP = find(sP);
      double v = ufv.get(s) * ufv.get(sP);
      ufp.put(s, sPP);
      ufv.put(s, v);
      return sPP;
    }
  }
  
  /**Task for method connect():
   * 1. Find the root positions of these two keys.
   * 2. If these two keys are not pointing to a same root position,
   *    update one of the root position pointing to another root position.
   *    (Be sure that both are root positions!!!)
   * 3. If these keys have another property, also update these property.
   */
  private void connect(String a, String b, double values) {
    String aP = find(a);
    String bP = find(b);
    double v = ufv.get(b) * values / ufv.get(a);
    ufp.put(aP, bP);
    ufv.put(aP, v);
  }
//  You can also connect a'root to b rather than b's root. Code as follow:
//  private void connect(String a, String b, double values) {
//    String aP = find(a);
//    double v = values / ufv.get(a);
//    uf.put(aP, b);
//    ufv.put(aP, v);
//  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode399EvaluateDivision one = new LeetCode399EvaluateDivision();
    String[][] equations = {{"a", "b"},{"e", "f"}, {"b", "e"}};
    double[] values = {3.4, 1.4, 2.3};
    String[][] queries = {{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}};
//    String[][] equations = {{"x1","x2"},{"x2","x3"},{"x1","x4"},{"x2","x5"}};
//    double[] values = {3.0,0.5,3.4,5.6};
//    String[][] queries = {{"x2","x4"},{"x1","x5"},{"x1","x3"},{"x5","x5"},{"x5","x1"},{"x3","x4"},{"x4","x3"},{"x6","x6"},{"x0","x0"}};
    double[] res = one.calcEquation(equations, values, queries);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i] + ", ");
    }

  }

}
