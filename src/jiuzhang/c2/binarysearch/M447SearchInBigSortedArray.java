package Jiuzhang.C2.BinarySearch;

import Jiuzhang.C2.XXOO.SVNRepo;

public class M447SearchInBigSortedArray {
	
    public int searchBigSortedArray(SVNRepo reader, int target) {
        
        if(reader == null || reader.get(0) == 2147483647){
            return -1;
        }
        return searchBigSortedArray(reader, target, 0, 1, -1);
    }
    private int searchBigSortedArray(SVNRepo reader, int target, int head, int end, int lastResult){
    		int newResult = lastResult;
    		if(head > end){
    			return newResult;
    		}
    		int mid = head + (end - head) / 2;
    		int current = reader.get(mid - 1);
    		if(current < target){
    			if(reader.get(end) > target){
    				return searchBigSortedArray(reader, target, mid + 1, end, newResult);
    			}else{
    				return searchBigSortedArray(reader, target, mid + 1, end * 2, newResult);
    			}
    		}else if(current == 2147483647 || current > target){
    			return searchBigSortedArray(reader, target, head, mid - 1, newResult);
    		}else{
    			newResult = mid - 1;
    			return searchBigSortedArray(reader, target, head, mid - 1, newResult);
    		}
    }
    

//    private int searchBigSortedArray(ArrayReader reader, int target, int head, int end, int result){
//        int newResult = result;
//        int current = reader.get(end - 1);
//        
//        if(head < end){
//            if(current == target){
//                newResult = end - 1;
//                end = (head + end) / 2;
//                return searchBigSortedArray(reader, target, head, end, newResult);
//            }else if(current == 2147483647 || current > target){
//                end = (head + end) / 2;
//                return searchBigSortedArray(reader, target, head, end, newResult);
//            }else{
//                return searchBigSortedArray(reader, target, end, end * 2, newResult);
//            }
//            
//        }else{
//            return newResult;
//        }
//    }
}
