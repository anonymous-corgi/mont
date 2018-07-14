package Drafts;

import java.util.*;
public class MyApp {

    public static List<Integer> findKNums(int[] array, int k) {
        List<Integer> ans = new ArrayList<>();
        if (array == null || array.length == 0 || k == 0) return ans;

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>(){
            public int compare(Map.Entry<Integer, Integer> map1, Map.Entry<Integer, Integer> map2) {
                return map1.getValue() - map2.getValue();
            }
        });

        Map<Integer, Integer> hs = new HashMap<>();
        for (int i :  array) {
            if (hs.containsKey(i)) {
                hs.put(i, hs.get(i) + 1);
            } else {
                hs.put(i, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : hs.entrySet()) {
            if (pq.size() < k) {
                pq.offer(entry);
            } else {
                if (entry.getValue() > pq.peek().getValue()){
                    pq.poll();
                    pq.offer(entry);
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : pq) {
            ans.add(entry.getKey());
        }
        return ans;
    }    
    
    public static void main(String args[]) {
        System.out.println("Hello world - Java!");
        int[] array = {1,1,2,2,2,3,3,3,3,3,4,4,4,5,5,5,5,5,6,7,8,8,8,8,8,8};
        System.out.println(findKNums(array, 3));
    }
}