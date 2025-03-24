package com.anonymouscorgi.karakoram.kb0050

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Medium

/**
 * LeetCode 072. Edit Distance
 * Hard
 * <p>
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
@Medium
internal interface LeetCode072EditDistance {

    fun minDistance(word1: String, word2: String): Int

    @Accepted
    object METHOD : LeetCode072EditDistance {

        override fun minDistance(word1: String, word2: String): Int {
            val len1 = word1.length
            val len2 = word2.length

            // dp indicates the edit distance from word1.substring(i) to word2.substring(j).
            val dp = Array(len1 + 1) { IntArray(len2 + 1) }

            for (i in 1..len1) {
                dp[i][0] = i
            }
            for (j in 1..len2) {
                dp[0][j] = j
            }

            for (i in 1..len1) {
                for (j in 1..len2) {
                    dp[i][j] = if (word1[i - 1] == word2[j - 1]) {
                        dp[i - 1][j - 1]
                    } else {
                        minOf(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1
                    }
                }
            }
            return dp[len1][len2]
        }
    }
}