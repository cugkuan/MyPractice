package letcode

import java.util.*
import kotlin.collections.HashMap

/**
 * // 给你一个包含 n 个整数的数组 nums，判断 nums  中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * //
 * //注意：答案中不可以包含重复的三元组。
 * 首先用最笨的方式实现，
 * - 解决重复问题
 * -  优化速度
 *
 */
/**
 * 上面的解法比较 原始，且问题很多，下面是一种优秀的算法
 * 如何去重的？
 *
 *
 * 如果现有的值跟前一个值相同，则是重复的，因为已经参与计算了
 *
 * @param nums
 */
fun b(nums: IntArray) {
    // 首先进行排序
    Arrays.sort(nums)
    for (i in nums.indices) {
        if (i == 0 || nums[i] != nums[i - 1]) {
            for (j in i + 1 until nums.size) {
                if (j == i + 1 || nums[j] != nums[j - 1]) {
                    for (k in j + 1 until nums.size) {
                        if (k == j + 1 || nums[k - 1] != nums[k]) {
                            if (nums[i] + nums[j] + nums[k] == 0) {
                                println("[" + nums[i] + "," + nums[j] + "," + nums[k] + "]")
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 对 b 算法的优化，b 基础算法，并解决了重复输出的问题
 * a+b+c  = 0;
 * 必然有 a+（b+1）+（c-1）  = 0
 *
 *
 * 也就是 b在增长，那么符合条件 的 c 再减少
 * 可以使用双向指针，加快速度
 */
fun c(nums: IntArray) {
    Arrays.sort(nums)
    for (i in nums.indices) {
        if (i == 0 || nums[i] != nums[i - 1]) {
            var k = nums.size - 1
            for (j in i + 1 until nums.size) {
                if (j == i + 1 || nums[j] != nums[j - 1]) {
                    while (j < k && nums[i] + nums[j] + nums[k] > 0) {
                        k--
                    }
                    if (j == k) {
                        break
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        println("[" + nums[i] + "," + nums[j] + "," + nums[k] + "]")
                    }
                }
            }
        }
    }
}
// 两个数之和
/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 对于一个 x ,需要找到一个对应的 target -x
 *
 * @param nums
 * @param target
 */
fun towNumSum(nums: IntArray, target: Int): IntArray? {
    val map = HashMap<Int,Int>();
    for (i in nums.indices){
        val value = nums[i]
        val targetValue = map[target - value]
        if (targetValue == null){
            map[target- value] = i
        }else{
            return intArrayOf(i,targetValue)
        }
    }
    return null
}


// 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。

fun subarraySum(nums: IntArray,k:Int) :Int{
    var total = 0
    for (i  in nums.indices){
        var index = i
        var target = nums[index]
        if (target == k){
            total++
        }
        while (index +1 < nums.size){
            index++
            target += nums[index]
            if (target == k){
                total++
            }
        }
    }
    return total
}

/**
 *
 * pre[j-1] = pre[i] -k
 */

fun subarraySum2(nums: IntArray,k:Int) :Int{
    var pre = 0
    var count = 0
    val map = HashMap<Int,Int>()
    map[0] = 1
    for (i in nums.indices){
        pre+= nums[i];
        if (map.containsKey(pre -k)){
            count  = map[pre]!! +1
        }
        map[pre] = map.getOrDefault(pre,0)+1
    }
    return count
}

fun main(args: Array<String>) {
    val nums = intArrayOf(-1, 0, 1, 2, -1, -4, 1, 0)
   // c(nums)
    println("${subarraySum( intArrayOf(-1,1,0),0)}")
    println("${subarraySum2( intArrayOf(-1,1,0),0)}")
}
