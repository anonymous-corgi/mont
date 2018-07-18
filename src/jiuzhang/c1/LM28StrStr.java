package Jiuzhang.C1;

public class LM28StrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int hLen = haystack.length();
        int nLen = needle.length();
        if (hLen < nLen) {
            return -1;
        }
        if (nLen == 0 && hLen == 0) {
            return 0;
        }
        for (int i = 0; i < nLen - 1; i++) {
            power = (power * prime) % capacity;
        }
        long target = hashcode(needle);
        long source = hashcode(haystack.substring(0, nLen));
        for (int i = 0, len = hLen - nLen; i <= len; i++) {
            if (target == source) {
                return i;
            } else if (i != len) {
                source = nextHashcode(source, haystack.charAt(i), haystack.charAt(i + nLen));
            } 
        }
        return -1;
     }

     private long power = 1;
     private final long prime = 33;
     private final long capacity = (long) 1E9;

     private long nextHashcode(long ori, char prevChar, char nextChar) {
        long code = ori - ((prevChar - 64) * power % capacity);
        code = (code * prime + nextChar - 64) % capacity;
        //Remember to check if code is negative, because you operated minus previously.
        return code < 0 ? code + capacity : code;
     }

     private long hashcode(String str) {
        long code = 0;
        for (int i = 0, len = str.length(); i < len; i++) {
            code = (code * prime + str.charAt(i) - 64) % capacity;
        }
        return code;
     }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LM28StrStr one = new LM28StrStr();
		String haystack = "aabaaabaaac";
		String needle = "aabaaac";
		
		
		System.out.println(+one.strStr(haystack, needle));

	}

}
