package leetcode.p851to900;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode857MinimumCostToHireKWorkers {

    private interface Method {
        double mincostToHireWorkers(int[] quality, int[] wage, int K);
    }

    private static final class Greedy implements Method {

        private static class Node {
            int quality;
            double rate;

            Node(int quality, int w) {
                this.quality = quality;
                this.rate = (double) w / quality;
            }
        }

        /**
         * For k works, their cost is max(Rate) * sum(quality).
         * For a certain rate, the best case is when all other workers' rate <= max(rate), also the sum(quality) is the smallest.
         * So we should maintain a group of workers whose quality is smallest for a max(rate)
         */
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            Node[] nodes = new Node[quality.length];
            for (int i = 0; i < quality.length; i++) {
                nodes[i] = new Node(quality[i], wage[i]);
            }
            Arrays.sort(nodes, Comparator.comparingDouble(a -> a.rate));
            PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> (b.quality - a.quality));
            int qSum = 0;
            double minWage = Double.MAX_VALUE;
            for (int i = 0; i < quality.length; i++) {
                que.offer(nodes[i]);
                qSum += nodes[i].quality;

                if (i >= K - 1) {
                    minWage = Math.min(minWage, qSum * nodes[i].rate);
                    qSum -= que.poll().quality;
                }
            }
            return minWage;
        }
    }
}
