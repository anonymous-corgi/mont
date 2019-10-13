package algorithm.interview.gs;

public class WeeklyStockPrice {

    private static float[] stringFormattedWeeklyString(int[] dailyprice) {
        float sum = 0f;
        float[] res = new float[dailyprice.length - 6];
        for (int i = 0; i < 7; i++) {
            sum += dailyprice[i];
        }
        res[0] = sum / 7;
        for (int i = 7; i < dailyprice.length; i++) {
            sum += dailyprice[i] - dailyprice[i - 7];
            res[i - 6] = sum / 7;
        }
        return res;
    }
}
