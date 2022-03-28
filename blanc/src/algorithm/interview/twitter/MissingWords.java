package algorithm.interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MissingWords {
  
  public List<String> missingWords(String s, String t) {
    String[] ss = s.split(" ");
    String[] ts = t.split(" ");
    List<String> res = new ArrayList<>();
    
    int sIndex = 0;
    int tIndex = 0;
    while (tIndex < ts.length) {
        while (sIndex < ss.length) {
            if (ts[tIndex].equals(ss[sIndex])) {
                sIndex++;
                tIndex++;
                break;
            } else {
                res.add(ss[sIndex++]);
            }
        }
    }
    while (sIndex < ss.length) {
        res.add(ss[sIndex++]);
    }
    
    return res;
  }
  
  
  @Test
  public void testMissingWords() {
    String s = "I am using HackerRank to improve programming";
    String t = "am HackerRank to improve";
    List<String> testRes = missingWords(s, t);
    String[] res = {"I", "using", "programming"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  
  @Test
  public void testMissingWords2() {
    String s = "I love programming";
    String t = "programming";
    List<String> testRes = missingWords(s, t);
    String[] res = {"I", "love"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }

  public static void main(String[] args) {
    String s = "I am using HackerRank to improve programming";
    String t = "";
    MissingWords one = new MissingWords();
    System.out.println(one.missingWords(s, t));
  }

}
