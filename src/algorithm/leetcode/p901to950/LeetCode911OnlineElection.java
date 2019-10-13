package algorithm.leetcode.p901to950;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LeetCode911OnlineElection {
  
  private int[][] leading;
  
  public LeetCode911OnlineElection(int[] persons, int[] times) {
    List<int[]> lList = new ArrayList<>();
    int maxVote = 0;
    int maxPerson = -1;
    int[] count = new int[persons.length + 1];
    for (int i = 0; i < persons.length; i++) {
      if (count[persons[i]]++ >= maxVote - 1) {
        maxVote = count[persons[i]];
        if (persons[i] != maxPerson) {
          maxPerson = persons[i];
          lList.add(new int[]{times[i], maxPerson});
        }
      }
    }
    leading = lList.toArray(new int[0][2]);
  }
  
  public int q(int t) {
    int index = Arrays.binarySearch(leading, new int[]{t}, (a, b) -> (a[0] - b[0])); 
    return index < 0 ? leading[-index - 2][1] : leading[index][1];
  }

  public static void main(String[] args) {
    int[] persons = {0,1,1,0,0,1,0};
    int[] times = {0,5,10,15,20,25,30};
    LeetCode911OnlineElection one =
        new LeetCode911OnlineElection(persons, times);
    int[] q = {3,12,25,15,24,8};
    for (int t : q) {
       System.out.println(one.q(t));
    }
   
  }

}
