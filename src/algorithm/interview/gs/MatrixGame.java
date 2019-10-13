package algorithm.interview.gs;

import java.util.Arrays;

public class MatrixGame {

    private static int play(int[][] arrs) {
        int res = 0;
        int[] max = new int[arrs[0].length];
        for (int[] arr : arrs) {
            for (int j = 0; j < arr.length; j++) {
                max[j] = Math.max(max[j], arr[j]);
            }
        }
        Arrays.sort(max);
        for (int i = 1; i <= max.length; i++) {
            res += i % 2 == 1 ? max[max.length - i] : -max[max.length - i];
        }
        return res;
    }
}
