package jiuzhang.dp;

import java.util.Arrays;
import java.util.Comparator;

public class H602RussianDollEnvelopes {
	
    public int maxEnvelopes2(int[][] envelopes) {
        // Write your code here
        if(envelopes == null || envelopes.length == 0 
            || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            } 
        });
        
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if(index < 0)
                    index = -index - 1;
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }
	
    public int maxEnvelopes(int[][] envelopes) {
        // write your code here
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        if (envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return (a[0] - b[0]);
                } else {
                    return (a[1] - b[1]);
                }
            }
        });
        
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if(index < 0)
                    index = -index - 1;
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
        
//        int len = envelopes.length;
//        int[] record = new int[len];
//        record[0] = 0;
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j < i; j++) {
//                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
//                    record[i] = Math.max((record[j] + 1), record[i]);
//                }
//            }
//        }
//        return record[len - 1] + 1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		H602RussianDollEnvelopes one = new H602RussianDollEnvelopes();
		int[][] envelopes = {{5,4},{5,5},{6,7}};
		System.out.println(one.maxEnvelopes2(envelopes));

	}

}
