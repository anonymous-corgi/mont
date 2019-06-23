package leetcode.p201to250;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LeetCode218TheSkylineProblem {

    private interface Method {
        List<int[]> getSkyline(int[][] buildings);
    }

    private static class Point_TreeMap_Method implements Method{

        @Override
        public List<int[]> getSkyline(int[][] buildings) {
            List<int[]> res = new ArrayList<>();
            List<int[]> height = new ArrayList<>();
            for (int[] each : buildings) {
                height.add(new int[]{each[0], each[2]});
                height.add(new int[]{each[1], -each[2]});
            }
            height.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
            TreeMap<Integer, Integer> highest = new TreeMap<>();
            highest.put(0, 1);
            int prev = 0;
            for (int[] h : height) {
                if (h[1] > 0) {
                    highest.put(h[1], highest.getOrDefault(h[1], 0) + 1);
                } else {
                    int time = highest.get(-h[1]) - 1;
                    if (time == 0) {
                        highest.remove(-h[1]);
                    } else {
                        highest.put(-h[1], time);
                    }
                }
                int cur = highest.lastKey();
                if (cur != prev) {
                    res.add(new int[]{h[0], cur});
                    prev = cur;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
//    int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        Method one = new Point_TreeMap_Method();
        List<int[]> res = one.getSkyline(buildings);
        for (int i = 0, iLen = res.size(); i < iLen; i++) {
            int[] point = res.get(i);
            System.out.print("[" + point[0] + ", " + point[1] + "]\t");
        }
    }
}
