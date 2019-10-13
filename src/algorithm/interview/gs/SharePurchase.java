package algorithm.interview.gs;

public class SharePurchase {

    private static long analyzeInvestments(String s) {
        int res = 0;
        int count = 0;
        int start = 0;
        int[] repeat = new int[3];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'A';
            if (0 <= index && index <= 2 && repeat[index]++ == 0) {
                count++;
            }

            while (count == 3) {
                res += (s.length() - i);
                int sIndex = s.charAt(start++) - 'A';
                if (0 <= sIndex && sIndex <= 2 && repeat[sIndex]-- == 1) {
                    count--;
                }
            }
        }
        return res;
    }
}
