package leetcode.p051to100

class LeetCode051NQueens {

    interface Method {
        fun solveNQueens(n: Int): List<List<String>>
    }

    internal class Dfs_Method : Method {

        override fun solveNQueens(n: Int): List<List<String>> {
            if (n < 4) {
                return if (n == 1) listOf(listOf("Q")) else emptyList()
            }
            val results = mutableListOf<List<Int>>()
            dfs(n, mutableListOf(), results)
            return printChessboards(results)
        }

        private fun dfs(layer: Int, selected: MutableList<Int>, results: MutableList<List<Int>>) {
            if (selected.size == layer) {
                results.add(selected.toList())
                return
            }
            val isInvalid = BooleanArray(layer)
            val curLayer = selected.size
            for (pos in selected.indices) {
                val fPos = selected[pos] - curLayer + pos
                val bPos = selected[pos] + curLayer - pos
                if (fPos >= 0) isInvalid[fPos] = true
                isInvalid[selected[pos]] = true
                if (bPos < layer) isInvalid[bPos] = true
            }
            for (i in isInvalid.indices) {
                if (!isInvalid[i]) {
                    selected.add(i)
                    dfs(layer, selected, results)
                    selected.removeAt(selected.lastIndex)
                }
            }
        }

        private fun printChessboards(results: List<List<Int>>): List<List<String>> {
            val chessboards = mutableListOf<List<String>>()
            val sb = StringBuilder()
            for (result in results) {
                val chessboard = mutableListOf<String>()
                for (pos in result) {
                    for (i in result.indices) {
                        sb.append(if (i == pos) 'Q' else '.')
                    }
                    chessboard.add(sb.toString())
                    sb.setLength(0)
                }
                chessboards.add(chessboard)
            }
            return chessboards
        }
    }
}