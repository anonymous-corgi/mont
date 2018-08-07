package leetcode.p751to800;

public class LeetCode769MaxChunksToMakeSorted {
  
  public int maxChunksToSorted(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int b = 0;
    int count = 0;
    for (int i = 0; i < arr.length; i++) {            
      if (arr[i] > b) {
        b = arr[i];
      }
      if (i == b) {
        count++;
      }
    }
    return count;
  }
  
//  Before optimization:
//  public int maxChunksToSorted(int[] arr) {
//    if (arr == null || arr.length == 0) {
//      return 0;
//    }
//    int b = 0;
//    int count = 0;
//    for (int i = 0; i < arr.length; i++) {
//      if (i == b && arr[i] <= i) {
//        count++;
//        b++;
//      }
//      if (arr[i] > b) {
//        b = arr[i];
//      }
//    }
//    return count;
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
