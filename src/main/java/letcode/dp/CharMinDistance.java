package letcode.dp;

/**
 * 给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CharMinDistance {


    /**
     * 字符的操作有插入，删除，替换，跳过这些操作
     *
     * 既然有字符串的处理，那么，一定有字符串的对比。
     *
     * 我们知道了字符串的处理，无非是删除，插入，替换和跳过
     * 我们定义 f(n,m) 为 字符串 a,b 对比的 指针 移动 到n,m 时的移动的最小 步骤数
     *
     * 如果 a.charAt(n) == b.charAt(m) 表示这个字符串可以直接跳过，那么 f(n,m) = f(n-1,m-1)
     *
     * 如果是删除，m 不用动。就有 f(n,m) = f(n-1,m) +1
     * 如果是增加，n 不用动f(n,m) = f(n,m-1) +1
     * 如果是替换，n,m 都要动 f(n,m) = f(n-1,m-1)+1
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp  = new int[m+1][n+1];
        for (int i = 0;i <= m ;i++){
            dp[i][0] = i;
        }
        for (int j = 0;j <= n;j++){
            dp[0][j] = j;
        }
        for (int i = 1;i <= m;i++){
            for (int j = 1; j <=n ;j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    // 跳过
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    int insert = dp[i][j-1] ;
                    int replace = dp[i-1][j-1] ;
                    int delete = dp[i-1][j];
                    // 取最小值
                    int min = Math.min(insert,replace);
                    min = Math.min(min,delete);
                    dp[i][j] = min +1;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args){

        System.out.println(new CharMinDistance().minDistance("horse","ros"));
    }
}
