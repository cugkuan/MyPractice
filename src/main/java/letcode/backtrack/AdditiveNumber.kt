package letcode.backtrack

import letcode.c

/**
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。

一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。

给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。

说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/additive-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 这个题耗费的时间比较多，其实也有回溯的思想
 */
class AdditiveNumber {

    fun isAdditiveNumber(num: String): Boolean {
        for (a in 1..num.length) {
            for (b in a + 1..num.length) {
                if (check(num, a, b)) {
                    return true
                }
            }
        }
        return false
    }
    private fun check(num: String, a: Int, b: Int): Boolean {
        var i = 0
        var j = a
        var k = b
        while (i <= num.length && j <= num.length && k <= num.length){
            if (j - i > 1 && num.substring(i, i + 1) == "0") {
                return false
            }
            val first = num.substring(i, j).toLong()
            if (k - j > 1 && num.substring(j, j + 1) == "0") {
                return false
            }
            val second = num.substring(j, k).toLong()
            val sum = first + second
            val offset = checkSum(num, k, sum)
            if (offset < 0) {
                return false
            } else {
                if (offset == num.length) {
                    return true
                } else {
                    i = j
                    j = k
                    k = offset
                }
            }
        }
        return false
    }

    private fun checkSum(num: String, index: Int, sum: Long): Int {
        for (i in index + 1..num.length) {
            if (i - index > 1) {
                if (num.substring(index, index+1).toInt() == 0) {
                    return  -1
                }
            }
            val value = num.substring(index, i).toLong()
            if (value < sum) {
                continue
            }
            if (value == sum) {
                return i
            }
            if (value > sum) {
                return -1
            }
        }
        return -1
    }
}

fun main() {
    //val count = "112358"
    //  val count = "199100199"
    val count = "1023"

    val additiveNumber = AdditiveNumber()

    println(additiveNumber.isAdditiveNumber("0235813") == false)
    println(additiveNumber.isAdditiveNumber("1203") == false)

    println(additiveNumber.isAdditiveNumber("12012122436") == true)
    println(additiveNumber.isAdditiveNumber("12122436") == true)

    println(additiveNumber.isAdditiveNumber("211738") == true)
    println(additiveNumber.isAdditiveNumber("1023") == false)

    println(additiveNumber.isAdditiveNumber("101") == true)
    println(additiveNumber.isAdditiveNumber("111") == false)
    println(additiveNumber.isAdditiveNumber("199100199") == true)
    println(additiveNumber.isAdditiveNumber("112358") == true)


    println(additiveNumber.isAdditiveNumber("9910011992") == false)


}