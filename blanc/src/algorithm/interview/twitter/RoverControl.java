package algorithm.interview.twitter;

import static org.junit.Assert.*;
import org.junit.Test;

public class RoverControl {
  
  public int getStopPoint(int n, String[] cmds) {
    int row = 0;
    int col = 0;
    final int BOUNDARY = n - 1;
    for (String cmd : cmds) {
      switch(cmd) {
        case "UP" : {
          if (row > 0) { row--; } 
          break; }
        case "DOWN" : {
          if (row < BOUNDARY) { row++; }
          break;
        }
        case "LEFT" : {
          if (col > 0) { col--; }
          break;
        }
        case "RIGHT" : {
          if (col < BOUNDARY) { col++; }
          break;
        }
      }
    }
    return row * n + col;
  }
  
  
  @Test
  public void testGetStopPoint() {
    int n = 4;
    String[] cmds = {"RIGHT", "UP", "DOWN", "LEFT", "LEFT", "DOWN", "DOWN"};
    assertEquals(12, getStopPoint(n, cmds));
  }

  public static void main(String[] args) {
    int n = 4;
    String[] cmds = {"RIGHT", "UP", "DOWN", "LEFT", "LEFT", "DOWN", "DOWN"};
    System.out.println(new RoverControl().getStopPoint(n, cmds));
  }

}
