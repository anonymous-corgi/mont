package algorithm.leetcode.p401to450;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode406QueueReconstructionByHeight {
  
  public int[][] reconstructQueue(int[][] people) {
    
    List<int[]> res = new ArrayList<>(people.length);
    Arrays.sort(people, (a, b)-> {return a[0] == b[0] 
        ? a[1] - b[1] : b[0] - a[0];});
    
    for (int[] cursor : people) {
        res.add(cursor[1], cursor);
    }
    
    return res.toArray(new int[0][2]);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode406QueueReconstructionByHeight one 
    = new LeetCode406QueueReconstructionByHeight();
    int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
    one.reconstructQueue(people);
  }

}
