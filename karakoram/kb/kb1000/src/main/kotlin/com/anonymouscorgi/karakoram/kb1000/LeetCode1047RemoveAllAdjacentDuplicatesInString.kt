package com.anonymouscorgi.karakoram.kb1000

import com.anonymouscorgi.karakoram.annotation.Accepted
import com.anonymouscorgi.karakoram.annotation.Easy

@Easy
internal interface LeetCode1047RemoveAllAdjacentDuplicatesInString {

    fun removeDuplicates(s: String): String

    @Accepted
    object METHOD : LeetCode1047RemoveAllAdjacentDuplicatesInString {

        override fun removeDuplicates(s: String): String {
            val stack = ArrayDeque<Char>()
            for (char in s) {
                if (stack.isNotEmpty() && stack.last() == char) {
                    stack.removeLast()
                } else {
                    stack.addLast(char)
                }
            }
            return stack.joinToString("")
        }
    }
}