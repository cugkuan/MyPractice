package letcode.dp;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * <p>
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 注意：不允许旋转信封。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 示例 2：
 * <p>
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxEnvelopes {
    /**
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        // dp[i] 为 前 n 个信封。能装入的数量
        int max = 0;
        int dp[][] = new int[envelopes.length][2];
        for (int i = 0;i < envelopes.length;i++){
            dp[i][1] = 1;
            dp[i][0] = 1;
        }

        for (int i = 0; i < envelopes.length; i++) {
            int cW = envelopes[i][0];
            int cH = envelopes[i][1];
            int s1 = 1;
            int s2 = 1;
            for (int j = 0; j < i; j++) {
                int tW = envelopes[j][0];
                int tH = envelopes[j][1];
                // 表示信封能放入

                if (cW > tW && cH > tH){
                    s1 = Math.max(dp[j][0] +1,s1);
                }
                if (cW < tW && cH < tH){
                    s2 = Math.max(dp[j][1]+1,s2);
                }
            }
            dp[i][0] = s1;
            dp[i][1] = s2;
            max = Math.max(max, Math.max(s1,s2));
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] v = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(new MaxEnvelopes().maxEnvelopes(v));
    }
}
