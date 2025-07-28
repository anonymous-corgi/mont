package com.anonymouscorgi.karakoram.kb0600

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Hard
import kotlin.math.max

@Hard
internal interface LeetCode629KInversePairsArray {

  fun kInversePairs(n: Int, k: Int): Int

  @Accepted
  object DP_Base : LeetCode629KInversePairsArray {

    val MOD: Int = 1_000_000_007
    override fun kInversePairs(n: Int, k: Int): Int {
      val possibility = Array(k + 1) { IntArray(n + 1) }
      possibility[0].fill(1)
      for (numIndex in 2..n) {
        for (kIndex in 1..k) {
          if (kIndex - numIndex + 1 >= 0 && possibility[kIndex - numIndex + 1][numIndex - 1] == 0) {
            break
          }
          val start = max(0, kIndex - numIndex + 1)
          for (subKIndex in start..kIndex) {
            possibility[kIndex][numIndex] += possibility[subKIndex][numIndex - 1]
            possibility[kIndex][numIndex] = possibility[kIndex][numIndex] % MOD
          }
        }
      }
      return possibility[k][n]
    }
  }

  @Accepted
  object DP_Improved : LeetCode629KInversePairsArray {

    val MOD: Int = 1_000_000_007
    override fun kInversePairs(n: Int, k: Int): Int {
      val possibility = Array(k + 1) { IntArray(n + 1) }
      possibility[0].fill(1)
      for (numIndex in 2..n) {
        for (kIndex in 1..k) {
          possibility[kIndex][numIndex] = possibility[kIndex - 1][numIndex]
          if (kIndex - numIndex + 1 >= 0) {
            possibility[kIndex][numIndex] += possibility[kIndex][numIndex - 1] - possibility[kIndex - numIndex + 1][numIndex - 1]
          } else {
            possibility[kIndex][numIndex] += possibility[kIndex][numIndex - 1]
          }
          if (possibility[kIndex][numIndex] > MOD) {
            possibility[kIndex][numIndex] = possibility[kIndex][numIndex] % MOD
          } else if (possibility[kIndex][numIndex] < 0) {
            possibility[kIndex][numIndex] += MOD
          }
        }
      }
      return possibility[k][n]
    }
  }
}