package algorithm.classic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class KnuthMorrisPratt {

    private interface SearchPattern {
        List<Integer> search(String source, String pattern);
    }

    private static final class KMP implements SearchPattern {

        @Override
        public List<Integer> search(String source, String pattern) {
            List<Integer> result = new ArrayList<>();
            int sLen = source.length();
            int pLen = pattern.length();

            int sIndex = 0; // index for source.
            int pIndex = 0; // index for pattern.

            char[] sChars = source.toCharArray();
            char[] pChars = pattern.toCharArray();
            int[] patternLps = getLPS(pChars);

            while (sIndex < sLen) {
                if (sChars[sIndex] == pChars[pIndex]) {
                    sIndex++;
                    pIndex++;
                    if (pIndex == pLen) {
                        result.add(sIndex - pLen);
                        pIndex = patternLps[pIndex - 1] + 1;
                    }
                } else {

                    if (pIndex != 0) {
                        pIndex = patternLps[pIndex - 1] + 1;
                    } else {
                        sIndex++;
                    }
                }
            }

            return result;
        }

        private int[] getLPS(char[] chars) {
            int compareIndex = 0;
            int cursorIndex = 1;

            int[] lps = new int[chars.length];
            Arrays.fill(lps, -1);

            while (cursorIndex < lps.length) {

                if (chars[cursorIndex] == chars[compareIndex]) {
                    lps[cursorIndex++] = compareIndex++;
                } else {

                    if (compareIndex != 0) {
                        compareIndex = lps[compareIndex - 1] + 1;
                    } else {
                        cursorIndex++;
                    }
                }
            }
            return lps;
        }
    }

    private static final class KMP2 implements SearchPattern {

        @Override
        public List<Integer> search(String source, String pattern) {
            List<Integer> result = new ArrayList<>();
            int sLen = source.length();
            int pLen = pattern.length();

            int sIndex = 0; // index for source.
            int pIndex = 0; // index for pattern.

            char[] sou = source.toCharArray();
            char[] pat = pattern.toCharArray();
            // Preprocess the pattern (calculate lps[] array)
            int[] lps = getLPS(pat);

            while (sIndex < sLen) {
                if (pat[pIndex] == sou[sIndex]) {
                    sIndex++;
                    pIndex++;

                    if (pIndex == pLen) {
                        result.add(sIndex - pIndex);
                        pIndex = lps[pIndex - 1];
                    }
                }
                // mismatch after j matches
                else {
                    // Do not match lps[0..lps[j-1]] characters,
                    // they will match anyway
                    if (pIndex != 0) {
                        pIndex = lps[pIndex - 1];
                    } else {
                        sIndex++;
                    }
                }
            }
            return result;
        }

        //lps[i] means the pat.subString(0, lps[i]) eauals to pat.subString(i - lps[i] + 1, i + 1);
        //For example: pat:"ABAB" lps[3] = 2; lps:[0, 0, 1, 2];
        //For example: pat:"ABCDAB" lps[5] = 2; lps:[0, 0, 0, 0, 1, 2];
        //For example: pat:"ABCDAB" lps[5] = 2;
        private int[] getLPS(char[] pat) {
            // length of the previous longest prefix suffix
            int i = 1;
            int j = 0;
            // lps[0] is always 0
            int[] lps = new int[pat.length];

            // the loop calculates lps[i] for i = 1 to M-1
            while (i < pat.length) {

                if (pat[i] == pat[j]) {
                    j++;
                    lps[i] = j;
                    i++;
                } else {
                    // This is tricky. Consider the example.
                    // AAACAAAA and i = 7. The idea is similar
                    // to search step.
                    if (j != 0) {
                        j = lps[j - 1];
                        // Also, note that we do not increment
                        // i here
                    } else {
                        lps[i] = 0;
                        i++;
                    }
                }
            }
            return lps;
        }
    }


    private static SearchPattern getInstance() {
        return new KMP2();
    }

    private void test(String source, String pattern, List<Integer> expected) {
        List<Integer> actual = getInstance().search(source, pattern);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testcase1() {
        String source = "AABAABAAABA";
        String pattern = "AABA";
        List<Integer> expected = Arrays.asList(0, 3, 7);
        test(source, pattern, expected);
    }

    @Test
    public void testcase2() {
        String source = "ABABDABABCDABABCABAB";
        String pattern = "ABABCD";
        List<Integer> expected = Arrays.asList(5);
        test(source, pattern, expected);
    }
}
