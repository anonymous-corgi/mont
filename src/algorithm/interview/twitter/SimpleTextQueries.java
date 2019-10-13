package algorithm.interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class SimpleTextQueries {
  
  public List<String> simplifyText(String[] sentences, String[] queries) {
    List<String> res = new ArrayList<>(queries.length + 1);
    Map<String, Map<Integer, Integer>> dict = new HashMap<>();
    for (int i = 0; i < sentences.length; i++) {
      for (String word : sentences[i].split(" ")) {
        Map<Integer, Integer> timesMap;
        if (!dict.containsKey(word)) {
          timesMap = new HashMap<>(20);
          dict.put(word, timesMap);
        } else {
          timesMap = dict.get(word);
        }
        timesMap.put(i, timesMap.getOrDefault(i, 0) + 1);
      }
    }
    for (String query : queries) {
      String[] words = query.split(" ");
      Map<Integer, Integer> minTimes = new HashMap<>(dict.get(words[0]));
      for (int i = 1; i < words.length; i++) {
        Map<Integer, Integer> times = dict.get(words[i]);
        for (Map.Entry<Integer, Integer> entry : minTimes.entrySet()) {
          int cTime = Math.min(times.getOrDefault(entry.getKey(), 0), entry.getValue());
          entry.setValue(cTime);
        }
      }
      List<int[]> list = new ArrayList<>(10);
      for (Map.Entry<Integer, Integer> entry : minTimes.entrySet()) {
        if (entry.getValue() > 0) {
          list.add(new int[]{entry.getKey(), entry.getValue()});
        }
      }
      Collections.sort(list, (a, b) -> a[0] - b[0]);
      StringBuilder sb = new StringBuilder();
      for (int[] each : list) {
        for (int i = 0; i < each[1]; i++) {
          sb.append(each[0] + " ");
        }
      }
      String resStr = sb.length() == 0 ? "-1" : sb.substring(0, sb.length() - 1);
      res.add(resStr);
    }
    
    return res;
  }
  
  
  @Test
  public void testSimplifyText1() {
    String[] sentences = {"bob and alice like to text each other", "bob does not like to ski", "alice likes to ski"};
    String[] queries = {"bob alice", "alice", "like"};
    List<String> testRes = simplifyText(sentences, queries);
    String[] res = {"0", "0 2", "0 1"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  @Test
  public void testSimplifyText2() {    
    String[] sentences = {"jim likes mary", "kate likes tom", "tom does not like jim"};
    String[] queries = {"jim tom", "likes"};
    List<String> testRes = simplifyText(sentences, queries);
    String[] res = {"2", "0 1"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  @Test
  public void testSimplifyText3() {
    String[] sentences = {"how it was done", "are you how", "it goes to", "goes done are it"};
    String[] queries = {"done it", "it"};
    List<String> testRes = simplifyText(sentences, queries);
    String[] res = {"0 3", "0 2 3"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  @Test
  public void testSimplifyText4() {
    String[] sentences = {"it go will away", "go do it", "what to will east"};
    String[] queries = {"it will", "go east will", "will"};
    List<String> testRes = simplifyText(sentences, queries);
    String[] res = {"0", "-1", "0 2"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  @Test
  public void testSimplifyText5() {
    String[] sentences = {"bob alice bob alice bob alice"};
    String[] queries = {"bob alice"};
    List<String> testRes = simplifyText(sentences, queries);
    String[] res = {"0 0 0"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  @Test
  public void testSimplifyText6() {
    //printList(textQueries(sentences6, phrases6));
    String[] sentences = {"bob alice bob alice bob alice"};
    String[] queries = {"bob alice bob alice"};
    List<String> testRes = simplifyText(sentences, queries);
    String[] res = {"0", "0 2", "0 1"};
    for (int i = 0; i < res.length; i++) {
      assertEquals(res[i], testRes.get(i));
    }
  }
  
  
  public static void main(String[] args) {
    String[] sentences = {"bob and alice like to text each other", "bob does not like to ski", "alice likes to ski"};
    String[] queries = {"bob alice", "alice", "like"};
    SimpleTextQueries one = new SimpleTextQueries();
    System.out.println(one.simplifyText(sentences, queries));
  }

}
