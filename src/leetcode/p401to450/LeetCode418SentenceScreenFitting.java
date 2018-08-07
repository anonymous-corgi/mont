package leetcode.p401to450;

public class LeetCode418SentenceScreenFitting {
  
  public int wordsTyping(String[] sentence, int rows, int cols) {
    if (sentence == null || sentence.length == 0) {
      return 0;
    }
    int si = 0;
    int count = 0;
    for (int i = 0; i < rows; i++) {
      int j = 0;
      while (j < cols) {
        if (j + sentence[si].length() > cols) {
          break;
        }
        j += sentence[si++].length() + 1;
        if (si == sentence.length) {
          count++;
          si = 0;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    LeetCode418SentenceScreenFitting one 
    = new LeetCode418SentenceScreenFitting();
    String[] sentence = {"a", "bcd", "e"};
    int rows = 3;
    int cols = 6;
    System.out.println(one.wordsTyping(sentence, rows, cols));
  }

}
