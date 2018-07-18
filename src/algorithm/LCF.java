package algorithm;

public class LCF {
	
	public static int getLCFRecusive(int a, int b) {
		if (a == 0 || b == 0) {
			return -1;
		}
		if (a > b) {
			a = a % b;
			if (a == 0) {
				return b;
			} else {	
				return getLCFRecusive(a, b);
			}
		} else if (a < b){			
			b = b % a;
			if (b == 0) {
				return a;
			} else {
				return getLCFRecusive(a, b);				
			}
		} else {
			return a;
		}
	}
	
	
	public static int getLCFNonRecusive(int a, int b) {
		if (a == 0 || b == 0) {
			return -1;
		}
		do {
			if (b > a) {
				int temp = b;
				b = a;
				a = temp;
			}
			a = a % b;			
		} while (a != 0);
		return b;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 119;
		int b = 34;
		System.out.println(getLCFRecusive(a, b));
		System.out.println(getLCFNonRecusive(a, b));
	}

}
