package algorithm.interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuackSort {

    public static class Quack {

        private Deque<Integer> queue;

        public Quack(int[] nums) {
            Arrays.sort(nums);
            this.queue = new LinkedList<>();
            for (int num : nums) {
                queue.add(num);
            }
        }

        public Integer pop() {
            Random r = new Random();
            return r.nextInt(2) == 0 ? queue.pollFirst() : queue.pollLast();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

    }

    public List<Integer> sortQuack(Quack q) {
        Deque<Integer> min = new LinkedList<>();
        Deque<Integer> max = new LinkedList<>();
        while (q.hasNext()) {
            int num = q.pop();
            if (min.isEmpty() || num >= min.peek()) {
                min.push(num);
            } else {
                while (!min.isEmpty() && num < min.peek()) {
                    max.push(min.pop());
                }
                min.push(num);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!min.isEmpty()) {
            list.add(min.pollLast());
        }
        while (!max.isEmpty()) {
            list.add(max.pollFirst());
        }
        return list;
    }
}
