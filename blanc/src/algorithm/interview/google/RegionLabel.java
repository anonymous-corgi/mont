package algorithm.interview.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegionLabel {
  //����Skyline,����һ��list (start, end, label), start��inclusive��,end��exclusive��,
  //Ҫ��������������Ĳ�ͬ��region��
  //���磺���� (2, 8, 'a'), (3, 5, 'c'), (6, 9, 'b')�Ļ�, ������û��label�ĵط�,
  //    ������� (2, 3, "a"), (3, 5, "ac"), (5, 6, "a"), (6, 8, "ab"), (8, 9, "b")
  
  private static class Interval {
    int start;
    int end;
    String str;
    public Interval(int start, int end, String str) {
      this.start = start;
      this.end = end;
      this.str = str; 
    }
  }
  
  private static class Node {
    int time;
    Node flag;
    String label;
    public Node(int time, Node flag, String label) {
      this.time = time;
      this.flag = flag;
      this.label = label;
    }
  }
  
  public List<Interval> getRange(List<Interval> intervals) {
    List<Interval> res = new ArrayList<>(); 
    if (intervals == null || intervals.size() == 0) {
      return res;
    }
    Set<Node> tempRes = new HashSet<>();
    List<Node> nodes = new ArrayList<>();
    for (int i = 0, iLen = intervals.size(); i < iLen; i++) {
      Interval cursor = intervals.get(i);
      Node start = new Node(cursor.start, null, cursor.str);
      Node end = new Node(cursor.end, start, cursor.str);
      nodes.add(start);
      nodes.add(end);
    }
    Collections.sort(nodes, (a, b) -> (a.time - b.time));
    int index = 0;
    int len = nodes.size();
    int prevTime = 0;
    while (index < len) {
      int time = nodes.get(index).time;
      
      if (!tempRes.isEmpty()) {
        String str = "";
        for (Node n : tempRes) {
          str += n.label;
        }
        res.add(new Interval(prevTime, time, str));
      }
      
      while (index < len && time == nodes.get(index).time) {
        Node cursor = nodes.get(index++);
        if (cursor.flag == null) {
          tempRes.add(cursor);
        } else {
          tempRes.remove(cursor.flag);
        }
      }
      prevTime = time;
    }
    return res;
  }

  public static void main(String[] args) {
    List<Interval> intervals = new ArrayList<>();
    intervals.add(new Interval(2, 8, "a"));
    intervals.add(new Interval(3, 5, "c"));
    intervals.add(new Interval(6, 9, "b"));
    RegionLabel one = new RegionLabel();
    List<Interval> res = one.getRange(intervals);
    for (int i = 0; i < res.size(); i++) {
      Interval cur = res.get(i);
      System.out.println("(" + cur.start + ", " + cur.end + ", " + cur.str + ")");
    }
  }

}
