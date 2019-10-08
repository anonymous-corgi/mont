package leetcode.p601to650;

import java.util.Arrays;

public class LeetCode621TaskScheduler {

    private interface Method {
        int leastInterval(char[] tasks, int n);
    }

    private static final class Trick implements Method {

        public int leastInterval(char[] tasks, int n) {
            if (tasks.length == 0) {
                return 0;
            }
            int[] repetitions = new int[26];
            for (char task : tasks) {
                repetitions[task - 'A']++;
            }
            Arrays.sort(repetitions);
            int countMaxRepetition = 25;
            while (countMaxRepetition >= 0 && repetitions[countMaxRepetition] == repetitions[25]) {
                countMaxRepetition--;
            }
            countMaxRepetition = 25 - countMaxRepetition;

            return Math.max(tasks.length, (n + 1) * (repetitions[25] - 1) + countMaxRepetition);
        }
    }
}
