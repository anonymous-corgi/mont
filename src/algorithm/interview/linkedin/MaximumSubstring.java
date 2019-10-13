package algorithm.interview.linkedin;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class MaximumSubstring {
  
  public String getMaxSubstring(String s) {
    char[] chs = s.toCharArray();
    char maxChar = 'a';
    List<String> maxIndex = new ArrayList<>(); 
    for (int i = 0; i < chs.length; i++) {
      if (chs[i] > maxChar) {
        maxChar = chs[i];
        maxIndex.clear();
        maxIndex.add(s.substring(i, s.length()));
      } else if (chs[i] == maxChar){
        if (i == 0 || chs[i - 1] != maxChar) {
          maxIndex.add(s.substring(i, s.length()));
        }
      }
    }
    Collections.sort(maxIndex);
    return maxIndex.get(maxIndex.size() - 1);
  }
  
//  public String getMaxSubstring(String s) {
//    char[] chs = s.toCharArray();
//    char maxChar = 'a';
//    List<Integer> maxIndex = new ArrayList<>(); 
//    for (int i = 0; i < chs.length; i++) {
//      if (chs[i] > maxChar) {
//        maxChar = chs[i];
//        maxIndex.clear();
//        maxIndex.add(i);
//      } else if (chs[i] == maxChar){
//        if (i == 0 || chs[i - 1] != maxChar) {
//          maxIndex.add(i);
//        }
//      }
//    }
//    int w = 0;
//    while (maxIndex.size() > 1) {
//      List<Integer> neo = new ArrayList<>();
//      maxChar = chs[maxIndex.get(0)];
//      for (int i : maxIndex) {
//        int ri = i + w;
//        if (ri != chs.length) {
//          if (chs[ri] > maxChar) {
//            maxChar = chs[ri];
//            neo.clear();
//            neo.add(i);
//          } else if (chs[ri] == maxChar) {
//            neo.add(i);
//          }
//        }
//        maxIndex = neo;
//        w++;
//      }
//    }
//    return s.substring(maxIndex.get(0), s.length());
//  }
  
  
  
  @Test
  public void testGetMaxSubstring1() {
    String s = "ggaggggg";
    String res = "ggggg";
    assertEquals(res, getMaxSubstring(s));
  }  
  
  @Test
  public void testGetMaxSubstring2() {
    String s = "ggaggggg";
    String res = "ggggg";
    assertEquals(res, getMaxSubstring(s));
  }
  
  public static void main(String[] args) {
    String s = "ggaggggg";
    MaximumSubstring one =
        new MaximumSubstring();
    System.out.println(one.getMaxSubstring(s));
  }

}
