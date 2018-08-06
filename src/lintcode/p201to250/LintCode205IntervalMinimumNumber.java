package lintcode.p201to250;

import java.util.ArrayList;
import java.util.List;
import basicclass.Interval;

public class LintCode205IntervalMinimumNumber {
  
  private static class SGTNode {
    int min;
    int start;
    int end;
    SGTNode left;
    SGTNode right;
    public SGTNode(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  
  private SGTNode build(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }            
    SGTNode cursor = new SGTNode(start, end);
    if (start == end) {
      cursor.min = nums[start];
    } else {
      int mid = start + ((end - start) >> 1);
      cursor.left = build(nums, start, mid);
      cursor.right = build(nums, mid + 1, end);
      cursor.min = Math.min(cursor.left.min, cursor.right.min);
    }
    return cursor;
  }
  //Should use iStart and iEnd, because they will change in recursion.
  private int searchMin(SGTNode root, int iStart, int iEnd) {
    if (root.start == iStart && root.end == iEnd) {
      return root.min;
    }
    if (root.right.start > iEnd) {
      return searchMin(root.left, iStart, iEnd);
    } else if (root.left.end < iStart) {
      return searchMin(root.right, iStart, iEnd);
    } else {
      int lMin = searchMin(root.left, iStart, root.left.end);
      int rMin = searchMin(root.right, root.right.start, iEnd);
      return Math.min(lMin, rMin);
    }
  }
  
  public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
    // write your code here
    List<Integer> res = new ArrayList<>();
    if (queries == null || queries.size() == 0) {
      return res;
    }
    SGTNode root = build(A, 0, A.length - 1);
    for (int i = 0, iLen = queries.size(); i < iLen; i++) {
      Interval cursor = queries.get(i);
      res.add(searchMin(root, cursor.start, cursor.end));
    }
    return res;
  }

  public static void main(String[] args) {
    LintCode205IntervalMinimumNumber one = new LintCode205IntervalMinimumNumber();
    int[] A = {1,2,7,8,5};
    List<Interval> queries = new ArrayList<>();
    queries.add(new Interval(1, 2));
    queries.add(new Interval(0, 4));
    queries.add(new Interval(2, 4));
    one.intervalMinNumber(A, queries);
  }

}
