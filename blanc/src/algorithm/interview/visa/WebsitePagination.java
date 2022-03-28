package algorithm.interview.visa;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

public class WebsitePagination {
  
  public String[] fetchItemsToDisplay(List<String[]> items, int sortParameter, int sortOrder, int x, int y) {
    Comparator<String[]> comparator = new Comparator<String[]>(){
      final int sign = (sortOrder == 0 ? 1 : -1);
      @Override
      public int compare(String[] item1, String[] item2) {
        if (sortParameter == 0) {
          return sign * item1[sortParameter].compareTo(item2[sortParameter]);
        } else {
          return sign * (Integer.parseInt(item1[sortParameter]) - Integer.parseInt(item2[sortParameter]));
        }
      }
    };
      
    Collections.sort(items, comparator);
    List<String> res = new ArrayList<>();
    int index = x * y;
    int iLen = Math.min(index + x, items.size()); 
    while (index < iLen) {
      res.add(items.get(index++)[0]);
    }
    
    return res.toArray(new String[0]);
  }
  
  public String[] fetchItemsToDisplay(String[][] items, int sortParameter, int sortOrder, int x, int y) {
    Comparator<String[]> comparator = new Comparator<String[]>(){
      final int sign = (sortOrder == 0 ? 1 : -1);
      @Override
      public int compare(String[] item1, String[] item2) {
        if (sortParameter == 0) {
          return sign * item1[sortParameter].compareTo(item2[sortParameter]);
        } else {
          return sign * (Integer.parseInt(item1[sortParameter]) - Integer.parseInt(item2[sortParameter]));
        }
      }
    };
      
    Arrays.sort(items, comparator);
    List<String> res = new ArrayList<>();
    int index = x * y;
    int iLen = Math.min(index + x, items.length); 
    while (index < iLen) {
      res.add(items[index++][0]);
    }
    
    return res.toArray(new String[0]);
  }
  
  
  @Test
  public void testFetchItemsToDisplay1() {
    String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
    int sortParameter = 1;
    int sortOrder = 0;
    int x = 2;
    int y = 1;
    String[] res = {"item3"};
    assertArrayEquals(res, fetchItemsToDisplay(items, sortParameter, sortOrder, x, y));
  }
  
  @Test
  public void testFetchItemsToDisplay2() {
    String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
    int sortParameter = 0;
    int sortOrder = 1;
    int x = 2;
    int y = 1;
    String[] res = {"item1"};
    assertArrayEquals(res, fetchItemsToDisplay(items, sortParameter, sortOrder, x, y));
  }  
  
  @Test
  public void testFetchItemsToDisplay3() {
    String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
    int sortParameter = 0;
    int sortOrder = 1;
    int x = 2;
    int y = 0;
    String[] res = {"item3", "item2"};
    assertArrayEquals(res, fetchItemsToDisplay(items, sortParameter, sortOrder, x, y));
  }  
  
  @Test
  public void testFetchItemsToDisplay4() {
    String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
    int sortParameter = 2;
    int sortOrder = 1;
    int x = 2;
    int y = 0;
    String[] res = {"item1", "item3"};
    assertArrayEquals(res, fetchItemsToDisplay(items, sortParameter, sortOrder, x, y));
  }
  
//  @Test
//  public void testFetchItemsToDisplay() {
//    String[][] items = {{"", "", ""}, {"", "", ""}, {"", "", ""}};
//    int sortParameter = 1;
//    int sortOrder = 0;
//    int x = 2;
//    int y = 1;
//    String[] res = {""};
//    assertArrayEquals(res, fetchItemsToDisplay(items, sortParameter, sortOrder, x, y));
//  }

  public static void main(String[] args) {
    
    String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
    int sortParameter = 1;
    int sortOrder = 0;
    int x = 2;
    int y = 1;
    
    WebsitePagination one = new WebsitePagination();
    System.out.println(Arrays.deepToString(one.fetchItemsToDisplay(items, sortParameter, sortOrder, x, y)));
  }

}
