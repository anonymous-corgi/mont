package algorithm.leetcode.p751to800;

import java.util.ArrayList;
import java.util.List;

public class LeetCode784LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {
        S = S.toLowerCase();
        List<Integer> charPosition = new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (int i = 0, iLen = S.length(); i < iLen; i++) {
            char cur = S.charAt(i);
            if (cur >= 'a' && cur <= 'z') {
                charPosition.add(i);
            }
        }
        dfs(new StringBuilder(S), charPosition, 0, res);
        return res;
    }

    private void dfs(StringBuilder sb,
                     List<Integer> charPosition,
                     int position,
                     List<String> res) {
        if (position == charPosition.size()) {
            res.add(sb.toString());
            return;
        }
        dfs(sb, charPosition, position + 1, res);
        int p = charPosition.get(position);
        char oriChar = sb.charAt(p);
        char subChar = (char) (oriChar - 32);
        sb.setCharAt(p, subChar);
        dfs(sb, charPosition, position + 1, res);
        sb.setCharAt(p, oriChar);
    }

    public static void main(String[] args) {
        String S = "a1b2";
        LeetCode784LetterCasePermutation one =
                new LeetCode784LetterCasePermutation();
    }
}
