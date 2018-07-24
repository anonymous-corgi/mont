package jiuzhang.dp;

public class LeetCode265PaintHouseII {
	
    public int minCostII(int[][] costs) {
        // write your code here
        if (costs == null || costs.length == 0) {
            return 0;
        }
        if (costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int K = costs[0].length;
        
        int[] pMin = new int[K];
        int[] cMin = new int[K];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0, len = costs.length; i < len; i++) {
            int m1 = -1;
            int m2 = -1;
            for (int j = 0; j < K; j++) {
                if (j != i1) {
                    cMin[j] = pMin[i1] + costs[i][j];
                } else {
                    cMin[j] = pMin[i2] + costs[i][j];
                }
                
                if (m1 == -1 || cMin[j] < cMin[m1]) {
                    m2 = m1;
                    m1 = j;
                } else if (m2 == -1 || cMin[j] < cMin[m2]) {
                    m2 = j;
                }
            }
            i1 = m1;
            i2 = m2;
            int[] temp = pMin;
            pMin = cMin;
            cMin = temp;
        }
        
        return pMin[i1];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
