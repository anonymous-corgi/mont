package algorithm.interview.google;

public class SplitPills {
  
  /**
   * Suppose you have a pill container, and there are two kinds of pills inside the container.
   * One is full pill and another is half pill. A full pill can be split into two half pills.
   * Each time, you randomly pick a pill from the container.
   * If you pick a full pill, you split it into two half pills, consume a half pill, and return the other.
   * If you pick a half pill, just consume it.
   * 
   * We know exactly how many full pills and half pills inside the container at the beginning,
   * and want to figure out the possibility of the container having the exact target full pills and
   * target half pills.
   * 
   * Example1:
   * Input: startFull = 2, startHalf = 1, endFull = 1, endHalf = 2;
   * output: 0.66666
   * Explanation:
   * By drawing one of the two full pills, the container will have 1 full pill and 2 half pills.
   * 
   * Example2:
   * Input: startFull = 2, startHalf = 1, endFull = 2, endHalf = 0;
   * output: 0.33333   
   * Explanation:
   * By drawing the only half pills, the container will have 2 full pills and 0 half pill.
   * 
   */
  
  public static class DFS_method {
    
    public double getPossibility(int startFull, int startHalf, int endFull, int endHalf) {
      return helper(startFull, startHalf, endFull, endHalf, 1d);
    }
    
    private double helper(int sF, int sH, int eF, int eH, double p) {
      if (sF < eF || sH < 0) {
        return 0;
      }
      if (sF == eF && sH == eH) {
        return p;
      }
      double PF = (double) sF / (sF + sH);
      return helper(sF - 1, sH + 1, eF, eH, PF * p) 
          + helper(sF, sH - 1, eF, eH, (1d - PF) * p);
    }
    
  }
  
  public static class DP_method {
    
    public double getPossibility(int startFull, int startHalf, int endFull, int endHalf) {
      startFull -= endFull;
      double[][] f = new double[startFull + 1][startFull + startHalf + 1];
      f[startFull][startHalf] = 1d;
      for (int full = startFull; full >= 0; full--) {
        for (int half = startFull + startHalf - full; half >= 0; half--) {
          double pH = (double) half / (full + half + endFull);
          if (half > 0) {
            f[full][half - 1] += pH * f[full][half];
          }
          if (full > 0) {
            f[full - 1][half + 1] += (1d - pH) * f[full][half];
          }
        }
      }
      return f[0][endHalf];
    }
    
  }

  public static void main(String[] args) {
    int startFull = 5;
    int startHalf = 2;
    int endFull = 3;
    int endHalf = 1;
    SplitPills.DFS_method one = new SplitPills.DFS_method();
    SplitPills.DP_method dpOne = new SplitPills.DP_method();
    System.out.println(one.getPossibility(startFull, startHalf, endFull, endHalf));
    System.out.println(dpOne.getPossibility(startFull, startHalf, endFull, endHalf));
  }

}
