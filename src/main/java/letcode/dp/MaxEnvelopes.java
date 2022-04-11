package letcode.dp;

import java.util.Arrays;
import java.util.Comparator;

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
      //  quickSort(envelopes);
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ?
                b[1] - a[1] : a[0] - b[0]);
        // LengthOfLIS 算法
        int[] en = new int[envelopes.length];
        for (int  i= 0; i < en.length;i++){
            en[i] = envelopes[i][1];
        }
        int[] dp = new int[en.length];
        dp[0] = 1;
        int max = 0;
        for (int i = 0; i < en.length;i++){
            int size = 1;
            for (int j = 0;j < i;j++){
                if (en[i] >  en[j]){
                    size = Math.max(dp[j] +1,size);
                }
            }
            dp[i] = size;
            max = Math.max(max,size);
        }
        return max;
    }

    private void quickSort(int[][] envelopes){
        internalQuickSort(envelopes,0,envelopes.length-1);
    }

    private int compare(int[][] envelopes,int left,int right ){
        if (envelopes[left][0] <  envelopes[right][0]){
            return 1;
        }else  if (envelopes[left][0] == envelopes[right][0]){
            if (envelopes[left][1] > envelopes[right][1]){
                return 1;
            }else if (envelopes[left][1] == envelopes[right][1]){
                return 0;
            }
        }
        return -1;
    }

    private void internalQuickSort(int[][] envelopes,int left,int right){
        if (left < right){
            int i = left;
            int j = right;
            while (i < j) {
                while (i < j && compare(envelopes,j,left) <= 0) {
                    j--;
                }
                while (i < j && compare(envelopes,i,left) > 0) {
                    i++;
                }

                if (compare(envelopes,i,j) == 0 && i < j){
                    i++;
                }else {
                    swap(envelopes,i,j);
                }
            }
            internalQuickSort(envelopes, left, i);
            internalQuickSort(envelopes, i + 1, right);
        }
    }

    private void swap(int[][] envelopes,int left,int right ){
        int t1 = envelopes[left][0];
        int t2 = envelopes[left][1];
        envelopes[left][0] = envelopes[right][0];
        envelopes[left][1] = envelopes[right][1];
        envelopes[right][0] = t1;
        envelopes[right][1] = t2;
    }


    public static void main(String[] args) {
        int[][] v = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(new MaxEnvelopes().maxEnvelopes(v));
    }
}
