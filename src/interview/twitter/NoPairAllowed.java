package interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class NoPairAllowed {
  
  public List<Integer> getMinOperations(List<String> words) {
    List<Integer> res = new ArrayList<>();
    for (String word : words) {
      res.add(getMinOperations(word));
    }
    return res;
  }
  
  private int getMinOperations(String s) {
    int count = 0;
    char prev = s.charAt(0);
    boolean prevMod = false;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == prev) {
        if (!prevMod) {
          count++;
        }
        prevMod = !prevMod;
      } else {
        prev = s.charAt(i);
        prevMod = false;
      }
    }
    return count;
  }
  
  
  @Test
  public void testGetStopPoint() {
    List<String> words = Arrays.asList("ab", "aab", "abb", "abab", "abaaba");
    List<Integer> testRes = getMinOperations(words);
    int[] res = {0, 1, 1, 0, 1};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], (int) testRes.get(i));
    }
  }

}
