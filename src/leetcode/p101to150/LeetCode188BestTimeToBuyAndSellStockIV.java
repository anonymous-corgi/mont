package leetcode.p101to150;

public class LeetCode188BestTimeToBuyAndSellStockIV {
  
  public int maxProfit(int k, int[] prices) {
    int len = prices.length;
    if (k >= len / 2) {
      return quickSolve(prices);
    }
    //After the i times of transaction and the last transaction is done in j day,
    //The profit is f[i][j]
    int[][] f = new int[k + 1][len];
    for (int i = 1; i <= k; i++) {
      int tmpMax = -prices[0];
      f[i][1] = Math.max(f[i][0], prices[1] + tmpMax);
      for (int j = 2; j < len; j++) {
        //tmpMax records the maximum profit minus the fee used to buy stock before day j.
        tmpMax =  Math.max(tmpMax, f[i - 1][j - 2] - prices[j - 1]);
        //
        f[i][j] = Math.max(f[i][j - 1], prices[j] + tmpMax);
      }
    }
    return f[k][len - 1];
  }
  
  private int quickSolve(int[] prices) {
    int len = prices.length, profit = 0;
    for (int i = 1; i < len; i++)
      // as long as there is a price gap, we gain a profit.
      if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
    return profit;
  }
  
//  Method 2: 
//  public int maxProfit(int k, int[] prices) {
//    if (prices == null || prices.length == 0) {
//      return 0;
//    }
//    int len = prices.length;
//    int[][] pre = new  int[2][len + 1];
//    int[][] cur = new int[2][len + 1];
//    for (int times = 0; times < k; times++) {
//      for (int day = 1; day <= len; day++) {
//        cur[0][day] = 0;
//        // o means has stock
//        if (day != 1) {
//          //(Bought previously in the same stage, Bought today and stage changed)
//          cur[0][day] = Math.max(cur[0][day - 1] + prices[day - 1] - prices[day - 2],
//              pre[1][day - 1]);
//        } else {
//          //(Bought today)
//          cur[0][day] = Math.max(cur[0][day], pre[1][day - 1]);
//        }
//      }
//      for (int day = 1; day <= len; day++) {
//        cur[1][day] = 0;
//        if (day != 1) {
//          //(Sold today, Sold previously)
//          cur[1][day] = Math.max(cur[0][day - 1] + prices[day - 1] - prices[day - 2],
//              cur[1][day - 1]);
//        } else {
//          cur[1][day] = 0;
//        }
//      }
//      pre = cur;
//      cur = pre;
//    }
//    return pre[1][len];
//  }
  
//  Method 3:
//  private int update(int a, int b, int delta) {
//    if (b == Integer.MIN_VALUE) {
//      return a;
//    }
//    
//    if (b + delta > a) {
//      return b + delta;
//    }
//    
//    return a;
//  } 
//  
//  public int maxProfit(int K, int[] prices) {
//    int n = prices.length;
//    int i, j;
//    
//    int[][] f = new int[n + 1][2 * K + 2];
//    for (i = 0; i <= n; ++i) {
//      for (j = 1; j <= 2 * K + 1; ++j) {
//        f[i][j] = Integer.MIN_VALUE;
//      }
//    }
//    
//    f[0][1] = 0;
//    for (i = 1; i <= n; ++i) {
//      for (j = 1; j <= 2 * K + 1; j += 2) {
//        f[i][j] = update(f[i][j], f[i - 1][j], 0);
//        if (j > 1 && i > 1) f[i][j] = update(f[i][j], f[i - 1][j - 1], prices[i - 1] - prices[i - 2]);
//      }
//      
//      for (j = 2; j <= 2 * K; j += 2) {
//        if (i > 1) f[i][j] = update(f[i][j], f[i-1][j], prices[i - 1] - prices[i - 2]);
//        if (j > 1) f[i][j] = update(f[i][j], f[i-1][j-1], 0);
//      }
//    }
//    
//    int res = Integer.MIN_VALUE;
//    for (j = 1; j <= 2 * K + 1; j += 2) {
//      res = update(res, f[n][j], 0);
//    }
//    
//    return res;
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    LeetCode188BestTimeToBuyAndSellStockIV one = 
        new LeetCode188BestTimeToBuyAndSellStockIV();
    int K = 2;
    int[] prices = {3,2,6,5,0,3};
    System.out.println(one.maxProfit(K, prices));
  }

}
