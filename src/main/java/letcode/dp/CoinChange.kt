package letcode.dp

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。

 

示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：

输入：coins = [2], amount = 3
输出：-1
示例 3：

输入：coins = [1], amount = 0
输出：0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class CoinChange {

    fun coinChange(coins: IntArray, amount: Int): Int {

        val dp = IntArray(amount+1)

        // -2 表示还没有参与计算
        repeat(amount+1){
            dp[it] = -2
        }
        dp(dp,coins,amount)

        return  dp[amount]
      //  return fn(coins,amount)
    }

    /**
     * 找出状态方程，使用递归
     * 我们定义 f[n] 为 amount 为 n  的最少硬币数，于是状态转移方程为:
     * f[n] = min(f[n- coin],1)
     */
    private fun fn(coins: IntArray, amount: Int): Int {
        if (amount < 0) {
            return -1
        }
        if (amount == 0) {
            return 0
        }
        var value = Int.MAX_VALUE
        for (coin in coins) {
            val subProblem = fn(coins, amount -coin)
            if (subProblem == -1) {
                continue
            }
            value = Math.min(value, subProblem + 1)

        }
        return if (value == Int.MAX_VALUE) -1 else value
    }

    /**
     * 递归的一种优化而已，动态规划，无非是将已经计算的值存起来，减少重复计算
     * dp[n] 表示金额为n 时，的最小银币数量
     */
    private fun dp(dp:IntArray,coins: IntArray,amount: Int):Int{
        if (amount < 0){
            return -1
        }
        if (amount == 0){
            dp[amount] = 0
            return 0
        }
        if (dp[amount] == -2){
            // 说明需要进行计算
            var value = Int.MAX_VALUE
            for (coin in coins) {
                val subProblem = dp(dp,coins, amount -coin)
                if (subProblem == -1) {
                    continue
                }
                value = Math.min(value, subProblem + 1)

            }
            dp[amount] = if (value == Int.MAX_VALUE) -1 else value
        }
        return dp[amount]
    }
}

fun main(){
    println(CoinChange().coinChange(intArrayOf(1,2,5),11))
    println(CoinChange().coinChange(intArrayOf(2),3))
    println(CoinChange().coinChange(intArrayOf(1),0))
}