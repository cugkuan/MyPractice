package letcode.backtrack


/**
 * 回溯算法的训练
 * 全排列,弄懂全排列就没有什么问题
 * 什么 是回溯
 */


private val result = ArrayList<List<Int>>()
fun List<Int>.print() {
    val value = StringBuilder("")
    forEach {
        value.append(it)
        value.append(",")
    }
    value.append("\n")
    print(value)
}

fun permute(nums: IntArray) {
    permuteBackTrack(nums.toMutableList(), ArrayList())
}
/**
 * 回溯算法的最基础版本；选择列表，路径列表；一定要弄懂 决策树
 */
private fun permuteBackTrack(pickList: List<Int>, trackList: ArrayList<Int>) {
    if (pickList.isEmpty()) {
        trackList.print()
        return
    }
    pickList.forEach { i ->
        trackList.add(i)
        val start = ArrayList(pickList).apply {
            remove(i)
        }
        permuteBackTrack(start, trackList)
        trackList.remove(i)
    }
}
/**
 *基础版本的优化版本
 */
fun permute2(nums: IntArray) {
    permuteBackTrack2(nums, ArrayList())
}
/**
 *  创建了大量的 pickList对象，下面的改进，通过路径列表可以推断出 选择列表
 */
private fun permuteBackTrack2(nums: IntArray, trackList: ArrayList<Int>) {
    if (nums.size == trackList.size) {
        trackList.print()
        return
    }
    nums.forEach { item ->
        if (trackList.contains(item).not()) {
            trackList.add(item)
            permuteBackTrack2(nums, trackList)
            trackList.removeLast()
        }

    }
}
fun main() {

    //  permute2(intArrayOf(1,2,3))
    //   nQueen(5)

//   val list = solveNQueens(4)
//
//    println(list.size)



}