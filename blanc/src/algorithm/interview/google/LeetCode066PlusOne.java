package algorithm.interview.google;

public class LeetCode066PlusOne {
  //Google Onsite: Use Backtracking to do it.
  public int[] plusOne(int[] digits) {
    if (digits == null || digits.length == 0) {
        return new int[0];
    }
    int[] res = null;
    if (helper(digits, 0) == 1) {
        res = new int[digits.length + 1];
        res[0] = 1;
    } else {
        res = new int[digits.length];
    }
    for (int i = 1; i <= digits.length; i++) {
        res[res.length - i] = digits[digits.length - i];
    }
    return res;
}

private int helper(int[] digits, int pos) {
    int remainer;
    if (pos == digits.length - 1) {
        remainer = 1;
    } else {
        remainer = helper(digits, pos + 1);
    }
    int sum = digits[pos] + remainer;
    digits[pos] = sum % 10;
    return sum / 10;
}

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
