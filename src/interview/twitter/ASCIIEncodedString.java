package interview.twitter;

import static org.junit.Assert.*;
import org.junit.Test;

public class ASCIIEncodedString {
  
  public String getEncodedString(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      sb.append((int) c);
    }
    sb.reverse();
    return sb.toString();
  }
  
  public String getDecodedString (String s) {
    s = new StringBuilder(s).reverse().toString();
    char[] chs = s.toCharArray();
    int cursor = 0;
    StringBuilder sb = new StringBuilder();
    while (cursor < chs.length) {
      int len = (chs[cursor] == '1' ? 3 : 2);
      sb.append((char) Integer.parseInt(s.substring(cursor, cursor + len)));
      cursor += len;
    }
    return sb.toString();
  }
  
  
  @Test
  public void testGetEncodedString() {
    String code = "23511011501782351112179911801562340161171141148";
    String s = "Hacker Rank";
//    assertEquals(s, getDecodedString(getEncodedString(s)));
    assertEquals(s, getDecodedString(code));
  }

  public static void main(String[] args) {
    ASCIIEncodedString one = new ASCIIEncodedString();

    String code = "23511011501782351112179911801562340161171141148";
    System.out.println(one.getDecodedString(code));
  }

}
