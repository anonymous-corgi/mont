package jiuzhang.c4.matrix;

public class M433NumberOfIslands2 {
	
    public int numIslands(boolean[][] grid) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int columns = grid[0].length;
        int rows = grid.length;
        int size = rows * columns;
        
        stub = new int[size];
        for(int i = 0; i < size; i++){
            stub[i] = i;
        }
        int current = -1;
        for(int i = 0;i < grid.length; ++i){
            for(int j = 0;j < grid[0].length; ++j){
            	current++;
                if (grid[i][j]) {
                    count++;
                    if(j + 1 < columns && grid[i][j + 1]){
                    	connect(current, (current + 1)); 
                    }
                    if(i + 1 < rows && grid[i + 1][j]){
                    	connect(current, (current + columns)); 
                    }
//                    if(j > 0 && grid[i][j - 1]){
//                    	connect(current, (current - 1)); 
//                    }                    
//                    if(i > 0 && grid[i - 1][j]){
//                    	connect(current, (current - columns)); 
//                    }
                }
                System.out.print(stub[current]+"\t");
            }
            System.out.println();
        }

        return count;
        
    }
    
    private int count;
    private int[] stub;
    
    private int find(int num){
        if(stub[num] == num){
            return num;
        }
        return stub[num] = find(stub[num]);
    }
    
    private void connect(int from, int to){
        int root_from = find(from);
        int root_to = find(to);
        if(root_from != root_to){
            stub[root_to] = root_from;
            count--;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M433NumberOfIslands2 one = new M433NumberOfIslands2();
		boolean[][] grid = {{true,true,true,true,true,true},
				{true,false,false,false,false,true},
				{true,false,true,true,false,true},
				{true,false,false,false,false,true},
				{true,true,true,true,true,true}};
		System.out.println(one.numIslands(grid));
		
		

	}

}
