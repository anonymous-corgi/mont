package com.anonymouscorgi.karakoram.kb0000;

interface LeetCode003LongestSubstringWithoutRepeatingCharacters {

  int lengthOfLongestSubstring(String s);

  LeetCode003LongestSubstringWithoutRepeatingCharacters GREEDY = new LeetCode003LongestSubstringWithoutRepeatingCharacters() {

    @Override
    public int lengthOfLongestSubstring(String s) {
      int left = -1, maxLength = 0;
      short[] charCount = new short[128];
      for (int right = 0; right < s.length(); right++) {
        if (++charCount[s.charAt(right)] == 2) {
          while (--charCount[s.charAt(++left)] == 0)
            ;
        } else {
          maxLength = Math.max(maxLength, right - left);
        }
      }
      return maxLength;
    }
  };

  LeetCode003LongestSubstringWithoutRepeatingCharacters GREEDY_CACHE = new LeetCode003LongestSubstringWithoutRepeatingCharacters() {

    @Override
    public int lengthOfLongestSubstring(String s) {
      int max = 0;
      char[] chs = s.toCharArray();
      int[] lastIndex = new int[128];
      for (int start = 0, end = 0; end < s.length(); end++) {
        start = Math.max(lastIndex[chs[end]], start);
        lastIndex[chs[end]] = end + 1;
        max = Math.max(end - start + 1, max);
      }
      return max;
    }
  };
}
