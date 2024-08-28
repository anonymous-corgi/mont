package com.anonymouscorgi.karakoram.jiuzhang.c2.xxoorange;

public class M61SearchForRange {
    public int[] searchRange(int[] A, int target) {
    	int[] result = {-1, -1};
        if(A == null || A.length == 0){
            return result;
        }
        
        result[0] = searchFirst(A, target, 0, A.length - 1, -1);
        result[1] = searchLast(A, target, result[0], A.length - 1, -1);
        return result;
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
