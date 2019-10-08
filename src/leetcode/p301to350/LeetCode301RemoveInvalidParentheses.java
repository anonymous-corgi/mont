package leetcode.p301to350;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LeetCode301RemoveInvalidParentheses {
    private final String s;
    private final List<String> expected;

    public LeetCode301RemoveInvalidParentheses(String s, List<String> expected) {
        this.s = s;
        this.expected = expected;
    }

    private interface Method {
        List<String> removeInvalidParentheses(String s);
    }

    private static final class DFS implements Method {

        public List<String> removeInvalidParentheses(String s) {
            List<String> res = new ArrayList<>();
            remove(s, 0, 0, new char[]{'(', ')'}, res);
            return res;
        }

        private void remove(String s, int start, int lastDelete, char[] pair, List<String> res) {
            int balance = 0;
            int index = start;
            // Find the index of the first unbalanced ')'
            for (; index < s.length(); index++) {
                if (s.charAt(index) == pair[0]) {
                    balance++;
                } else if (s.charAt(index) == pair[1]) {
                    balance--;
                }
                if (balance < 0) {
                    break;
                }
            }
            if (balance < 0) {
                // Has more pair[1] than pair[0]
                for (int delete = lastDelete; delete <= index; delete++) {
                    if (s.charAt(delete) == pair[1] && (delete == lastDelete || s.charAt(delete - 1) != pair[1])) {
                        remove(s.substring(0, delete) + s.substring(delete + 1), index, delete, pair, res);
                    }
                }
            } else if (balance > 0) {
                // Has more pair[0] than pair[1]
                s = new StringBuilder(s).reverse().toString();
                remove(s, 0, 0, new char[]{')', '('}, res);
            } else {
                // Fully balanced. But still need to determine whether s is reversed.
                res.add(pair[0] == '(' ? s : new StringBuilder(s).reverse().toString());
            }
        }
    }

    private static Method getMethod() {
        return new DFS();
    }

    private void test(String s, List<String> expected) {
        List<String> actual = getMethod().removeInvalidParentheses(s);
        Collections.sort(actual);
        Collections.sort(expected);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testcase() {
        test(s, expected);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {"()())()", Arrays.asList("(())()", "()()()")},
                {"(a)())()", Arrays.asList("(a)()()", "(a())()")},
                {")(", Arrays.asList("")},
                {"(()", Arrays.asList("()")},
        };
    }
}
