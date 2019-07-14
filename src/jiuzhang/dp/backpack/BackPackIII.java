package jiuzhang.dp.backpack;

//BackPackII doesn't contain repeated object
public class BackPackIII {

    //Count Max value can be stored in m capacity WITH repeated use
    private interface Method {
        int backPackIII(int capacity, int[] weights, int[] values);
    }

    //Method1: Loop from items first.
    private static class Method1 implements Method {

        @Override
        public int backPackIII(int capacity, int[] weights, int[] values) {
            if (weights == null || weights.length == 0) {
                return 0;
            }
            int[] dp = new int[capacity + 1];
            for (int i = 0; i < weights.length; i++) {
                for (int j = weights[i]; j <= capacity; j++) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
            return dp[capacity];
        }
    }

    //Method2: Loop from weight first.
    private static class Method2 implements Method {

        @Override
        public int backPackIII(int capacity, int[] weights, int[] values) {
            if (weights == null || weights.length == 0) {
                return 0;
            }
            int[] dp = new int[capacity + 1];
            for (int i = 1; i <= capacity; i++) {
                for (int j = 0; j < weights.length; j++) {
                    if (weights[j] <= i) {
                        dp[i] = Math.max(dp[i], dp[i - weights[j]] + values[j]);
                    }
                }
            }
            return dp[capacity];
        }
    }

    private static Method getMethod() {
        int res = 0;
        switch (res) {
            case 1:
                return new Method2();
            default:
                return new Method1();
        }
    }

    public static void main(String[] args) {
        int capacity = 30;
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] values = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        Method one = getMethod();
        System.out.println(one.backPackIII(capacity, weights, values));
    }
}
