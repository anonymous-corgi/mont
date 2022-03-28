package algorithm.jiuzhang.hardcollection;

public class M944MaximumSubmatrix {
	  int rows, columns;
	  
	  /**
	   * @param matrix: the given matrix
	   * @return: the largest possible sum
	   */
	  public int maxSubmatrix(int[][] matrix) {
	    if (matrix == null || matrix.length == 0) {
	      return 0;
	    }
	    if (matrix[0] == null || matrix[0].length == 0) {
	      return 0;
	    }
	    
	    rows = matrix.length;
	    columns = matrix[0].length;
	    
	    int[][] prefixColSum = getPrefixColSum(matrix);
	    
	    int max = Integer.MIN_VALUE;
	    for (int up = 0; up < rows; up++) {
	      for (int down = up; down < rows; down++) {
	        int[] arr = compression(matrix, up, down, prefixColSum);
	        max = Math.max(max, maximumSubarray(arr));
	      }
	    }
	    
	    return max;
	  };
	  
	  private int maximumSubarray(int[] arr) {
	    int min = 0, max = Integer.MIN_VALUE, sum = 0;
	    
	    for (int i = 0; i < columns; i++) {
	      sum += arr[i];
	      max = Math.max(max, sum - min);
	      min = Math.min(min, sum);
	    }
	    
	    return max;
	  }
	  
	  private int[] compression(int[][] matrix, int up, int down, int[][] prefixColSum) {
	    int[] arr = new int[columns];
	    for (int i = 0; i < columns; i++) {
	      arr[i] = prefixColSum[down + 1][i] - prefixColSum[up][i];
	    }
	    
	    return arr;
	  }
	  
	  private int[][] getPrefixColSum(int[][] matrix) {
	    int[][] sum = new int[rows + 1][columns];
	    
	    for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < columns; j++) {
	        sum[i + 1][j] = sum[i][j] + matrix[i][j];
	      }
	    }
	    
	    return sum;
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
