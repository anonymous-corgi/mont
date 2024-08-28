package com.anonymouscorgi.karakoram.jiuzhang.c7.twopointers;

public class E415ValidPalindrome {
	
    public boolean isPalindrome(String s) {
        // write your code here
        if(s == null || s.length() == 0){
            return true;
        }
        s = s.replaceAll("\\W", "");
        s = s.toLowerCase();
        int len = s.length();
        for(int i = 0; i < len/2 ; i++){
            if(s.charAt(i) != s.charAt(len - 1 -i)){
                return false;
            }
        }
        return true;
    }
    
	public static void main(String[] args) {
		String str = "A man, a plan, a canal: Panama";
		E415ValidPalindrome one = new E415ValidPalindrome();
		System.out.println(one.isPalindrome(str));
	}

}