package leetcode.p201to250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 247. Strobogrammatic Number II
 * Medium
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * Example:
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 */
public class LeetCode247StrobogrammaticNumberII {

    private interface Method {
        List<String> findStrobogrammatic(int n);
    }

    private static final class BackTracking implements Method {

        @Override
        public List<String> findStrobogrammatic(int n) {
            if (n < 0) {
                return new ArrayList<>();
            }
            return find(n, n);
        }

        private List<String> find(int n, int len) {
            if (n == 0) {
                return new ArrayList<>(Arrays.asList(""));
            }
            if (n == 1) {
                return new ArrayList<>(Arrays.asList("0", "1", "8"));
            }

            List<String> res = new ArrayList<>();
            List<String> prev = find(n - 2, len);
            for (String str : prev) {
                if (n != len) {
                    res.add("0" + str + "0");
                }
                res.add("1" + str + "1");
                res.add("6" + str + "9");
                res.add("8" + str + "8");
                res.add("9" + str + "6");
            }
            return res;
        }
    }

    private static Method getMethod() {
        return new BackTracking();
    }

    public static void main(String[] args) {
        Method method = getMethod();
        int n = 4;
        List<String> list = method.findStrobogrammatic(n);
        System.out.println(list);
    }
}
