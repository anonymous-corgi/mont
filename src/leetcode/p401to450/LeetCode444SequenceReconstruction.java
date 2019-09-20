package leetcode.p401to450;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 444. Sequence Reconstruction
 * Medium
 *
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * Example 1:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 * Output:
 * false
 *
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed,
 * because [1,3,2] is also a valid sequence that can be reconstructed.
 *
 * Example 2:
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 * Output:
 * false
 *
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 *
 * Example 3:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 * Output:
 * true
 *
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 *
 * Example 4:
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 * Output:
 * true
 */
public class LeetCode444SequenceReconstruction {

    private interface Method {
        boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs);
    }

    private static final class Verify implements Method {

        @Override
        public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
            if (org.length == 0) {
                return true;
            }
            boolean seqsHasNumber = false;
            int checkedNum = 0;
            int[] pos = new int[org.length + 1];
            boolean[] checked = new boolean[org.length + 1];
            for (int i = 0; i < org.length; i++) {
                pos[org[i]] = i;
            }
            for (List<Integer> seq : seqs) {
                for (int i = 0; i < seq.size(); i++) {
                    int successor = seq.get(i);
                    if (successor < 1 || successor > org.length) {
                        return false;
                    }
                    seqsHasNumber = true;
                    if (i == 0) {
                        continue;
                    }
                    int pioneer = seq.get(i - 1);
                    if (pos[pioneer] >= pos[successor]) {
                        return false;
                    }
                    if (!checked[pos[successor]] && pos[pioneer] + 1 == pos[successor]) {
                        checked[pos[successor]] = true;
                        checkedNum++;
                    }
                }
            }
            return seqsHasNumber && checkedNum == org.length - 1;
        }
    }

    private static final class BFS implements Method {

        @Override
        public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
            if (org.length == 0) {
                return true;
            }
            int[] inDegrees = new int[org.length + 1];
            Map<Integer, Set<Integer>> successors = new HashMap<>();
            int count = 0;
            Deque<Integer> taskQueue = new ArrayDeque<>();
            for (List<Integer> seq : seqs) {
                for (int num : seq) {
                    // seqs might contain numbers that aren't in org, we need to verify it.
                    if (num < 1 || num > org.length) {
                        return false;
                    }
                    if (!successors.containsKey(num)) {
                        successors.put(num, new HashSet<>());
                    }
                }
                for (int i = seq.size() - 1; i > 0; i--) {
                    int pioneer = seq.get(i - 1);
                    int successor = seq.get(i);
                    if (successors.get(pioneer).add(successor)) {
                        inDegrees[successor]++;
                    }
                }
            }

            // Make sure that seqs have all the numbers of org.
            if (successors.size() != org.length) {
                return false;
            }

            for (int i = 1; i < inDegrees.length; i++) {
                if (inDegrees[i] == 0) {
                    taskQueue.offer(i);
                }
            }
            while (taskQueue.size() == 1) {
                count++;
                int cursor = taskQueue.poll();
                Set<Integer> successorSet = successors.get(cursor);
                for (int successor : successorSet) {
                    if (--inDegrees[successor] == 0) {
                        taskQueue.offer(successor);
                    }
                }
            }
            return count == org.length;
        }
    }

    private static Method getMethod() {
        return new Verify();
    }

    private void test(int[] org, List<List<Integer>> seqs, boolean expected) {
        boolean actual = getMethod().sequenceReconstruction(org, seqs);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] org = new int[]{1};
        List<List<Integer>> seqs = Arrays.asList(Arrays.asList(1, 1));
        test(org, seqs, false);
    }

    @Test
    public void testcase2() {
        int[] org = new int[]{1};
        List<List<Integer>> seqs = Arrays.asList();
        test(org, seqs, false);
    }

    @Test
    public void testcase3() {
        int[] org = new int[]{1};
        List<List<Integer>> seqs = Arrays.asList(Arrays.asList(1), Arrays.asList(1), Arrays.asList(1));
        test(org, seqs, true);
    }
}
