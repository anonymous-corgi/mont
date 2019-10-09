package leetcode.p401to450;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Similar to {@link leetcode.p1001to1050.LeetCode1027LongestArithmeticSequence}
 */
public class LeetCode403FrogJump {

    private interface Method {
        boolean canCross(int[] stones);
    }

    private static final class Memorization implements Method {

        public boolean canCross(int[] stones) {
            if (stones[0] != 0 || stones[1] - stones[0] != 1) {
                return false;
            }
            // Key is stones[i], record all the possible steps that you last time jumped to this stones[i].
            Map<Integer, Set<Integer>> records = new HashMap<>(stones.length);
            for (int i = 1; i < stones.length; i++) {
                records.put(stones[i], new HashSet<>());
            }
            records.get(stones[1]).add(1);
            for (int i = 1, iEnd = stones.length - 1; i < iEnd; i++) {
                Set<Integer> record = records.get(stones[i]);
                for (int prevStep : record) {
                    for (int j = -1; j < 2; j++) {
                        int nextStep = prevStep + j;
                        if (nextStep < 1) {
                            continue;
                        }
                        int nextPosition = stones[i] + nextStep;
                        if (records.containsKey(nextPosition)) {
                            records.get(nextPosition).add(nextStep);
                        }
                    }
                }
            }
            return !records.get(stones[stones.length - 1]).isEmpty();
        }
    }

    private static Method getMethod() {
        return new Memorization();
    }

    private void test(int[] stones, boolean expected) {
        boolean actual = getMethod().canCross(stones);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        test(stones, true);
    }
}