package interview.twitter;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Whostheclosest {
  
  private int[] listPos;
  private Map<Character, List<Integer>> posListMap;
  
  public int[] getClosest(String s, int[] queries) {
    initMap(s);
    int[] res = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      res[i]= listPos[queries[i]];
    }
    return res;
  }
  
  private void initMap(String s) {
    posListMap = new HashMap<>();
    listPos = new int[s.length()];
    for (int i = 0, iLen = s.length(); i < iLen; i++) {
      char c = s.charAt(i);
      List<Integer> posList;
      if (posListMap.containsKey(c)) {
        posList = posListMap.get(c);
      } else {
        posList = new ArrayList<>();
        posListMap.put(c, posList);
      }
      int pos = posList.size();
      posList.add(i);
      
      if (pos >= 2) {
        int prev = posList.get(pos - 2);
        int mid = posList.get(pos - 1);
        int next = i;
        listPos[mid] = next - mid < mid - prev ? next : prev;
      } else if (pos == 1) {
        listPos[posList.get(0)] = i;
      }
      listPos[i] = pos > 0 ? posList.get(pos - 1) : -1;
    }
  }
  
  
  @Test
  public void testGetClosest1() {
    String s = "babab";
    int[] queries = {2,1,4,0};
    int[] res = {0,3,2,2};
    assertArrayEquals(res, getClosest(s, queries));
  }  
  
  @Test
  public void testGetClosest2() {
    String s = "hackerrank";
    int[] queries = {4,1,6,8};
    int[] res = {-1,7,5,-1};
    assertArrayEquals(res, getClosest(s, queries));
  }

}
