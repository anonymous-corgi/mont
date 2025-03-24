package com.anonymouscorgi.karakoram.kb0050

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Hard

/**
 * LeetCode 76. Minimum Window Substring
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 *
 * of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 */
@Hard
internal interface LeetCode076MinimumWindowSubstring {

    fun minWindow(s: String, t: String): String

    @Accepted
    object METHOD : LeetCode076MinimumWindowSubstring {

        override fun minWindow(s: String, t: String): String {
            var deficiency = t.length
            val tCountArray = IntArray(128)
            for (char in t) {
                tCountArray[char.code]++
            }

            var minLeft = 0
            var minLength = Int.MAX_VALUE
            var left = 0
            for (right in s.indices) {
                if (tCountArray[s[right].code]-- <= 0) {
                    continue
                }
                --deficiency
                while (deficiency == 0) {
                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1
                        minLeft = left
                    }
                    if (tCountArray[s[left++].code]++ >= 0) {
                        deficiency++
                        break
                    }
                }
            }
            return if (minLength == Int.MAX_VALUE) {
                ""
            } else {
                s.substring(minLeft, minLeft + minLength)
            }
        }
    }
}