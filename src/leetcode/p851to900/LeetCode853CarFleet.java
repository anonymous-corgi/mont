package leetcode.p851to900;

import java.util.Arrays;

public class LeetCode853CarFleet {
  
  class Node {
    int dis;
    double time;
    public Node(int dis, double time) {
      this.dis = dis;
      this.time = time;
    }
  }
  
  public int carFleet(int target, int[] position, int[] speed) {
    Node[] cars = new Node[speed.length];
    for (int i = 0; i < speed.length; i++) {
      int dis = target - position[i];
      double time = (double) dis / speed[i];
      cars[i] = new Node(dis, time);
    }
    Arrays.sort(cars, (a, b) -> (a.dis - b.dis));
    int count = 0;
    double slowest = 0d;
    for (int i = 0; i < speed.length; i++) {
      if (slowest < cars[i].time) {
        count++;
        slowest = cars[i].time;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
