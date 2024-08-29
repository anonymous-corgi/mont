package com.anonymouscorgi.karakoram.kb0600;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 605 · Sequence Reconstruction
 * <p>
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction
 * means building a shortest common supersequence of the sequences in seqs (i.e., a shortest
 * sequence so that all sequences in seqs are subsequences of it). Determine whether there is only
 * one sequence that can be reconstructed from seqs and it is the org sequence.
 * <p>
 * Example 1:
 * <p>
 * Input:org = [1,2,3], seqs = [[1,2],[1,3]] Output: false Explanation: [1,2,3] is not the only one
 * sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be
 * reconstructed.
 * <p>
 * Example 2:
 * <p>
 * Input: org = [1,2,3], seqs = [[1,2]] Output: false Explanation: The reconstructed sequence can
 * only be [1,2], can't reconstruct the sequence [1,2,3].
 * <p>
 * Example 3:
 * <p>
 * Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]] Output: true Explanation: The sequences [1,2],
 * [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * <p>
 * Example 4:
 * <p>
 * Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]] Output:true
 */
final class LintCode605SequenceReconstruction {

  // Actually, you need to check two things:
  // 1. seqs contains all the elements in org, but doesn't contain number not in org
  // 2. The number order of seqs is consistent with org
  interface Algorithm {

    boolean sequenceReconstruction(int[] org, int[][] seqs);
  }

  static Algorithm UniqueTopologicalOrdering_Method = new Algorithm() {

    @Override
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
      if (org == null || seqs == null || seqs.length == 0) {
        return false;
      }
      if (org.length == 0) {
        return true;
      }
      for (int[] seq : seqs) {
        // seq might contain number that isn't in org, we need to verify it.
        for (int num : seq) {
          if (num < 1 || num > org.length) {
            return false;
          }
        }
      }

      int[] dependencies = new int[org.length + 1];
      dependencies[0] = Integer.MAX_VALUE;
      Map<Integer, Set<Integer>> dependents = new HashMap<>();
      for (int[] seq : seqs) {
        for (int i = 0; i < seq.length - 1; i++) {
          int dependency = seq[i];
          int dependent = seq[i + 1];
          dependents.putIfAbsent(dependency, new HashSet<>());
          if (dependents.get(dependency).add(dependent)) {
            dependencies[dependent]++;
          }
        }
      }

      Queue<Integer> unblockedList = new ArrayDeque<>();
      for (int i = 1; i <= org.length; i++) {
        if (dependencies[i] == 0) {
          unblockedList.offer(i);
        }
      }

      int index = 0;
      while (unblockedList.size() == 1) {
        Integer unblockedNumber = unblockedList.poll();
        if (unblockedNumber != org[index++]) {
          return false;
        }
        for (Integer successor : dependents.getOrDefault(unblockedNumber, Collections.emptySet())) {
          if (--dependencies[successor] == 0) {
            unblockedList.add(successor);
          }
        }
      }
      return (index == org.length);
    }
  };

  static Algorithm Verification_Method = new Algorithm() {

    @Override
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
      if (org.length == 0) {
        return true;
      }
      boolean seqsHasNumber = false;
      int[] positionOfNumber = new int[org.length + 1];
      boolean[] checked = new boolean[org.length + 1];
      int checkedNum = org.length - 1;
      for (int i = 0; i < org.length; i++) {
        positionOfNumber[org[i]] = i;
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
          if (positionOfNumber[seq[i - 1]] >= positionOfNumber[seq[i]]) {
            return false;
          }
          if (!checked[positionOfNumber[seq[i]]]
              && positionOfNumber[seq[i - 1]] + 1 == positionOfNumber[seq[i]]) {
            checked[positionOfNumber[seq[i]]] = true;
            checkedNum--;
          }
        }
      }
      return seqsHasNumber && checkedNum == 0;
    }
  };
}
