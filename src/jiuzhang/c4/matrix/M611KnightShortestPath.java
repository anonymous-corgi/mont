package jiuzhang.c4.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class M611KnightShortestPath {
	
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        
        rows = grid.length;
        columns = grid[0].length;
        
        if(!insideMargin(source.x, source.y)){
            return -1;
        }
        if(!insideMargin(destination.x, destination.y)){
            return -1;
        }
        
        int count = 0;
        Queue<Point> taskList = new LinkedList<>();
        taskList.offer(source);
        
        int[][] movement = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
        
        while(!taskList.isEmpty()){
            int size = taskList.size();
            for(int i = 0; i < size; i++){
                Point current = taskList.poll();
                if(current.x == destination.x && current.y == destination.y){
                    return count;
                }
                for(int k = 0; k < 8; k++){
                    int newX = current.x + movement[k][0];
                    int newY = current.y + movement[k][1];
                    if(!insideMargin(newX, newY)){
                    	continue;
                    }
                    if(grid[newX][newY]){
                        continue;
                    }
                    taskList.offer(new Point(newX, newY));
                    grid[newX][newY] = true;
                }
            }
            count++;
        }
        return -1;
    }
    
    private int rows;
    private int columns;
    
    private boolean insideMargin(int x, int y){
        if(x >= rows || x < 0 || y >= columns || y < 0){
            return false;
        }
        return true;
    }
    
    public class Point {
    	 public int x, y;
    	 public Point() { x = 0; y = 0; }
    	 public Point(int a, int b) { x = a; y = b; }
   }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
