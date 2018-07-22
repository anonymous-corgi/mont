package leetcode.contest;

public class LeetCode875KokoEatingBananas {
  
  public int minEatingSpeed(int[] piles, int H) {
    int start = 1;
    int end = piles[0];
    for (int i = 1, len = piles.length; i < len; i++) {
      if (piles[i] > end) {
        end = piles[i];
      }
    }
        
    while (start + 1 < end) {
      int mid = (start + end) / 2;
      if (canFinish(piles, mid, H)) {
        end = mid;
      } else {
        start = mid;
      }            
    }
    
    if (canFinish(piles, start, H)) {
      return start;
    } else {
      return end;
    }
  }
  
  private boolean canFinish(int[] piles, int k, int h) {
    int t = 0;
    for (int i = 0, len = piles.length; i < len; i++) {
      t += (piles[i] % k == 0 ? piles[i] / k : piles[i] / k + 1);
    }
    return t <= h;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
