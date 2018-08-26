package leetcode.p851to900;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class LeetCode855ExamRoom {
  
  public static class ExamRoom {
    
    private final int N;
    private final TreeSet<Integer> seated;
    //empty stores empty intervals.
    private final TreeMap<Integer, TreeSet<Integer>> empty;
    
    public ExamRoom(int N) {
      this.N = N;
      this.seated = new TreeSet<>();
      this.empty = new TreeMap<>();
    }
    
    public int seat() {
      int res = 0;
      if (seated.size() != 0) {
        int first = seated.first(), last = seated.last();
        Integer max = empty.isEmpty() ? null : empty.lastKey();
        //If there is interval we checkout whether two sides have larger space. 
        if (max == null || first >= max / 2 || N - 1 - last > max / 2) {
          if (first >= N - 1 - last) {
            addInterval(0, first);
            res = 0;
          } else {
            addInterval(last, N - 1 - last);
            res = N - 1;
          }
        } else {
          int index = empty.get(max).first();
          int next = seated.higher(index);
          int mid = (next + index) / 2;
          removeInterval(index, max);
          addInterval(index, mid - index);
          addInterval(mid, next - mid);
          res = mid;
        }
      }
      seated.add(res);
      return res;
    }
    
    public void leave(int p) {
      Integer prev = seated.lower(p);
      Integer next = seated.higher(p);
      seated.remove(p);
      if (next != null) {
        removeInterval(p, next - p);
      }
      if (prev != null) {
        removeInterval(prev, p - prev);
        if (next != null) {
          addInterval(prev, next - prev);
        }
      }
    }
    
    private void addInterval(int index, int len) {
      len -= len & 1;  // trim to even number
      empty.putIfAbsent(len, new TreeSet<>());
      empty.get(len).add(index);
    }
    
    private void removeInterval(int index, int len) {
      len -= len & 1;
      Set<Integer> temp = empty.get(len);
      if (temp == null) {
        return;
      }
      temp.remove(index);
      if (temp.size() == 0) {
        empty.remove(len);
      }
    }
    
  }

  public static void main(String[] args) {
    int N = 10;
    LeetCode855ExamRoom.ExamRoom one = 
        new LeetCode855ExamRoom.ExamRoom(N);
    one.seat();
    one.seat();
    one.seat();
    one.seat();
  }

}
