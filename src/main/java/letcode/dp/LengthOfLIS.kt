package letcode.dp


/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。


示例 1：

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
示例 2：

输入：nums = [0,1,0,3,2,3]
输出：4
示例 3：

输入：nums = [7,7,7,7,7,7,7]
输出：1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LengthOfLIS {


    /**
     * 注意这个状态转移方程，很巧妙
     * 定于 f(n) 为 前 nums 前 n项 最长严格递增子序列的长度，那么有
     *
     * f(n+1) = max{ if(nums[n+1] > nums[i]) f(i)+1 else f(i)} (0<=i<=n )
     */
    fun lengthOfLIS(nums: IntArray): Int {
        var max =1
        val dp =  IntArray(nums.size +1)
        repeat(nums.size){
            dp[it] = 1
        }
        for (i in nums.indices){
            var size = 1
            for (j in 0 until  i){
                if (nums[i] > nums[j]){
                    size = Math.max(dp[j]+1,size)
                }
            }
            dp[i] = size
            max = Math.max(max,dp[i])
        }
        return max
    }
}

fun main(){
    println(LengthOfLIS().lengthOfLIS(intArrayOf(1,1,1,5,3,7,101,18)) == 4)
    println(LengthOfLIS().lengthOfLIS(intArrayOf(10,9,2,5,3,7,101,18)) == 4)
    println(LengthOfLIS().lengthOfLIS(intArrayOf(10,9,2,5,3,4)) ==3)
    println(LengthOfLIS().lengthOfLIS(intArrayOf(7,7,7,7,7,7,7)) == 1)
    println(LengthOfLIS().lengthOfLIS(intArrayOf(4,10,4,3,8,9)) == 3)
    println(LengthOfLIS().lengthOfLIS(intArrayOf(0,1,0,3,2,3)) == 4)
}