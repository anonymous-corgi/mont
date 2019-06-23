package leetcode.p701to750;

import java.util.TreeMap;

public class LeetCode732MyCalendarIII {

    private interface Method {
        int book(int start, int end);
    }

    private static class TreeMap_Method implements Method {

        private TreeMap<Integer, Integer> timeline = new TreeMap<>();

        @Override
        public int book(int s, int e) {
            timeline.put(s, timeline.getOrDefault(s, 0) + 1);
            timeline.put(e, timeline.getOrDefault(e, 0) - 1);
            int max = 0;
            int count = 0;
            for (int v : timeline.values()) {
                count += v;
                max = Math.max(max, count);
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Method one = new TreeMap_Method();
        int[][] times = {{10, 11}, {10, 13}, {5, 6}, {3, 6}, {4, 13}, {1, 16}};
        for (int[] time : times) {
            System.out.println(one.book(time[0], time[1]));
        }
    }
}
