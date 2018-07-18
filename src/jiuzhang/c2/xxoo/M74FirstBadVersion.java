package jiuzhang.c2.xxoo;
//	OOXX
public class M74FirstBadVersion {
    public int findFirstBadVersion(int n) {
        return findFirstBadVersion(1, n, -1);
    }
    
    private int findFirstBadVersion(int head, int end, int lastResult){
        int newResult = lastResult;
        if(head > end){
            return newResult;
        }
        
        int mid = head + (end - head) / 2;
        if(SVNRepo.isBadVersion(mid)){
            newResult = mid;
            return findFirstBadVersion(head, mid - 1, newResult);
        }else{
            return findFirstBadVersion(mid + 1, end, newResult);
        }
    }
	

	
    /*
     *	A previous answer 
     */
//    public int findFirstBadVersion(int n) {
//        if(n < 1){
//            return -1;
//        }
//        return findFirstBadVersion(0, n);
//    }
//    
//    private int findFirstBadVersion(int head, int end){
//        if(head + 1 == end){
//            return end;
//        }
//        
//        int mid = head + (end -head) / 2;
//        if(SVNRepo.isBadVersion(mid)){
//            return findFirstBadVersion(head, mid);
//        }else {
//            return findFirstBadVersion(mid, end);
//        }

    	
/**    	
 * The code for the one that may have no bad version.
 */
//        if(head + 1 < end){
//        	int mid = head + (end -head) / 2;
//            
//        	if(SVNRepo.isBadVersion(mid)){
//                return findFirstBadVersion(head, mid);
//            }else {
//                return findFirstBadVersion(mid, end);
//            }
//        }else if(head + 1 == end && !SVNRepo.isBadVersion(head) && SVNRepo.isBadVersion(end)){
//            return end;
//        }else{
//        	return -1;
//        }
}
