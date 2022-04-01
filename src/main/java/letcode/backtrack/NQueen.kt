package letcode.backtrack

import kotlin.math.abs

/**
 * N 皇后问题
 * 问题表述为：在n×n格的国际象棋上摆放n个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 *
 * 问题分析，实际上也是一个决策树的问题
 */
class NQueen {

    private val nQResult = ArrayList<ArrayList<Int>>()
    fun nQueen(n: Int) {
        val intArray = IntArray(n) { index ->
            index + 1
        }
        nQueenBackTrack(intArray, ArrayList<Int>(n))
    }

    private fun nQueenBackTrack(nums: IntArray, trackList: ArrayList<Int>) {
        if (nums.size == trackList.size) {
            nQResult.add(ArrayList<Int>(trackList))
            trackList.print()
            return
        }
        nums.forEach { item ->
            if (nQueenCondition(item, trackList)) {
                trackList.add(item)
                nQueenBackTrack(nums, trackList)
                trackList.removeLast()
            }
        }
    }

    private fun nQueenCondition(item: Int, trackList: ArrayList<Int>): Boolean {
        return if (trackList.isEmpty()) true else {
            if (trackList.contains(item)) {
                false
            } else {
                // 对角线的检查
                val p = trackList.size
                trackList.forEachIndexed() { index, value ->
                    if (abs(p - index) == abs(value - item)) {
                        return false
                    }
                }
                return true
            }
        }
    }

    /**
     * lectCode 上的要求输出
     */
    fun solveNQueens(n: Int): List<List<String>> {
        val intArray = IntArray(n) { index ->
            index + 1
        }
        nQueenBackTrack(intArray, ArrayList<Int>(n))
        val result = ArrayList<List<String>>()
        nQResult.forEach { first ->
            val list = ArrayList<String>()
            first.forEach { item ->
                val c = StringBuilder()
                repeat(n) { index ->
                    if (index + 1 == item) {
                        c.append("Q")
                    } else {
                        c.append(".")
                    }
                }
                list.add(c.toString())
            }
            result.add(list)
        }
        return result
    }
}

fun main() {
    NQueen().nQueen(5)
}