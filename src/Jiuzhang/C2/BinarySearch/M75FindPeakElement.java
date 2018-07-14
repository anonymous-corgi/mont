package Jiuzhang.C2.BinarySearch;

public class M75FindPeakElement {
    public int findPeak(int[] A) {
        return findPeak(A, 0, A.length - 1);
    }
    
    private int findPeak(int[] A, int head, int end){
        if(head + 2 > end){
            return -1;
        }
        
        int mid = head + (end - head) / 2;
        if(A[mid - 1] > A[mid]){
            return findPeak(A, head, mid);
        }else if(A[mid] < A[mid + 1]){
            return findPeak(A, mid, end);
        }else{
            return mid;
        }
    }
	
	
//    public int findPeak(int[] A) {
//        // write your code here
//        int start = 1, end = A.length-2; // 1.答案在之间，2.不会出界 
//        while(start + 1 <  end) {
//            int mid = (start + end) / 2;
//            if(A[mid] < A[mid - 1]) {
//                end = mid;
//            } else if(A[mid] < A[mid + 1]) {
//                start = mid;
//            } else {
//                end = mid;
//            }
//        }
//        if(A[start] < A[end]) {
//            return end;
//        } else { 
//            return start;
//        }
//    }

}
