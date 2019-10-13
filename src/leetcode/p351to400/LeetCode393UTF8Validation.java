package leetcode.p351to400;

public class LeetCode393UTF8Validation {

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        int last = 0;
        for (int i = 0; i < data.length; i++) {
            int index = 0;
            int bit = 1 << 7;
            while ((data[i] & bit) != 0) {
                index++;
                bit >>= 1;
            }
            if (index > 4) {
                return false;
            } else if (last <= 1 && index != 1) {
                last = index;
            } else if (last > 1 && index == 1) {
                last--;
            } else {
                return false;
            }
        }
        return last <= 1;
    }

    private static String convertToBinary(int num) {
        final int GAP = 8;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            while (index < 31) {
                if (num < 0) {
                    sb.insert(0, (num % 2 == 0 ? '1' : "0"));
                    num /= 2;
                } else {
                    sb.insert(0, '1');
                }
                index++;
                if (index % GAP == 0) {
                    sb.insert(0, ',');
                }
            }
            sb.insert(0, '1');
        } else {
            while (index < 31) {
                if (num > 0) {
                    sb.insert(0, (num % 2));
                    num /= 2;
                } else {
                    sb.insert(0, '0');
                }
                index++;
                if (index % GAP == 0) {
                    sb.insert(0, ',');
                }
            }
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode393UTF8Validation one = new LeetCode393UTF8Validation();
        int[] data = {250, 145, 145, 145, 145};
        for (int datum : data) {
            System.out.println(convertToBinary(datum));
        }
        System.out.println(one.validUtf8(data));
    }
}
