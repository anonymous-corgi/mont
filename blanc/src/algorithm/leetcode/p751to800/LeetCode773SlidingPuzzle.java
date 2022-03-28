package algorithm.leetcode.p751to800;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LeetCode773SlidingPuzzle {
  
  public static class BFS_method {
    
    private final String TARGET = "123450";
    private final int[][] ALTS = {{1,3},{0,2,4},{1,5},
                                  {0,4},{1,3,5},{2,4}};
    
    public int slidingPuzzle(int[][] board) {
      Set<String> seen = new HashSet<>(); // used to avoid duplicates
      // convert board to string - initial state.
      String start = Arrays.deepToString(board).replaceAll("\\[|\\]|,|\\s", "");
      Queue<String> taskList = new LinkedList<>(Arrays.asList(start));
      seen.add(start); // add initial state to set.
      int step = 0; // record the # of rounds of Breadth Search
      while (!taskList.isEmpty()) { // Not traverse all states yet?
        // loop used to control search breadth.
        for (int sz = taskList.size(); sz > 0; --sz) { 
          String cursor = taskList.poll();
          if (TARGET.equals(cursor)) { return step; } // found target.
          int zero = cursor.indexOf('0'); // locate '0'
          char[] puzzle = cursor.toCharArray();
          for (int j : ALTS[zero]) {
            // swap ch[i] and ch[j].
            puzzle[zero] = puzzle[j];
            puzzle[j] = '0';
            String next = String.valueOf(puzzle); // a new candidate state.
            if (seen.add(next)) { 
              taskList.offer(next); 
            }
            puzzle[j] = puzzle[zero];
            puzzle[zero] = '0';
          }
        }
        step++;
      }
      return -1;
    }
    
  }
  
  public static class Fastest_method {
    
    private final int[] base = new int[]{1, 6, 36, 216, 1296, 7776};
    private final int[][] nextStates = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    
    public int slidingPuzzle(int[][] board) {
      boolean[] visited = new boolean[46656];
      int target = 11190;
      int start = getRepresentation(board);
      if (start == target) return 0;
      //System.out.println("The start state is " + start);
      //System.out.println("Starting zero position is " + findZeroPosition(board));
      
      Queue<Step> queue = new LinkedList<Step>();
      queue.add(new Step(findZeroPosition(board), start, 0));
      
      while (!queue.isEmpty()) {
        Step next = queue.poll();
        if (visited[next.rep]) continue;
        visited[next.rep] = true;
        for (int pos : nextStates[next.zeropos]) {
          int nextState = exchange(next.rep, next.zeropos, pos);
          //System.out.println("The new state is " + nextState);
          if (nextState == target) return next.steps + 1;
          if (!visited[nextState]) queue.add(new Step(pos, nextState, next.steps + 1));
        }
      }
      
      return -1;
    }
    
    private int findZeroPosition(int[][] board) {
      for (int i = 0; i < board.length; ++i) {
        for (int j = 0; j < board[i].length; ++j) {
          if (board[i][j] == 0) {
            return (1 - i) * 3 + 2 - j;
          }
        }
      }
      return -1;
    }
    
    private int getRepresentation(int[][] board) {
      int res = 0;
      for (int i = 0; i < board.length; ++i) {
        for (int j = 0; j < board[i].length; ++j) {
          res *= 6;
          res += board[i][j];
        }
      }
      return res;
    }
    
    private class Step {
      int zeropos;
      int rep;
      int steps;
      
      private Step (int zeropos, int rep, int steps) {
        this.zeropos = zeropos;
        this.rep = rep;
        this.steps = steps;
      }
    }
    
    private int exchange(int state, int i, int j) {
      int digit = state / base[j] % 6;
      state -= digit * base[j];
      state += digit * base[i];
      return state;
    }
    
  }


  public static void main(String[] args) {

  }

}
