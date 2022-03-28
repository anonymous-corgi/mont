package algorithm.interview;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DialogSort {

    private interface Method {
        void sort(int[][] square);
    }

    private static final class QuickSort implements Method {

        public void sort(int[][] square) {
            int lastIndex = square.length - 1;
            for (int startX = 0; startX < square.length; startX++) {
                int endX = lastIndex;
                int endY = lastIndex - startX;
                sort(square, startX, 0, endX, endY);
            }
            for (int startY = 1; startY < square.length; startY++) {
                int endX = lastIndex - startY;
                int endY = lastIndex;
                sort(square, 0, startY, endX, endY);
            }
        }

        private void sort(int[][] square, int startX, int startY, int endX, int endY) {
            if (startX >= endX || startY >= endY) {
                return;
            }
            int leftX = startX - 1;
            int leftY = startY - 1;
            int rightX = endX + 1;
            int rightY = endY + 1;
            int cursorX = startX;
            int cursorY = startY;
            int pivot = square[startX][startY];
            while (cursorX < rightX && cursorY < rightY) {
                if (square[cursorX][cursorY] < pivot) {
                    swap(square, ++leftX, ++leftY, cursorX++, cursorY++);
                } else if (square[cursorX][cursorY] > pivot) {
                    swap(square, --rightX, --rightY, cursorX, cursorY);
                } else {
                    cursorX++;
                    cursorY++;
                }
            }

            sort(square, startX, startY, leftX, leftY);
            sort(square, rightX, rightY, endX, endY);
        }

        private void swap(int[][] square, int aX, int aY, int bX, int bY) {
            int temp = square[aX][aY];
            square[aX][aY] = square[bX][bY];
            square[bX][bY] = temp;
        }
    }

    private static Method getMethod() {
        return new QuickSort();
    }

    private void test(int[][] square, int[][] expected) {
        getMethod().sort(square);
        assertThat(square, equalTo(expected));
    }

    @Test
    public void testcase1() {
        int[][] square = new int[][]{{9, 8, 7,}, {6, 5, 4,}, {3, 2, 1,}};
        int[][] expected = new int[][]{{1, 8, 7,}, {6, 5, 4,}, {3, 2, 9,}};
        test(square, expected);
    }

    @Test
    public void testcase2() {
        int[][] square = new int[][]{{16, 15, 14, 13}, {4, 3, 2, 1,}, {8, 7, 6, 5,}, {12, 11, 10, 9,}};
        int[][] expected = new int[][]{{3, 2, 1, 13,}, {4, 6, 5, 14,}, {8, 7, 9, 15,}, {12, 11, 10, 16,}};
        test(square, expected);
    }
}