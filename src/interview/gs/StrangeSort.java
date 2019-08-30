package interview.gs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrangeSort {

    private static class Node {
        int value;
        int order;
        String origin;
    }

    public String[] sort(int[] mapping, String[] nums) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        List<Node> list = new ArrayList<>(nums.length);
        for (int i = 0; i < mapping.length; i++) {
            map.put(mapping[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Node node = new Node();
            sb.setLength(0);
            for (char bit : nums[i].toCharArray()) {
                sb.append(map.get(bit - '0'));
            }
            node.value = Integer.parseInt(sb.toString());
            node.order = i;
            node.origin = nums[i];
            list.add(node);
        }
        list.sort((a, b) -> a.value != b.value ? a.value - b.value : a.order - b.order);
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = list.get(i).origin;
        }
        return res;
    }
}
