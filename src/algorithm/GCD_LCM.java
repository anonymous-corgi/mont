package algorithm;

public class GCD_LCM {
  
  //Greatest Common Divisor
  //It needs a wrapper method to handler corner cases like: a or b is 0;
  public static int getGCD(int a, int b) {
    return b == 0 ? a : getGCD(b, a % b);
  }
  
  //Lowest Common Multiple
  public static int getLCM(int a, int b) {
    if (a == 0 || b == 0) {
      return 0;
    }
    return  a * b / getGCD(a, b);
  }
  
  public static int getGCDNonRecusive(int a, int b) {
    if (a == 0 || b == 0) {
      return 0;
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
    int a = 0;
    int b = 18;
    System.out.println(getGCD(a, b));
    System.out.println(getGCDNonRecusive(a, b));
  }
  
}
