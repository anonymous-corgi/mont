package algorithm.leetcode.p301to350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode347TopKFrequentElements {

    public static class Bucketsort_method {
        //There is a problem with Bucket_Sort: If there are several number has same frequency,
        //it will pick more than k elements;
        public List<Integer> topKFrequent(int[] nums, int k) {

            @SuppressWarnings("unchecked")
            List<Integer>[] buckets = new List[nums.length + 1];
            Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

            for (int n : nums) {
                frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
            }

            for (int key : frequencyMap.keySet()) {
                int frequency = frequencyMap.get(key);
                if (buckets[frequency] == null) {
                    buckets[frequency] = new ArrayList<>();
                }
                buckets[frequency].add(key);
            }

            List<Integer> res = new ArrayList<>();

            for (int pos = buckets.length - 1; pos >= 0 && res.size() < k; pos--) {
                if (buckets[pos] != null) {
                    res.addAll(buckets[pos]);
                }
            }
            return res;
        }

    }

    public static void main(String[] args) {
        LeetCode347TopKFrequentElements.Bucketsort_method one =
                new LeetCode347TopKFrequentElements.Bucketsort_method();
        int[] nums = {1, 1, 2, 3, 4, 5, 3, 2, 4,};
        int k = 2;
    }
}
