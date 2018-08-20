package leetcode.p201to250;

public class LeetCode242ValidAnagram {
  
  public boolean isAnagram(String s, String t) {
    int[] alphabet = new int[26];
    for (char c : s.toCharArray()) alphabet[c - 'a']++;
    for (char c : t.toCharArray()) alphabet[c - 'a']--;
    for (int i : alphabet) if (i != 0) return false;
    return true;
  }

  public static void main(String[] args) {

  }

}
