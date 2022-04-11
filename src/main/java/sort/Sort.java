package sort;

import java.util.Arrays;

/**
 *
 * 五种最基础的排序算法
 * 冒泡排序
 * 选择排序
 * 插入排序
 * 归并排序
 * 快速排序
 *
 * 其它的排序算法
 *
 */
public  abstract class Sort {

    public static int[] input = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};

    public abstract  void sort(int[] input);
    protected void swap(int[] input,int left,int right){
        int temp = input[left];
        input[left] =input[right];
        input[right] = temp;
    }
    public static void count(int[] input){
        Arrays.stream(input).forEach(value -> System.out.print(value + ","));
    }

}
