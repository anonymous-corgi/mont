package jiuzhang.c2.binarysearch;

public class M38Search2DMatrixII {
    public int searchMatrix(int[][] matrix, int target) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
    	    return 0;
    	}
    	return searchMatrix(matrix, target, matrix.length - 1, 0);
    }
//    ����һ�����׵Ĵ��η�ʽ��ͳ���ܸ���
    private int searchMatrix(int[][] matrix, int target, int row, int column) {
    	if(row < 0 || column > (matrix[0].length - 1)){
    	    return 0;
    	}
    	int current = matrix[row][column];
    	if(current > target){
    	    return searchMatrix(matrix, target, row - 1, column);
    	}else if(current < target){
    	    return searchMatrix(matrix, target, row, column + 1);
    	}else{
    	    return 1 + searchMatrix(matrix, target, row - 1, column + 1);
    	}
    }
}
