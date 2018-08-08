package leetcode.p751to800;

public class LeetCode774MinimizeMaxDistanceToGasStation {
  
  public double minmaxGasDist(int[] stations, int k) {
    // Write your code here
    if (stations == null || stations.length == 0) {
      return 0d;
    }
    double left = (double) (stations[stations.length - 1] - stations[0]) 
        / (k + stations.length - 1);
    double right = 0d;
    double[] gap = new double[stations.length - 1];
    for (int i = 0, iLen = stations.length - 1; i < iLen; i++) {
      gap[i] = stations[i + 1] - stations[i];
      right = Math.max(right, gap[i]);
    }
    while (right - left > 1e-6) {
      double mid = (left + right) / 2;
      if (canSplit(gap, mid, k)) {
        right = mid; 
      } else {
        left = mid;
      }
    }
    return right;
  }
  
  private boolean canSplit(double[] gap, double maxGap, int maxStations) {
    int count = 0;
    for (int i = 0; i < gap.length; i++) {
      count += (int) gap[i] / maxGap;
      if (count > maxStations) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode774MinimizeMaxDistanceToGasStation one = 
        new LeetCode774MinimizeMaxDistanceToGasStation();
//    int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//    int k = 9;    
    int[] stations = {3,6,12,19,33,44,67,72,89,95};
    int k = 2;
    System.out.println(one.minmaxGasDist(stations, k));
  }

}
