package letcode;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * // 给你一个包含 n 个整数的数组 nums，判断 nums  中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * //
 * //注意：答案中不可以包含重复的三元组。
 * 首先用最笨的方式实现，
 * - 解决重复问题
 * -  优化速度
 *
 */
public class A {
    /**
     * 上面的解法比较 原始，且问题很多，下面是一种优秀的算法
     * 如何去重的？
     * <p>
     * 如果现有的值跟前一个值相同，则是重复的，因为已经参与计算了
     *
     * @param nums
     */
    public static void b(int[] nums) {
        // 首先进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        for (int k = j + 1; k < nums.length; k++) {
                            if (k == j + 1 || nums[k - 1] != nums[k]) {
                                if (nums[i] + nums[j] + nums[k] == 0) {
                                    System.out.println("[" + nums[i] + "," + nums[j] + "," + nums[k] + "]");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 对 b 算法的优化，b 基础算法，并解决了重复输出的问题
     * a+b+c  = 0;
     * 必然有 a+（b+1）+（c-1）  = 0
     * <p>
     * 也就是 b在增长，那么符合条件 的 c 再减少
     * 可以使用双向指针，加快速度
     */
    public static void c(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int k = nums.length - 1;
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        while (j < k && nums[i] + nums[j] + nums[k] > 0) {
                            k--;
                        }
                        if (j == k) {
                            break;
                        }
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            System.out.println("[" + nums[i] + "," + nums[j] + "," + nums[k] + "]");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4, 1, 0};
        c(nums);
    }
}
