package leetcode.p151to200;

public class LeetCode188BestTimeToBuyAndSellStockIV {
  
  public static class DP_method1 {
    
    public int maxProfit(int k, int[] prices) {
      int LEN = prices.length;
      if (k >= LEN / 2) {

        return quickSolve(prices);
      }
      //After the i times of transaction and the last transaction is done in j day,
      //The profit is f[i][j]
      int[][] f = new int[k + 1][LEN];
      for (int i = 1; i <= k; i++) {
        int prevMax = -prices[0];
        f[i][1] = Math.max(f[i][0], prices[1] + prevMax);
        for (int j = 2; j < LEN; j++) {
          //tmpMax records the maximum profit minus the fee used to buy stock before day j.
          prevMax =  Math.max(prevMax, f[i - 1][j - 2] - prices[j - 1]);
          
          f[i][j] = Math.max(f[i][j - 1], prices[j] + prevMax);
        }
      }
      return f[k][LEN - 1];
    }
    
    private int quickSolve(int[] prices) {
      int len = prices.length, profit = 0;
      for (int i = 1; i < len; i++)
        // as long as there is a price gap, we gain a profit.
        if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
      return profit;
    }
    
  }
  
  public static class DP_method2 {
    
    public int maxProfit(int k, int[] prices) {
      if (prices == null || prices.length == 0) {
        return 0;
      }
      int len = prices.length;
      int[][] pre = new  int[2][len + 1];
      int[][] cur = new int[2][len + 1];
      for (int times = 0; times < k; times++) {
        for (int day = 1; day <= len; day++) {
          cur[0][day] = 0;
          // o means has stock
          if (day != 1) {
            //(Bought previously in the same stage, Bought today and stage changed)
            cur[0][day] = Math.max(cur[0][day - 1] + prices[day - 1] - prices[day - 2],
                pre[1][day - 1]);
          } else {
            //(Bought today)
            cur[0][day] = Math.max(cur[0][day], pre[1][day - 1]);
          }
        }
        for (int day = 1; day <= len; day++) {
          cur[1][day] = 0;
          if (day != 1) {
            //(Sold today, Sold previously)
            cur[1][day] = Math.max(cur[0][day - 1] + prices[day - 1] - prices[day - 2],
                cur[1][day - 1]);
          } else {
            cur[1][day] = 0;
          }
        }
        pre = cur;
        cur = pre;
      }
      return pre[1][len];
    }
    
  }

  public static void main(String[] args) {
    int K = 2;
    int[] prices = {3,2,6,5,0,3};
    LeetCode188BestTimeToBuyAndSellStockIV.DP_method1 one = 
        new LeetCode188BestTimeToBuyAndSellStockIV.DP_method1();
    System.out.println(one.maxProfit(K, prices));
  }

}
