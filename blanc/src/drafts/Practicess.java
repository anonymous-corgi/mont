package drafts;

import java.util.concurrent.Executors;

public class Practicess {
  public static void main(String[] args) {
   int[][]matrix2=new int[][] {{2,5},{4,7},{8,1}};
   reverse(matrix2);

  }

  public static void reverse(int[][]matrix){
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j]);
        }
    }
  }
 }