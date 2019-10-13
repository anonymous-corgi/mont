package algorithm.jiuzhang.c1;

public class C1strStr {
    private final int BASE = 1000000;
    public int strStr(String source, String target) {
        // write your code here
		if(source == null || target == null) {
			return -1;
		}else if(target.length() == 0){
		    return 0;
		}
		int tLength = target.length();
		int sLength = source.length();
		
		int power = 1;
		for(int i = 0; i < tLength - 1; i++) {
			power = (31 * power) % BASE;
		}

		int targetHashcode = 0;
		for(int i = 0; i < tLength; i++){
			targetHashcode = (targetHashcode * 31 +target.charAt(i)) % BASE;
		}
		
		int sourceHashcode = 0;
		for(int i = 0; i < sLength; i++){
			if (i < tLength){
				sourceHashcode = (sourceHashcode * 31 + source.charAt(i)) % BASE;
			}else {
				sourceHashcode = ((sourceHashcode - source.charAt(i-tLength) * power % BASE) * 31  + source.charAt(i)) % BASE;
				
			}
			if(sourceHashcode < 0){
			    sourceHashcode += BASE;
			}
			if (sourceHashcode == targetHashcode){
				if(source.substring(i - tLength + 1, i + 1).equals(target)){
					return i - tLength + 1;
				}
			}
		}
		return -1;
    }
	
//	private int nextHashcode(int pre, String str) {
//		int result = pre;
//		for(int i = 0; i < str.length(); i++) {
//			result = nextHashcode(result, str.charAt(i));
//		}
//		return result;
//	}
//    private int nextHashcode(int pre, int del, int add){
//    	int result;
//    	result = (pre - power * del %) - 
//    }
}
