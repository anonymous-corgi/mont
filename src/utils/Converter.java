package utils;

public class Converter {
  
  public static String convertToBinary(int num) {
    final int GAP = 8;
    int index = 0;
    StringBuilder sb = new StringBuilder();
    if (num < 0) {
      while (index < 31) {
        if (num < 0) {
          sb.insert(0, (num % 2 == 0 ? '1' : "0"));
          num /= 2;
        } else {
          sb.insert(0, '1');
        }
        index++;      
        if (index % GAP == 0) {
          sb.insert(0, ',');
        }
      }
      sb.insert(0, '1');
    } else {      
      while (index < 31) {
        if (num > 0) {
          sb.insert(0, (num % 2));
          num /= 2;
        } else {
          sb.insert(0, '0');
        }
        index++;      
        if (index % GAP == 0) {
          sb.insert(0, ',');
        }
      }
      sb.insert(0, '0');
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Converter one = new Converter();
    int num = -8;
    System.out.println(one.convertToBinary(num));

  }

}
