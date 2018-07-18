package jiuzhang.c4.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class M598ZombieInMatrix {
	
    public int zombie(int[][] grid) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int columns = grid[0].length;
        int rows = grid.length;
        // stub = new int[rows * columns];
        // for(int i = 0; i < rows * columns; i++){
        //     stub[i] = i;
        // }
        
        int count = 0;
        Queue<Coordinate> taskList = new LinkedList<>();
        
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(grid[i][j] == 1){
                    taskList.offer(new Coordinate(i, j));
                }
            }
        }
        
        while(!taskList.isEmpty()){
            count++;
            int size = taskList.size();
            for(int i = 0; i < size; i++){
                Coordinate current = taskList.poll();
                    for(int k = 0; k < 4; k++){
                        int neighborR = current.row + dir[k][0];
                        int neighborC = current.column + dir[k][1];
                        if(neighborR < rows && neighborR > -1 && neighborC < columns && neighborC > -1){
                            if(grid[neighborR][neighborC] == 0){
                                grid[neighborR][neighborC] = 1;
                                taskList.offer(new Coordinate(neighborR, neighborC));
                            }
                        }
                    }
            }
        }
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(grid[i][j] == 0){
                    return -1;
                }
            }
        }
        return --count;
    }
    
    private class Coordinate{
        int row;
        int column;
        public Coordinate(int r, int c){
            row = r;
            column = c;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
