package interview.google;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RewriteStringIterator implements Iterator<String>{
  
  private String prev;
  private Iterator<String> oriIter;
  
  public RewriteStringIterator(Iterator<String> oriIter) {
    this.oriIter = oriIter;
    this.prev = oriIter.hasNext() ? oriIter.next() : null;
  }
  
  @Override
  public boolean hasNext() {
    return prev != null;
  }
  
  @Override
  public String next() {
    if (prev == null) {
      return null;
    }
    int time = 1;
    String ans = prev;
    String next = oriIter.hasNext() ? oriIter.next() : null;
    while (prev.equals(next)) {
      time++;
      next = oriIter.hasNext() ? oriIter.next() : null;
    }
    prev = next;
    return time > 1 ? ans + time : ans; 
  }
  
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("foo");
    list.add("foo");    
    list.add("foo");
    list.add("bar"); 
    list.add("bar"); 
    list.add("foo");
    RewriteStringIterator one =
        new RewriteStringIterator(list.iterator());
    while (one.hasNext()) {
      System.out.print(one.next() + "\t");
    }
  }

}
