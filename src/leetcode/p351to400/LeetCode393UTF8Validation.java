package leetcode.p351to400;

import utils.Converter;

public class LeetCode393UTF8Validation {
  
  public boolean validUtf8(int[] data) {
    if (data == null || data.length == 0) {
      return false;
    }
    int last = 0;
    for (int i = 0; i < data.length; i++) {
      int index = 0;
      int bit = 1 << 7;
      while ((data[i] & bit) != 0) {
        index++;
        bit >>= 1;
      }
      if (index > 4) {
        return false;
      } else if (last <= 1 && index != 1 ) {
        last = index;
      } else if (last > 1 && index == 1){
        last--;
      } else {
        return false;
      }
    }
    return last <= 1;
  }

  public static void main(String[] args) {
    LeetCode393UTF8Validation one 
    = new LeetCode393UTF8Validation();
    int[] data = {250,145,145,145,145};
    for (int i = 0; i < data.length; i++) {
      System.out.println(Converter.convertToBinary(data[i]));
    }
    System.out.println(one.validUtf8(data));
  }

}
