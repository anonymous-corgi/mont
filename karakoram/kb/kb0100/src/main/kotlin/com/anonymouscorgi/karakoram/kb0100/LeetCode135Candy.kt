package com.anonymouscorgi.karakoram.kb0100

internal interface LeetCode135Candy {

    fun candy(ratings: IntArray): Int

    object METHOD : LeetCode135Candy {

        override fun candy(ratings: IntArray): Int {
            val candies = IntArray(ratings.size) { 1 }

            for (i in 1 until ratings.size) {
                if (ratings[i] > ratings[i - 1]) {
                    candies[i] = candies[i - 1] + 1
                }
            }

            for (i in ratings.size - 2 downTo 0) {
                if (ratings[i] > ratings[i + 1]) {
                    candies[i] = maxOf(candies[i], candies[i + 1] + 1)
                }
            }

            return candies.sum()
        }
    }
}