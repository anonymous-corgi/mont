package interview.fb;

public class LintCode1305IntegerToEnglishWords {
	
    private final static String[][] LI = {{"", " One", " Two", " Three", "Four",
    	" Five", " Six", " Seven", " Eight", " Nine"}
    , {"", " Ten", " Twenty", " Thirty", " Fourty", 
    	" Fivety", " Sixty", " Seventy", " Eighty", "Ninty"}
    , {"", "One Hundred", "Two Hundred", "Three Hundred", "Four Hundred"
    	, "Five Hundred", "Six Hundred", "Seven Hundred", "Eight Hundred", "Nine Hundred"}};
    
    private final static String[] HI = {"", " Thousand", " Million", " Billion", " Trillion"};
    
    public String numberToWords(int num) {
        // Write your code here
        if (num < 0) {
            return "";
        }
        if (num == 0) {
            return "zero";
        }
        int li = 0;
        int hi = 0;
        StringBuilder sb = new StringBuilder();
        while(num != 0) {
            if (li % 3 == 0) {
                li = 0;
                sb.insert(0, HI[hi]);
                hi++;
            } 
            int cursor = num % 10;
            sb.insert(0, LI[li][cursor]);
            num /= 10;
            li++;
        }
        if (sb.charAt(0) == ' ') {
            sb.deleteCharAt(0);
        } 
        return sb.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LintCode1305IntegerToEnglishWords one = new LintCode1305IntegerToEnglishWords();
		int num = 101;
		System.out.println(one.numberToWords(num));

	}

}
