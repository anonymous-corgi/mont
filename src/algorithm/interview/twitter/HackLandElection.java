package algorithm.interview.twitter;

import static org.junit.Assert.assertEquals;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

public class HackLandElection {
  
  public String hackerlandElection(String[] votes) {
    Map<String, Integer> countMap = new TreeMap<>(Collections.reverseOrder());
    for (String vote : votes) {
        countMap.put(vote, countMap.getOrDefault(vote, 1) + 1);
    }

    return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
  }  
  
  @Test
  public void testHackerlandElection1() {
    String[] votes = {"Joe", "Mary", "Joe", "Mary"};
    String res = "Mary";
    assertEquals(res, hackerlandElection(votes));
  }
  
  @Test
  public void testHackerlandElection2() {
    String[] votes = {"victor", "veronica", "ryan", "dave", "maria", "farah", "farah", "ryan", "veronica"};
    String res = "veronica";
    assertEquals(res, hackerlandElection(votes));
  }  
  
  @Test
  public void testHackerlandElection3() {
    String[] votes = {"Alex", "Michael", "Harry", "Dave", "Michael", "Victor", "Harry", "Alex", "Mary", "Mary"};
    String res = "Michael";
    assertEquals(res, hackerlandElection(votes));
  }

  public static void main(String[] args) {
    String[] votes = {"Alex", "Michael", "Harry", "Dave", "Michael", "Victor", "Harry", "Alex", "Mary", "Mary"};
    HackLandElection one = new HackLandElection();
    System.out.println(one.hackerlandElection(votes));
  }

}
