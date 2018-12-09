package leetcode.p201to250

class LeetCode208ImplementTrie {

    interface Trie {
        /** Inserts a word into the trie. */
        fun insert(word: String)

        /** Returns if the word is in the trie. */
        fun search(word: String): Boolean

        /** Returns if there is any word in the trie that starts with the given prefix. */
        fun startsWith(prefix: String): Boolean
    }

    class Solution : Trie {

        class TrieNode (private var value: Char) {
            var next = arrayOfNulls<TrieNode>(26)
            var isWord: Boolean = false
        }

        /** Initialize your data structure here. */
        private val root = TrieNode('a')
        private val STANDARD = 'a'.toInt()

        override fun insert(word: String) {
            val chars = word.toCharArray()
            var node = root
            for (char in chars) {
                val num = char.toInt() - STANDARD
                node = node.next[num]?: TrieNode(char).apply { node.next[num] = this}
            }
            node.isWord = true
        }

        override fun search(word: String): Boolean {
            val chars = word.toCharArray()
            var node = root
            for (char in chars) {
                val num = char.toInt() - STANDARD
                val next = node.next[num]
                if (next == null) {
                    return false
                } else {
                    node = next
                }
            }
            return node.isWord
        }

        override fun startsWith(prefix: String): Boolean {
            val chars = prefix.toCharArray()
            var node = root
            for (char in chars) {
                val num = char.toInt() - STANDARD
                val next = node.next[num]
                if (next == null) {
                    return false
                } else {
                    node = next
                }
            }
            return true
        }
    }
}