package letcode.dp;

import java.util.Arrays;

/**
 * 将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 *
 * 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 endi 结束。
 *
 * 甚至可以对这些片段自由地再剪辑：
 *
 * 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VideoStitching {

    public int videoStitching(int[][] clips, int time) {
        // 先排序
        Arrays.sort(clips, (o1, o2) -> {
            if (o1[0] - o2[0] == 0){
                return  o2[1] - o1[1];
            }else {
                return o1[0] - o2[0];
            }
        });
        if (clips[0][0] != 0){
            return -1;
        }
        int begin = -1;
        int value = 0;
        int distance = 0;
        int end = 0;
        // 选择区间内的最大长度
        int i = 0;
        while (true){
            if (end >= time){
                break;
            }
            if (i >=  clips.length){
                break;
            }
            if (clips[i][0] <= begin){
                int d = clips[i][1]  - begin;
                if (d > distance){
                    distance = d;
                    end = clips[i][1];
                }
                if (end >= time){
                    return value;
                }
            }else {
                if (clips[i][0] > end){
                    // 中间断层缺失
                    return -1;
                }
                begin = end;
                end = clips[i][1];
                distance = end  - begin;
                value ++;
            }
            i++;
        }
        if (end <  time){
            return -1;
        }
        return value;
    }

    public static void main(String[] args){



        System.out.println(new VideoStitching().videoStitching(new int[][]{{0,2},{4,8}},5) == -1);
        System.out.println(new VideoStitching().videoStitching(new int[][]{{0,2},{1,6},{3,10}},10) == 3);

        int[][] test1 = new int[][]{{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        System.out.println(new VideoStitching().videoStitching(test1,9) == 3);
        int[][] test2 = new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        System.out.println(new VideoStitching().videoStitching(test2,10)  == 3);



        int[][] test3 = new int[][]{{0,1},{1,2}};
        System.out.println(new VideoStitching().videoStitching(test3,5) == -1);
        int[][] test4 = new int[][]{ {5,7},{1,8},{0,0},{2,3},{4,5},{0,6},{5,10},{7,10}};
        System.out.println(new VideoStitching().videoStitching(test4,5) == 1);

    }

}
