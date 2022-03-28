package algorithm.leetcode.p751to800;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 765. Couples Holding Hands
 * Hard
 *
 * N couples sit in 2N seats arranged in a row and want to hold hands.
 * We want to know the minimum number of swaps so that every couple is sitting side by side.
 * A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * The people and seats are represented by an integer from 0 to 2N-1,
 * the couples are numbered in order, the first couple being (0, 1),
 * the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
 *
 * The couples' initial seating is given by row[i] being the value of the person
 * who is initially sitting in the i-th seat.
 *
 *
 * Example 1:
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 *
 *
 * Example 2:
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 * Note:
 *
 * len(row) is even and in the range of [4, 60].
 * row is guaranteed to be a permutation of 0...len(row)-1.
 */
public class LeetCode765CouplesHoldingHands {

    private interface Method {
        int minSwapsCouples(int[] row);
    }

    private static final class Impl implements Method {

        public int minSwapsCouples(int[] row) {
            int res = 0;
            // Store the partner's ID of the person i, or the paired position of the position i.
            int[] partner = new int[row.length];
            // Store the position of the people with ID i.
            int[] position = new int[row.length];
            for (int i = 0; i < row.length; i++) {
                partner[i] = (i % 2 == 0 ? i + 1 : i - 1);
                position[row[i]] = i;
            }

            for (int posI = 0; posI < row.length; posI++) {
                int posJ;
                // If posJ (the paired position of the partner of the person in posI) is not posI itself.
                while ((posJ = getPartnerPairedPosition(posI, row, partner, position)) != posI) {
                    swap(row, posI, posJ);
                    swap(position, row[posI], row[posJ]);
                    res++;
                }
            }

            return res;
        }

        // Return the paired position of the partner of the person in posI
        private int getPartnerPairedPosition(int posI, int[] row, int[] partner, int[] position) {
            int personId = row[posI];
            int partnerId = partner[personId];
            int partnerPosition = position[partnerId];
            int pairedPosition = partner[partnerPosition];
            return pairedPosition;
        }

        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    private static Method getMethod() {
        return new Impl();
    }

    private void test(int[] row, int expected) {
        int actual = getMethod().minSwapsCouples(row);
        assertThat(actual, is(expected));
    }

    @Test
    public void testcase1() {
        int[] row = new int[]{0, 2, 1, 3};
        int expected = 1;
        test(row, expected);
    }
}