package leetcode.p701to750;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LeetCode731MyCalendarII {

    private interface Method {
        boolean book(int start, int end);
    }

    //Method 1:
    public static class TreeMap_Method implements Method {

        private TreeMap<Integer, Integer> timeline = new TreeMap<>();

        @Override
        public boolean book(int start, int end) {
            timeline.put(start, timeline.getOrDefault(start, 0) + 1);
            timeline.put(end, timeline.getOrDefault(end, 0) - 1);
            int cnt = 0;
            for (Integer v : timeline.values()) {
                cnt += v;
                if (cnt == 3) {
                    timeline.put(start, timeline.get(start) - 1);
                    timeline.put(end, timeline.get(end) + 1);
                    return false;
                }
            }
            return true;
        }
    }

    // Two intervals overlap: [s1, e1], [s2, e2] => max(s1, s2) < min(e1, e2)
    //Method 2:
    public static class List_Method implements Method {

        private Overlap overlaps = new Overlap();
        private List<int[]> books = new ArrayList<>();

        @Override
        public boolean book(int start, int end) {
            for (int[] b : books)
                if (Math.max(b[0], start) < Math.min(b[1], end)) {
                    // overlap exists
                    if (!overlaps.book(Math.max(b[0], start), Math.min(b[1], end))) {
                        return false; // overlaps overlapped
                    }
                }
            books.add(new int[]{start, end});
            return true;
        }

        private class Overlap {

            List<int[]> books = new ArrayList<>();

            boolean book(int start, int end) {
                for (int[] b : books) {
                    if (Math.max(b[0], start) < Math.min(b[1], end)) {
                        return false;
                    }
                }
                books.add(new int[]{start, end});
                return true;
            }
        }
    }

    private static Method getMethod() {
        int type = 0;
        switch (type) {
            case 0:
                return new List_Method();
            default:
                return new TreeMap_Method();
        }
    }

    public static void main(String[] args) {
        Method one = getMethod();
        int[][] times = {{10, 11}, {10, 13}, {5, 6}, {3, 6}, {4, 13}, {1, 16}};
        for (int[] time : times) {
            System.out.println(one.book(time[0], time[1]));
        }
    }
}
