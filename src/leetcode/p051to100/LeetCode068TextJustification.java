package leetcode.p051to100;

import java.util.ArrayList;
import java.util.List;
import utils.Print;

public class LeetCode068TextJustification {
  
  public List<String> fullJustify(String[] words, int maxWidth) {
    int index = 0;
    List<String> res = new ArrayList<>();
    while (index < words.length) {
      int last = index;
      int width = maxWidth;
      StringBuilder sb = new StringBuilder();
      while (last < words.length && width >= words[last].length()) {
        width -= (words[last++].length() + 1);
      }
      if (last != words.length) {
        int gap = Math.max(1, last - index - 1);
        int space = last - index + width;
        sb.append(words[index]);
        for (int i = index + 1; i < last; i++) {
          int thisSpace = space % gap != 0 ? space / gap-- + 1 : space / gap--;
          space -= thisSpace;
          addSpace(sb, thisSpace);
          sb.append(words[i]);
        }
      } else {
        sb.append(words[index]);
        for (int i = index + 1; i < last; i++) {
          addSpace(sb, 1);
          sb.append(words[i]);
        }
      }
      addSpace(sb, maxWidth - sb.length());
      res.add(sb.toString());
      index = last;
    }
    return res;
  }
  
  private void addSpace(StringBuilder sb, int num) {
    for (int i = 0; i < num; i++) {
      sb.append(" ");
    }
  }

  public static void main(String[] args) {
//    String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//    int maxWidth = 16;
    String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
    int maxWidth = 20;
    LeetCode068TextJustification one =
        new LeetCode068TextJustification();
    Print.printList(one.fullJustify(words, maxWidth));
  }

}
