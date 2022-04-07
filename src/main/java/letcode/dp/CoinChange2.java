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
     *  f(n，m) 表示金额为 n ,前 m 种，硬币总的组合数
     *  金额为 n-1 时，金额 m 可以 放入 组成金额 为 n ，也可能没法放入组成
     *  f(n,m) = f(n-1,m) or f(n-1,m)+1
     */
    public int change(int amount,int[] coins){

        /**
         *  金额 为 a ,前 i 种 硬币总的组合数
         */
        int[][] dp = new int[amount+1][coins.length+1];
        // 当金额为 0 时，总的组合数为0
        for (int i = 0;i <= coins.length;i++) {
            dp[0][i] = 0;
        }
        for (int i = 1;i <= amount;i++){
            for (int j = 0;j < coins.length;j++){
                if (coins[j] - i == 0){
                    dp[i][j] = dp[i-1][j] +1;
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[amount][coins.length];

    }

    public static void main(String[] args){

        System.out.println(new CoinChange2().change(5,new int[]{1,2,5}));
    }

}
