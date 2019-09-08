package leetcode.p701to750;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class LeetCode721AccountsMerge {

    private interface Method {
        List<List<String>> accountsMerge(List<List<String>> accounts);
    }

    private static final class UnionFind implements Method {

        @Override
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int[] ids = new int[accounts.size()];
            Map<Integer, List<String>> idToList = new HashMap<>();
            Map<String, Integer> emailToId = new HashMap<>(10000);
            for (int i = 0; i < ids.length; i++) {
                ids[i] = i;
            }
            for (int i = 0; i < accounts.size(); i++) {
                List<String> account = accounts.get(i);
                List<String> list = new ArrayList<>();
                list.add(account.get(0));
                idToList.put(i, list);
                for (int j = 1; j < account.size(); j++) {
                    String email = account.get(j);
                    Integer idInMap = emailToId.get(email);
                    if (idInMap == null) {
                        int rootId = find(ids, i);
                        emailToId.put(email, rootId);
                        idToList.get(rootId).add(email);
                    } else {
                        connect(ids, idToList, idInMap, i);
                    }
                }
            }
            List<List<String>> result = new ArrayList<>(idToList.values());
            for (List<String> account : result) {
                account.subList(1, account.size()).sort(String::compareTo);
            }
            return result;
        }

        private int find(int[] ids, int index) {
            return ids[index] == index ? index : (ids[index] = find(ids, ids[index]));
        }

        private void connect(int[] ids, Map<Integer, List<String>> idToList, int t, int f) {
            int tRoot = find(ids, t);
            int fRoot = find(ids, f);
            if (tRoot != fRoot) {
                List<String> tList = idToList.get(tRoot);
                List<String> fList = idToList.remove(fRoot);
                tList.addAll(fList.subList(1, fList.size()));
                ids[fRoot] = tRoot;
            }
        }
    }
}
