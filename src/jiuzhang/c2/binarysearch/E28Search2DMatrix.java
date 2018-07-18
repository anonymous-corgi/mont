package jiuzhang.c2.binarysearch;

public class E28Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        return searchMatrix(matrix, target, 0, matrix.length*matrix[0].length - 1);
    }
    
    private boolean searchMatrix(int[][] matrix, int target, int head, int end){
        int columns = matrix[0].length;

        if(head > end){
            return false;
        }
        
        int mid = head + (end - head) / 2;
        int current = matrix[findRow(columns, mid)][findColumn(columns, mid)];
        if(current == target){
            return true;
        }else if(current < target){
            return searchMatrix(matrix, target, mid + 1, end);
        }else{
            return searchMatrix(matrix, target, head, mid - 1);
        }
    }
    
    private int findColumn(int columns, int num){
        return num % columns;
    }
    private int findRow(int columns, int num){
        return num / columns;
    }
}
