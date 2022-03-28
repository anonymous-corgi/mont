package algorithm.interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class TheHuffmanDecoder {
  
  public String huffmanDecoder(String[] dict, String input) {
    Map<String, String> map = new HashMap<>();
    List<Integer> lenList = new ArrayList<>();
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < dict.length; i++) {
      String[] pair = dict[i].split(" ");
      if ("newline".equals(pair[0])) {
        map.put(pair[1], "\n");
      } else {
        map.put(pair[1], pair[0]);
      }
      int len = pair[1].length();
      if (!lenList.contains(len)) {
        lenList.add(len);
      }
    }
    Collections.sort(lenList);
    int head = 0;
    int tail = lenList.get(0);
    int LEN = input.length();
    while (head < LEN) {
      for (Integer l : lenList) {
        tail = head + l;
        if (tail > LEN) { break; }
        String code = map.get(input.substring(head, tail));
        if (code != null) {
          res.append(code);
          break;
        }
      }
      head = tail;
    }
    
    return res.toString();
  }
  
  
  @Test
  public void testHuffmanDecoder1() {
    String[] dict = {"a 100100", "b 100101", "c 110001", "d 100000", "newline 111111", "p 111110", "q 000001"};
    String input = "111110000001100100111111100101110001111110";   
    String res = "pqa\nbcp";
    assertEquals(res, huffmanDecoder(dict, input));
  }
  
  @Test
  public void testHuffmanDecoder2() {
    String[] dict = {"a 100100", "b 100101", "c 110001", "d 100000", "newline 1111111", "p 111110", "q 000001"};
    String input = "1111100000011001001111111100101110001100000";
    String res = "pqa\nbcd";
    assertEquals(res, huffmanDecoder(dict, input));
  }


  public static void main(String[] args) {
    TheHuffmanDecoder one = new TheHuffmanDecoder();
    String[] dict = {"a 100100", "b 100101", "c 110001", "d 100000", "newline 1111111", "p 111110", "q 000001"};
    String input = "1111100000011001001111111100101110001100000";
    System.out.println(one.huffmanDecoder(dict, input));
  }

}
