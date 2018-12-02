package leetcode.p751to800

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.Serializable
import java.util.*

@RunWith(Parameterized::class)
class LeetCode787CheapestFlightsWithinKStops(val res: Int,
                                             val n: Int,
                                             val flights: Array<IntArray>,
                                             val src: Int,
                                             val dst: Int,
                                             val K: Int) {

    private val method: Method
        get() = DP_method()

    interface Method {
        fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int
    }

    class Dijkstra_method : Method {

        internal class Node(var city: Int, var cost: Int, var stop: Int) : Comparable<Node> {

            override fun compareTo(node: Node): Int {
                return this.cost - node.cost
            }
        }

        override fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
            val prices = IntArray(n) { i -> if (i != src) Int.MAX_VALUE else 0 }
            val stops = IntArray(n) { i -> if (i != src) Int.MAX_VALUE else 0 }
            val lines = Array<MutableMap<Int, Int>>(n) { mutableMapOf() }
            val tasks = PriorityQueue<Node>().apply { offer(Node(src, 0, 0)) }
            for (flight in flights) {
                lines[flight[0]][flight[1]] = flight[2]
            }
            while (!tasks.isEmpty()) {
                val cursor = tasks.poll()
                if (cursor.city == dst) return cursor.cost
                if (cursor.stop > K) continue
                lines[cursor.city].forEach { (nextCity, value) ->
                    val price = cursor.cost + value
                    if (price < prices[nextCity]) {
                        prices[nextCity] = price
                        tasks.offer(Node(nextCity, price, cursor.stop + 1))
                    } else if (cursor.stop + 1 < stops[nextCity]) {
                        stops[nextCity] = cursor.stop + 1
                        tasks.offer(Node(nextCity, price, cursor.stop + 1))
                    }
                }
            }
            return -1
        }
    }

    class DP_method : Method {

        override fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
            val prices = IntArray(n) { i -> if (i == src) 0 else Int.MAX_VALUE }
            for (stop in 0..K) {
                val lastPrices = prices.clone()
                for (flight in flights) {
                    if (lastPrices[flight[0]] != Integer.MAX_VALUE) {
                        prices[flight[1]] = Math.min(prices[flight[1]], lastPrices[flight[0]] + flight[2])
                    }
                }
            }
            return if (prices[dst] != Int.MAX_VALUE) prices[dst] else -1
        }
    }

    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        return method.findCheapestPrice(n, flights, src, dst, K)
    }

    @Test
    fun testFindCheapestPrice() {
        assertEquals(res, findCheapestPrice(n, flights, src, dst, K))
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Serializable>> {
            return listOf(
                    arrayOf(500, 3, arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)), 0, 2, 0),
                    arrayOf(200, 3, arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)), 0, 2, 1)
            )
        }
    }

}
