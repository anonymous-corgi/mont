package leetcode.p151to200;

public class LeetCode200NumberOfIslands {
	
  private int[] ufp;
  
  public int numIslands(boolean[][] grid) {
    // write your code here
    if(grid == null || grid.length == 0 
        || grid[0] == null || grid[0].length == 0){
      return 0;
    }
    
    int rows = grid.length;
    int columns = grid[0].length;
    int size = rows * columns;
    ufp = new int[size];
    for(int i = 0; i < size; i++){
      ufp[i] = i;
    }
    int count = 0;
    int current = -1;
    for(int i = 0; i < rows; ++i){
      for(int j = 0;j < columns; ++j){
        current++;
        if (grid[i][j]) {
          count++;
          if(j + 1 < columns && grid[i][j + 1]
              && connect(current, (current + 1))){
            count--; 
          }
          if(i + 1 < rows && grid[i + 1][j]
              && connect(current, (current + columns))){
            count--; 
          }
        }
        System.out.print(ufp[current] + "\t");
      }
      System.out.println();
    }
    return count;
  }
  
  private int find(int num){
    return ufp[num] == num ? num : (ufp[num] = find(ufp[num]));
  }
  
  private boolean connect(int to, int from){
    int root_to = find(to);
    int root_from = find(from);
    if(root_from != root_to){
      ufp[root_from] = root_to;
      return true;
    } else {
      return false;
    }
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode200NumberOfIslands one = new LeetCode200NumberOfIslands();
    boolean[][] grid = {{true,true,true,true,true,true},
        {true,false,false,false,false,true},
        {true,false,true,true,false,true},
        {true,false,false,false,false,true},
        {true,true,true,true,true,true}};
    System.out.println("The number of islands: " + one.numIslands(grid));
    System.out.println();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print((grid[i][j] ? "¡ø" : "~") + "\t");
      }
      System.out.println();
    }
  }
  
}
