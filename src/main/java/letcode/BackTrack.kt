package letcode


/**
 * 回溯算法的训练
 * 全排列
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

/*************************************************************************************具体题目**************/

/**
 * N 皇后问题
 * 问题表述为：在n×n格的国际象棋上摆放n个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 *
 * 问题分析，实际上也是一个决策树的问题
 */

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
                if (Math.abs(p - index) == Math.abs(value - item)) {
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

/***************************************************电话号码题目****************************************************************************/

/**
给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
**/

fun letterCombinations(digits: String): List<String> {
    val map = HashMap<Char,String>()
    map['2'] = "abc"
    map['3'] = "def"
    map['4'] = "ghi"
    map['5'] = "jkl"
    map['6'] = "mno"
    map['7'] = "pqrs"
    map['8'] = "tuv"
    map['9'] = "wxyz"
    val list = ArrayList<String>();
    for (c in digits.toCharArray()){
        map.get(c)?.let {
            list.add(it)
        }
    }
    val prints = ArrayList<String>()
    numberBacktrack(list,"",ArrayList<Char>(),prints)
    return prints
}

private fun numberBacktrack(number:List<String>,pickNum:String,tracks:ArrayList<Char>,prints:ArrayList<String>){
    if (number.size == tracks.size){
        tracks.forEach {
            print(it)
        }
        print("\n")
        return
    }
    val index = number.indexOf(pickNum)
    for (i in index+1 until  number.size){
        val num = number[i]
        num.toCharArray().forEach {  c->
            if (tracks.contains(c).not() ){
                tracks.add(c)
                numberBacktrack(number,num,tracks,prints)
                tracks.removeLast()
            }
        }
    }

}

fun main() {

    //  permute2(intArrayOf(1,2,3))
 //   nQueen(5)

//   val list = solveNQueens(4)
//
//    println(list.size)

    letterCombinations("23")


}