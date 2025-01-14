package com.anonymouscorgi.karakoram.kb0350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

interface LeetCode398RandomPickIndex {

  int pick(int target);

  LeetCode398RandomPickIndex METHOD = new LeetCode398RandomPickIndex() {

    private Random random = new Random();
    private HashMap<Integer, ArrayList<Integer>> indexMap = new HashMap<>();

    public void solution(int[] nums) {
      for (int i = 0; i <nums.length;i++) {
        indexMap.computeIfAbsent(nums[i], t-> new ArrayList<>());
        indexMap.get(nums[i]).add(i);
      }
    }

    @Override
    public int pick(int target) {
      ArrayList<Integer> indexes = indexMap.get(target);
      return indexes.get(random.nextInt(indexes.size()));
    }
  };
}
