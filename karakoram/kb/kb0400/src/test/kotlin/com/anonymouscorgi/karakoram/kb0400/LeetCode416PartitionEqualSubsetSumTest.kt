package com.anonymouscorgi.karakoram.kb0400

import com.anonymouscorgi.karakoram.kb0400.LeetCode416PartitionEqualSubsetSum.*
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.provider.Arguments.arguments

import java.util.stream.Stream
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LeetCode416PartitionEqualSubsetSumTest {

    companion object {
        @JvmStatic
        fun generateTestParametersStream(): Stream<Arguments> {
            return Stream.of<Arguments>(
                arguments(
                    /* ...arguments = */ intArrayOf(1, 5, 11, 5),
                    /* ...arguments = */ true
                ),
                arguments(
                    /* ...arguments = */ intArrayOf(1, 2, 3, 5),
                    /* ...arguments = */ false
                ),
                arguments(
                    /* ...arguments = */ intArrayOf(2,2,1,1),
                    /* ...arguments = */ true
                ),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("generateTestParametersStream")
    fun canPartition(nums: IntArray, expected: Boolean) {
        assertThat(METHOD.canPartition(nums)).isEqualTo(expected)
    }
}