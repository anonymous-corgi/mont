package algorithm.interview.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Puzzle3x3 {
  
  private final String TARGET = "123456780";
  private final int[][] ALTS = {{1,3},{0,2,4},{1,5},
                                {0,4,6},{1,3,5,7},{2,4,8},
                                {3,7},{4,6,8},{5,7}};
  public int slidingPuzzle(int[][] board) {
    int step = 0;
    String start = Arrays.deepToString(board).replaceAll("\\[|\\]|,|\\s", "");
    Set<String> visited = new HashSet<>();
    Queue<String> taskList = new LinkedList<>();
    taskList.offer(start);
    visited.add(start);
    while (!taskList.isEmpty()) {
      for (int i = 0, iLen = taskList.size(); i < iLen; i++) {
        String cursor = taskList.poll();
        if (TARGET.equals(cursor)) { 
          return step;
        }
        int zero = cursor.indexOf('0');
        char[] puzzle = cursor.toCharArray();
        for (int j : ALTS[zero]) {
          puzzle[zero] = puzzle[j];
          puzzle[j] = '0';
          String nextPuzzle = String.valueOf(puzzle);
          if (visited.add(nextPuzzle)) {
            taskList.offer(nextPuzzle);
          }
          puzzle[j] = puzzle[zero];
          puzzle[zero] = '0';
        }
      }
      step++;
    }
    return -1;
  }

  public static void main(String[] args) {
    Puzzle3x3 one = new Puzzle3x3();
    //Ans: 14
    int[][] board = {{2,3,0},{1,6,8},{7,5,4}};
//    //Ans: 2
//    int[][] board = {{1,2,3},{4,5,6},{0,7,8}};
    System.out.println(one.slidingPuzzle(board));
  }

}
