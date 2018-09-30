package interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Test;

public class ImageMatching {
  
  private int rows;
  private int cols;
  private final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
  
  public int countMatch(List<String> grid1, List<String> grid2) {
    rows = grid2.size();
    cols = grid2.get(0).length();
    int count = 0;
    boolean[][] visited = new boolean[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid2.get(i).charAt(j) == '1' && !visited[i][j] 
            && isMatch(grid1, grid2, visited, i, j)) {
          count++;
        }
      }
    }
    return count;
  }
  
  private boolean isMatch(List<String> grid1, List<String> grid2, 
                          boolean[][] visited, int row, int col) {
    boolean isMatch = true;
    Queue<int[]> taskList = new LinkedList<>();
    taskList.offer(new int[]{row, col});
    visited[row][col] = true;
    while (!taskList.isEmpty()) {
      int[] point = taskList.poll();
      isMatch &= grid1.get(point[0]).charAt(point[1]) == '1';
      for (int[] dir : DIRS) {
        int[] nPoint = new int[]{point[0] + dir[0], point[1] + dir[1]};
        if (nPoint[0] >= 0 && nPoint[0] < rows && nPoint[1] >= 0 && nPoint[1] < cols 
            && !visited[nPoint[0]][nPoint[1]] && grid2.get(nPoint[0]).charAt(nPoint[1]) == '1') {
          visited[nPoint[0]][nPoint[1]] = true;
          taskList.offer(nPoint);
        }
      }
    }
    return isMatch;
  }
  
  
  @Test
  public void testCountMatch1() {
    List<String> grid1 = Arrays.asList("111","101","100");
    List<String> grid2 = Arrays.asList("111","100","101");    
    int res = 1;
    assertEquals(res, countMatch(grid1, grid2));
  }
  
  @Test
  public void testCountMatch2() {
    List<String> grid1 = Arrays.asList("001","011","100");
    List<String> grid2 = Arrays.asList("001","011","101");      
    int res = 1;
    assertEquals(res, countMatch(grid1, grid2));
  }
   
  @Test
  public void testCountMatch3() {
    List<String> grid1 = Arrays.asList("0100","1001","0011","0011");
    List<String> grid2 = Arrays.asList("0101","1001","0011","0011");      
    int res = 2;
    assertEquals(res, countMatch(grid1, grid2));
  }
  
  
 @Test
 public void testCountMatch4() {
   List<String> grid1 = Arrays.asList("0010","0111","0100","1111");
   List<String> grid2 = Arrays.asList("0010","0111","0110","1111");      
   int res = 0;
   assertEquals(res, countMatch(grid1, grid2));
 } 
 
// @Test
// public void testCountMatch() {
//   List<String> grid1 = Arrays.asList("","","","");
//   List<String> grid2 = Arrays.asList("","","","");      
//   int res = 1;
//   assertEquals(res, countMatch(grid1, grid2));
// }
  
  
  public static void main(String[] args) {
    List<String> grid1 = Arrays.asList("111","101","100");
    List<String> grid2 = Arrays.asList("111","100","101");    
//    List<String> grid1 = Arrays.asList("","","");
//    List<String> grid2 = Arrays.asList("","","");    
    ImageMatching one = new ImageMatching();
    System.out.println(one.countMatch(grid1, grid2));
  }

}
