package interview.gs;

public class RotateString {

    private static String rotateTheString(String originalString, int[] direction, int[] amount) {
        int move = 0;
        for (int i = 0; i < direction.length; i++) {
            if (direction[i] == 0) {
                move -= amount[i];
            } else {
                move += amount[i];
            }
        }

        move %= originalString.length();
        if (move < 0) {
            move += originalString.length();
        }
        if (move == 0) {
            return originalString;
        } else {
            return originalString.substring(originalString.length() - move) + originalString.substring(0, originalString.length() - move);
        }
    }
}
