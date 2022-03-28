package algorithm.interview.visa;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FindTheWinner {
  
  public String winner(List<Integer> andrea, List<Integer> maria, String s) {
    int count = 0;
    int start = "Even".equals(s) ? 0 : 1;
    for (int len = andrea.size(); start < len; start += 2) {
      count += (andrea.get(start) - maria.get(start));
    }
    return count == 0 ? "Tie" : (count > 0 ? "Andrea" : "Maria");
  }
  
  
  @Test
  public void testFindWinner1() {
    List<Integer>  andrea = Arrays.asList(4,5,7);
    List<Integer> maria = Arrays.asList(3,5,6);
    String b = "Even";
    String res = "Andrea";
    assertEquals(res, winner(andrea, maria, b));
  }
  
  @Test
  public void testFindWinner2() {
    List<Integer> andrea = Arrays.asList(1,2,3);
    List<Integer> maria = Arrays.asList(2,1,3);
    String b = "Even";
    String res = "Maria";
    assertEquals(res, winner(andrea, maria, b));
  }

}
