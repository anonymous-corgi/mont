package algorithm.leetcode.p801to850;

import java.util.Arrays;

public class LeetCode881BoatsToSavePeople {
  
  public int numRescueBoats(int[] people, int limit) {
    if (people == null || people.length == 0) {
      return 0;
    }
    Arrays.sort(people);
    int left = 0;
    int right = people.length - 1;
    int count = 0;
    while (left <= right) {
      if (people[left] + people[right] <= limit) {
        left++;
      }
      right--;
      count++;
    }
    return count;
  }

  public static void main(String[] args) {

  }

}
