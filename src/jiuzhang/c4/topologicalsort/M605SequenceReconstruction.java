package jiuzhang.c4.topologicalsort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class M605SequenceReconstruction {

    // Actually, you need to check two things:
    // 1. seqs contains all the elements in org, but doesn't contain number not in org
    // 2. The number order of seqs is consistent with org
    interface SequenceReconstruction {
        boolean sequenceReconstruction(int[] org, int[][] seqs);
    }

    static class BFS_Method implements SequenceReconstruction {

        @Override
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            if (org == null || seqs == null) {
                return false;
            }
            if (org.length == 0) {
                return true;
            }

            int[] inDegrees = new int[org.length + 1];
            inDegrees[0] = Integer.MAX_VALUE;
            Map<Integer, Set<Integer>> successors = new HashMap<>();
            List<Integer> taskList = new ArrayList<>();

            for (int[] seq : seqs) {
                // seq might contains number that isn't in org, we need to verify it.
                for (int num : seq) {
                    if (num < 1 || num > org.length) {
                        return false;
                    }
                    successors.putIfAbsent(num, new HashSet<>());
                }
                for (int j = seq.length - 2; j >= 0; j--) {
                    if (successors.get(seq[j]).add(seq[j + 1])) {
                        inDegrees[seq[j + 1]]++;
                    }
                }
            }

            if (successors.size() != org.length) {
                return false;
            }

            for (int i = 1; i <= org.length; i++) {
                if (inDegrees[i] == 0) {
                    taskList.add(i);
                }
            }

            int index = 0;
            while (taskList.size() == 1) {
                Integer cursor = taskList.remove(0);
                if (cursor != org[index++]) {
                    return false;
                }
                for (Integer successor : successors.getOrDefault(cursor, Collections.emptySet())) {
                    if (--inDegrees[successor] == 0) {
                        taskList.add(successor);
                    }
                }
            }
            return (index == org.length);
        }
    }

    static class Verification_Method implements SequenceReconstruction {

        @Override
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            if (org.length == 0) {
                return true;
            }
            boolean seqsHasNumber = false;
            int[] pos = new int[org.length + 1];
            boolean[] checked = new boolean[org.length + 1];
            int checkedNum = org.length - 1;
            for (int i = 0; i < org.length; i++) {
                pos[org[i]] = i;
            }
            for (int[] seq : seqs) {
                for (int i = 0; i < seq.length; i++) {
                    if (seq[i] < 1 || seq[i] > org.length) {
                        return false;
                    }
                    seqsHasNumber = true;
                    if (i == 0) {
                        continue;
                    }
                    if (pos[seq[i - 1]] >= pos[seq[i]]) {
                        return false;
                    }
                    if (!checked[pos[seq[i]]] && pos[seq[i - 1]] + 1 == pos[seq[i]]) {
                        checked[pos[seq[i]]] = true;
                        checkedNum--;
                    }
                }
            }
            return seqsHasNumber && checkedNum == 0;
        }
    }

    public static SequenceReconstruction getMethod() {
        return new BFS_Method();
    }

    public static void main(String[] args) {
        int[] org = {1};
        int[][] seqs = {};
//        int[] org = {1, 2, 3};
//        int[][] seqs = {{1, 2}, {1, 3}, {2, 3}};
        SequenceReconstruction method = getMethod();
        method.sequenceReconstruction(org, seqs);
    }

    @Test
    public void testCase1() {
        int[] org = {1};
        int[][] seqs = {};
        SequenceReconstruction method = getMethod();
        Assert.assertFalse(method.sequenceReconstruction(org, seqs));
    }

    @Test
    public void testCase2() {
        int[] org = {1, 2, 3};
        int[][] seqs = {{3, 2}, {2, 1}};
        SequenceReconstruction method = getMethod();
        Assert.assertFalse(method.sequenceReconstruction(org, seqs));
    }
}
