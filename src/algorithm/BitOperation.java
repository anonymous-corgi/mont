package algorithm;

public class BitOperation {
  
//  Set the last (right) 1-bit of a positive integer to 0-bit 
//  Examples:
//  00100101 -> 00100100
//  00100100 -> 00100000
//  00100000 -> 00000000
  public static int op1(int num) {
    return num > 0 ? num & (num - 1) : 0;
  }
  
//  Set all 1-bits after the last 0-bit to 0-bit
//  Examples:
//  00100101 -> 00100100
//  00100100 -> 00100100
//  00100111 -> 00100000
  public static int op2(int num) {
    return num > 0 ? num & (num + 1) : 0;
  }
  
//  Set all 1-bits except the last one to 0-bit 
//  Examples:
//  00100101 -> 00000001
//  00100100 -> 00000100
//  00100111 -> 00000001
  public static int op3(int num) {
    return num > 0 ? (num & -num) : 0; 
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int num = 20;
    System.out.println(op3(num));
  }

}
