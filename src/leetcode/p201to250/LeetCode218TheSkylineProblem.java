package leetcode.p201to250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class LeetCode218TheSkylineProblem {

    private interface Method {
        List<List<Integer>> getSkyline(int[][] buildings);
    }

    private static final class Point_TreeMap implements Method {

        private static final class Point {
            int pos;
            int height;

            private Point(int pos, int height) {
                this.pos = pos;
                this.height = height;
            }
        }

        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> result = new ArrayList<>();
            List<Point> points = new ArrayList<>();
            for (int[] building : buildings) {
                points.add(new Point(building[0], building[2]));
                points.add(new Point(building[1], -building[2]));
            }
            points.sort((a, b) -> a.pos != b.pos ? a.pos - b.pos : b.height - a.height);
            TreeMap<Integer, Integer> highest = new TreeMap<>();
            highest.put(0, 1);
            int lastHighest = 0;
            for (Point point : points) {
                if (point.height > 0) {
                    highest.compute(point.height, this::increase);
                } else {
                    highest.compute(-point.height, this::decrease);
                }
                int currentHighest = highest.lastKey();
                if (currentHighest != lastHighest) {
                    result.add(Arrays.asList(point.pos, currentHighest));
                    lastHighest = currentHighest;
                }
            }
            return result;
        }

        private Integer increase(Integer k, Integer v) {
            return v == null ? 1 : v + 1;
        }

        // Return null cause compute removes the Map.Entry.
        private Integer decrease(Integer k, Integer v) {
            return v - 1 == 0 ? null : v - 1;
        }
    }
}
