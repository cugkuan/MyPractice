package letcode.dp;

/**
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。

请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。

假设每一种面额的硬币有无限个。 

题目数据保证结果符合 32 位带符号整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change-2
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class CoinChange2 {

    /**
     *  f(n，m) 前 n 种银币 对应 金额 为 m 的最大组合数量
     *  那么 有，对于  第 n 种 银币，可以是放入也可以是不能放入
     *  请注意，第 n 种银币，对应的币值 是 coins[n-1]
     *
     *  请注意下面的语句,有点绕
     *  假设，第 n 种银币能放入，那么  f(n,m-coins[n-1])  + f(n-1,m ) 就是 f(n,m) 的组合数
     *
     *
     */
    public int change(int amount,int[] coins){
        /**
         *  定义 dp[i][j] 表示前i种银币，金额为 j 的最大组合数
         */
        int[][] dp = new int[coins.length+1][amount+1];
        //当金额 为 0 的时候，组合数为1
        for (int i = 0;i <= coins.length;i++){
            dp[i][0] = 1;
        }
        for (int i = 1;i <= coins.length;i++){
            for (int j = 1;j <= amount;j++ ){
                if (j - coins[i-1] >= 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                } else{
                    dp[i][j] =  dp[i-1][j];
                }

            }
        }
        return dp[coins.length][amount];

    }

    public static void main(String[] args){

        System.out.println(new CoinChange2().change(5,new int[]{1,2,5}));
        System.out.println(new CoinChange2().change(3,new int[]{2}));
    }

}
