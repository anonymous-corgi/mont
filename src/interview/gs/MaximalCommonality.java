package interview.gs;

public class MaximalCommonality {

    private static int maxCommon(String s) {
        int[] s1 = new int[26];
        int[] s2 = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            s1[s.charAt(i) - 'a']++;
        }
        int common = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int index = s.charAt(i) - 'a';
            int oldS1 = s1[index]--;
            int oldS2 = s2[index]++;
            int oldCommon = Math.min(oldS1, oldS2);
            int newCommon = Math.min(s1[index], s2[index]);
            common += (newCommon - oldCommon);
            res = Math.max(res, common);
        }
        return res;
    }
}
