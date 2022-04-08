package letcode.dp;

/**
 * 给你一个 只包含正整数 的 非空 数组nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanPartition {
    /**
     * 这个题有点巧妙啊，既然能分割成二个 子集，且和相等，那么一定有 子集的和 是 全集 和的一半；
     * 当我们想通了这一点时，一切变得容易了
     * 我们设 集合的和为 sum
     * 那么转变为，从集合中能否找出 元素和为 sum/2 转变为背包问题
     *
     * 设 f(n,m)表示 前n 个元素 和为 m 的存在情况，
     *
     *  那么对  f(n,m) 第 n-1 个元素 可以放入，也可能放不入 如果放不了，那么 f(n,m) = f(n-1,m),如果能放入，则有
     *   f(n,m) = f(n-1,m) || f(n,m - nums[i])
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int value :nums){
            sum+= value;
        }
        if (sum %2 != 0){
            return false;
        }
        int c = sum/2;
        // dp[i][j] 表示，前 i 个元素, 和为 j 是否满足
        boolean[][] dp = new boolean[nums.length +1][c +1];
        // 于是对于 和为 0
        for (int i = 0;i <= nums.length;i++){
            dp[i][0] = true;
        }
        for (int i = 1; i <= nums.length;i++){
            for (int j = 1; j <=c ;j++){
                if (j - nums[i-1] >=0){
                    dp[i][j] = dp[i-1][j] ||   dp[i-1][j - nums[i-1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][c];
    }
    public static void main(String[] args){
//        System.out.println(new CanPartition().canPartition(new int[]{1,5,11,5}));
//        System.out.println(new CanPartition().canPartition(new int[]{1,2,3,5}));
//        System.out.println(new CanPartition().canPartition(new int[]{1,2,3,8}));
//        System.out.println(new CanPartition().canPartition(new int[]{5,10,5}));

        System.out.println(new CanPartition().canPartition(new int[]{2,2,3,5}));
    }
}
