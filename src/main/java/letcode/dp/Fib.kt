package letcode.dp

/**
 * 斐波那契数
 *
 * 这个是动态规划最简单的版本
 *
 * 斐波那契数（通常用F(n) 表示）形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定n ，请计算 F(n) 。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Fib {
    /**
     * 递归
     */
    fun  recursion(n:Int):Int{
        if (n == 0){
            return  0
        }
        if ( n == 1){
            return 1
        }
        return recursion(n-1) + recursion(n-1)
    }

    /**
     * 主要是找状态转移方程
     * 我们设 dp[n] 为 F(n)，那么 dp[n] = dp[n-1] +dp[n-2]
     */
    fun fib(n:Int):Int{
        val dp = IntArray(n+1)
        if (n == 0){
            return 0
        }
        if ( n == 1){
            return 1
        }
        for (index in 2..n){
            dp[index] = dp[index-1] +dp[index-2]
        }
        return dp[n]
    }
}

fun main(){
    println(Fib().fib(4))
}