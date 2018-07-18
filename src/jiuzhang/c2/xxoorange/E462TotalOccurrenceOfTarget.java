package jiuzhang.c2.xxoorange;

public class E462TotalOccurrenceOfTarget {
    public int totalOccurrence(int[] A, int target) {
        if(A == null || A.length == 0){
            return 0;
        } 
        int[] result = new int[2];
        result[0] = searchFirst(A, target, 0, A.length - 1, -1);
        
        if(result[0] == -1){
            return 0;
        }else{
            result[1] = searchLast(A, target, result[0], A.length - 1, -1);
            return result[1] - result[0] + 1;
        }
    }
        private int searchFirst(int[] A, int target, int head, int end, int lastResult){
        if(head > end){
            return lastResult;
        }
        
        int mid = head + (end - head) / 2;
        if(A[mid] > target){
            return searchFirst(A, target, head, mid - 1, lastResult);
        }else if(A[mid] < target){
            return searchFirst(A, target, mid + 1, end, lastResult);
        }else{
            return searchFirst(A, target, head, mid - 1, mid);
        }
    }
    
        private int searchLast(int[] A, int target, int head, int end, int lastResult){
        if(head > end){
            return lastResult;
        }
        
        int mid = head + (end - head) / 2;
        if(A[mid] > target){
            return searchLast(A, target, head, mid - 1, lastResult);
        }else if(A[mid] < target){
            return searchLast(A, target, mid + 1, end, lastResult);
        }else{
            return searchLast(A, target, mid + 1, end, mid);
        }
    }
}
