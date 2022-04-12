package letcode.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EraseOverlapIntervals {
    /**
     * 贪心算法，取出最小end，然后删除和 最小end 重叠的部分。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        List<int[]> temp = new ArrayList<>();
        for (int i = 0;i < intervals.length;i++){
            temp.add(intervals[i]);
        }
        temp.sort(Comparator.comparingInt(o -> o[1]));
        int count = 0;
        while (true){
            if (temp.isEmpty()){
                break;
            }
            int[] min = temp.get(0);
            temp.remove(min);
            Iterator<int[]> iterable = temp.iterator();
            while (iterable.hasNext()) {
                int[] item = iterable.next();
                if (item[0] < min[1]) {
                    iterable.remove();
                    count ++;
                }
            }

        }
        return count;
    }

    public static void main(String[] args){

        System.out.println(new EraseOverlapIntervals().eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));

        System.out.println(new EraseOverlapIntervals().eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2},{1,2}}));
    }
}
