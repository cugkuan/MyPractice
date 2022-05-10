package letcode.array


/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
fun removeDuplicates(nums: IntArray): IntArray {

    var i=  -1
    for (j in nums.indices) {
        if (i == -1){
            i = 0
        }else if (nums[i] != nums[j]){
            i++
            nums[i] = nums[j]
        }
    }
    return  nums

}

/**
 * 其实就是二分查找法
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/submissions/
 */
fun twoSum(numbers: IntArray, target: Int): IntArray {
    var left = 0
    var right  = numbers.size -1
    while (left < right ){
        val  value = numbers[left] +numbers[right]
        if (value < target){
            left ++
        }else if (value == target){
            return intArrayOf(left+1,right+1)
        }else{
            right --
        }
    }
    return intArrayOf()

}

fun main(){

    val nums = intArrayOf(5,25,75)

 //   println( twoSum(nums,100))
    println( twoSum(intArrayOf(2,7,11,15),9))

}