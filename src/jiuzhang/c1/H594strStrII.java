package jiuzhang.c1;

public class H594strStrII {
	
    public int strStr2(String source, String target) {
        // write your code here
        if(source == null ||  target == null) {
            return -1;
        }
        if(source.length() == 0){
            if(target.length() == 0){
                return 0;
            }
            return -1;
        }
        if (source.length() < target.length()) {
            return -1;
        }
        for(int i = 0; i < target.length() - 1; i++){
            primePower = ((primePower * prime) % capacity + capacity) % capacity;
        }
        int codeT = hashcode(target);
        int codeS = hashcode(source.substring(0, target.length()));
        for(int i = 0; i < source.length() - target.length() + 1; i++){
            if(codeT == codeS){
                return i;
            }else{
                if(i == source.length() - target.length()){
                    break;
                }
                codeS = nextHashcode(codeS, source.charAt(i), source.charAt(i + target.length()));
            }
        }
        return -1;
    }
    
    private final int capacity = 100000;
    private final int prime = 33;
    private int primePower = 1;
    
    private int hashcode(String str){
        int result = 0;
        for(int i = 0; i < str.length(); i++){
            result = (result * prime % capacity + (str.charAt(i) - 'a')) % capacity; 
        }
        if(result < 0){
            result += capacity;
        }
        return result;
    }
    
    private int nextHashcode(int num, char prev, char next){
        num = ((num - (prev - 'a') * primePower) * prime % capacity + (next - 'a')) % capacity;
        if(num < 0){
            num += capacity; 
        }
        return num;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = new int[2][2];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				System.out.print(arr[i][j]);
			}
		}
	}
	
	
//	    public int strStr(String haystack, String needle) {
//    if (haystack == null || needle == null) {
//        return -1;
//    }
//    int hLen = haystack.length();
//    int nLen = needle.length();
//    if (hLen < nLen) {
//        return -1;
//    }
//    if (nLen == 0 && hLen == 0) {
//        return 0;
//    }
//    for (int i = 0; i < nLen - 1; i++) {
//        power = (power * prime) % capacity;
//    }
//    long target = hashcode(needle);
//    long source = hashcode(haystack.substring(0, nLen));
//    for (int i = 0, len = hLen - nLen; i <= len; i++) {
//        if (target == source) {
//            return i;
//        } else if (i != len) {
//            source = nextHashcode(source, haystack.charAt(i), haystack.charAt(i + nLen));
//        } 
//    }
//    return -1;
//}
//
//private long power = 1;
//private final long prime = 33;
//private final long capacity = (long) 1E7;
//
//private long nextHashcode(long ori, char prevChar, char nextChar) {
//    long code = ori - ((prevChar - 64) * power % capacity);
//    code = (code * prime + nextChar - 64) % capacity;
//    return code;
//}
//
//private long hashcode(String str) {
//    long code = 0;
//    for (int i = 0, len = str.length(); i < len; i++) {
//        code = code * prime + str.charAt(i) - 64;
//    }
//    return code;
//}
}
