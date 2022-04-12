package letcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 *
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 *
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 *
 *  
 * 示例 1：
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 * 示例 2：
 *
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 解释：每个气球需要射出一支箭，总共需要4支箭。
 * 示例 3：
 *
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * - 在x = 2处发射箭，击破气球[1,2]和[2,3]。
 * - 在x = 4处射出箭，击破气球[3,4]和[4,5]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMinArrowShots {

    /**
     * 这个题和 最小重叠部分类似，计算重叠数
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        /**
         * 先排序
         */
        // 考虑极端情况下 数据对比
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] <0 &&  o2[1] > 0){
                    return -1;
                }else if (o1[1] >0 &&  o2[1] < 0){
                    return 1;
                }else {
                    return o1[1] - o2[1];
                }
            }
        });
        int minEnd = points[0][1];
        int count = 0;
        for (int i = 1;i  < points.length;i++){
            if (points[i][0] <= minEnd){
                count ++;
            }else {
                minEnd = points[i][1];
            }
        }
        return  points.length - count;
    }

    public static void main(String[] args){

        System.out.println(new FindMinArrowShots().findMinArrowShots(new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}}));
     //   System.out.println(new FindMinArrowShots().findMinArrowShots(new int[][]{{7,8},{1,2},{3,4},{5,6}}));
     //   System.out.println(new FindMinArrowShots().findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
    }
}
