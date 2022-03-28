package algorithm.leetcode.p801to850;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LeetCode843GuessTheWord {
  
  interface Master {
    public int guess(String word);
  }
  
  public void findSecretWord(String[] wordlist, Master master) {
    Random r = new Random();
    for (int i = 0; i < 5; i++) {
      String gue = wordlist[r.nextInt(wordlist.length)];
      int num = master.guess(gue);
      if (num == 6) {
        break;
      }
      List<String> neoWordlist = new ArrayList<>();
      for (String each : wordlist) {
        if (gue != each && match(gue, each) == num) {
          neoWordlist.add(each);
        }
      }
      wordlist = neoWordlist.toArray(new String[neoWordlist.size()]);
    }
  }
  
  private int match(String ori, String com) {
    int count = 0;
    for (int i = 0; i < 6; i++) {
      if (ori.charAt(i) == com.charAt(i)) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
