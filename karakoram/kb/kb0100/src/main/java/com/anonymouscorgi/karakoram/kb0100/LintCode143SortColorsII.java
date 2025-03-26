package com.anonymouscorgi.karakoram.kb0100;

/**
 * LintCode 143 · Sort Colors II
 * <p>
 * Description
 *
 * Given an array of n objects with k different colors (numbered from 1 to k), sort them so that
 * objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
 */
interface LintCode143SortColorsII {

  void sortColors2(int[] colors, int k);

  // Accepted
  LintCode143SortColorsII QUICK_SORT = new LintCode143SortColorsII() {

    @Override
    public void sortColors2(int[] colors, int k) {
      if (colors == null || colors.length == 0) {
        return;
      }
      sortColors(colors, 1, k, 0, colors.length - 1);
    }

    private void sortColors(int[] colors, int kStart, int kEnd, int startIndex,
        int endIndex) {
      if (kStart >= kEnd || startIndex >= endIndex) {
        return;
      }

      int k = (kStart + kEnd) / 2;
      int lowNumIndex = startIndex;
      int highNumIndex = endIndex;
      int cursor = startIndex;
      while (cursor <= highNumIndex) {
        if (colors[cursor] < k) {
          swap(colors, cursor, lowNumIndex);
          lowNumIndex++;
          cursor++;
        } else if (colors[cursor] > k) {
          swap(colors, cursor, highNumIndex);
          highNumIndex--;
        } else {
          cursor++;
        }
      }
      sortColors(colors, kStart, k - 1, startIndex, lowNumIndex - 1);
      sortColors(colors, k + 1, kEnd, highNumIndex + 1, endIndex);
    }

    private void swap(int[] colors, int a, int b) {
      if (a == b) {
        return;
      }
      int temp = colors[a];
      colors[a] = colors[b];
      colors[b] = temp;
    }
  };
}
