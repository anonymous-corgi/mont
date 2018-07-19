package interview.fb.phoneinterview;

public class LeetCode158ReadNCharacters {
  
  private char[] buf4 = new char[4];
  private int head = 0;
  private int tail = 0;
  
  public int read(char[] buf, int n) {
      // Write your code here
      int index = 0;
      while (index < n) {
          if (head == tail) {
              head = 0;
              tail = read4(buf4);
              if (tail == 0) {
                  break;
              }
          }
          while (index < n && head < tail) {
              buf[index++] = buf4[head++];
          }
      }
      return index;
  }
  
  private int read4(char[] buf4) {
    return 0;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
