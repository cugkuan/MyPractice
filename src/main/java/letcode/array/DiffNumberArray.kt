package letcode.array

class NumberArray(val nums: IntArray) {
    /**
     * 刚开始没有弄懂什么意思，前缀和
     */
    fun sumRange(left: Int, right: Int): Int {
        var index = left
        var sum = 0
        while (index <= right) {
            sum += nums[index]
            index++
        }
        return sum
    }
}

/**
 * 航班预订
 * https://leetcode.cn/problems/corporate-flight-bookings/
 * 下面的做法是比较传统的做法
 */
fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
    val results = IntArray(n)
    bookings.forEach { value ->
        val begin = value[0] - 1
        val end = value[1] - 1
        val num = value[2]
        for (i in begin..end) {
            results[i] = results[i] + num
        }
    }
    return results
}

/**
 * 使用差分法,请注意差分法的起始点
 */
fun corpFlightBookings2(bookings: Array<IntArray>, n: Int): IntArray {
    val diff = IntArray(n + 1)
    bookings.forEach { value ->
        val begin = value[0] - 1
        val end = value[1]
        val num = value[2]
        diff[begin] = diff[begin] + num
        diff[end] = diff[end] - num
    }
    // 进行数据恢复
    val results = IntArray(n)
    results[0] = diff[0]
    for (i in 1 until n) {
        results[i] = results[i - 1] + diff[i]
    }
    return results
}

fun main() {
    val i1 = intArrayOf(1, 2, 10)
    val i2 = intArrayOf(2, 3, 20)
    val i3 = intArrayOf(2, 5, 25)

    val array = Array<IntArray>(3) {
        when (it) {
            0 -> i1
            1 -> i2
            else -> i3
        }
    }
    corpFlightBookings2(array, 5)

}