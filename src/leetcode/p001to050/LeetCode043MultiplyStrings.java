package leetcode.p001to050;

@SuppressWarnings("unused")
public class LeetCode043MultiplyStrings {

    private interface Method {
        String multiply(String num1, String num2);
    }

    private static class Normal implements Method {

        @Override
        public String multiply(String num1, String num2) {
            if (num1 == null || num2 == null) {
                return "";
            }
            if ("0".equals(num1) || "0".equals(num2)) {
                return "0";
            }
            int[] n1 = toIntArray(num1);
            int[] n2 = toIntArray(num2);
            int[] tRes = new int[n1.length + n2.length];
            StringBuilder sb = new StringBuilder();
            for (int i1 = n1.length - 1; i1 >= 0; i1--) {
                for (int i2 = n2.length - 1; i2 >= 0; i2--) {
                    int m = n1[i1] * n2[i2] + tRes[i1 + i2 + 1];
                    tRes[i1 + i2 + 1] = m % 10;
                    tRes[i1 + i2] += m / 10;
                }
            }
            if (tRes[0] != 0) {
                sb.append(tRes[0]);
            }
            for (int i = 1; i < tRes.length; i++) {
                sb.append(tRes[i]);
            }
            return sb.toString();
        }

        private int[] toIntArray(String s) {
            int[] res = new int[s.length()];
            for (int i = s.length() - 1; i >= 0; i--) {
                res[i] = s.charAt(i) - '0';
            }
            return res;
        }
    }
}
