package interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import utils.Print;

public class PrimeInSubtree {
  
  public List<Integer> primeQuery(int n, List<Integer> u, List<Integer> v, 
                                  List<Integer> values, List<Integer> queries) {
    List<Integer> res = new ArrayList<>();
    int[] parent = new int[n + 1];
    int[] primeNum = new int[n + 1];
    for (int i = 1; i < parent.length; i++) {
      parent[i] = i;
    }
    for (int i = 0, iLen = u.size(); i < iLen; i++) {
      parent[v.get(i)] = u.get(i);
    }
    for (int i = 0, iLen = values.size(); i < iLen;) {
      int val = values.get(i++);
      primeNum[i] = isPrime(val) ? 1 : 0; 
    }        
    for (int i = 1; i < primeNum.length; i++) {
      // loop and add
      int temp = i;
      if (primeNum[i] > 0) {
        while (parent[temp] != temp) {
          temp = parent[temp];
          primeNum[temp]++;
        }
      }
    }
    
    for (int q : queries) {
       res.add(primeNum[q]);
    }
    
    return res;
  }
  
  private boolean isPrime(int n) {
    if (n <= 3) {
      return true;
    }
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
  
  
  @Test
  public void testGetStopPoint() {
    int n = 6;
    List<Integer> u = Arrays.asList(1, 2, 2, 1, 3);
    List<Integer> v = Arrays.asList(2, 4, 5, 3, 6);
    List<Integer> values = Arrays.asList(2, 2, 6, 5, 4, 3);
    List<Integer> queries = Arrays.asList(1, 4, 5, 6, 2);
    List<Integer> testRes = primeQuery(n, u, v, values, queries);
    int[] res = {4, 1, 0, 1, 2};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], (int) testRes.get(i));
    }
  }
  
  public static void main(String[] args) {
    // testcase
    int n = 6;
    List<Integer> u = Arrays.asList(1, 2, 2, 1, 3);
    List<Integer> v = Arrays.asList(2, 4, 5, 3, 6);
    List<Integer> values = Arrays.asList(2, 2, 6, 5, 4, 3);
    List<Integer> queries = Arrays.asList(1, 4, 5, 6, 2);
    Print.printList(new PrimeInSubtree().primeQuery(n, u, v, values, queries));
    // 4 1 0 1 2
  }

}
