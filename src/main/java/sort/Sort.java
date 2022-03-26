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
public class Sort {
    public static int[] input = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
    public static void count(int[] input){
        Arrays.stream(input).forEach(value -> System.out.print(value + ","));
    }
    public static void swap(int i,int j,int[] input){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    /**
     * 冒泡排序
     * @param input
     */
    public static void bubbleSort(int[] input){
        for (int i = input.length - 1;i >= 0;i--){
            for (int j = 0;j < i;j++){
                if (input[j] > input[j+1]){
                    swap(j,j+1,input);
                }
            }
        }
    }
    /**
     * 选择排序
     * @param input
     */
    public static void selectSort(int[] input){
        for (int i = 0;i < input.length;i++ ){
            int index = i;
            for (int j = i;j < input.length;j++){
                if (input[index] > input[j]){
                    index = j;
                }
            }
            swap(i,index,input);
        }
    }
    /** 这个掌握的不是很熟，注意从已经排的右边开始插入
     * 插入排序
     */

    public static void insertSort(int[] input){
        for (int i = 1;i < input.length;i++){
            int card = input[i];
            //从已经排好序的右边开始插入
            int j = i;
            while (j > 0 && card < input[j-1]){
                input[j] = input[j-1];
                j--;
            }
            if (i  != j) {
                input[j] = card;
            }
        }
    }

    private static void internalMergeSort(int[] input,int[] assist,int left,int right){
        if (left < right){
            int middle = (left + right)/2;
            internalMergeSort(input,assist,left,middle);
            internalMergeSort(input,assist,middle+1,right);
            doubleMerger(input,assist,left,right);
        }
    }
    // 关键代码，注意归并过程，下面的代码繁琐的再现了整个繁琐的过程
    // 二个已经排序好的子序列，归并成一个，这个过程是难点，需要一个额外空间去进行归并
    // 典型的 分而治之 的方法论
    private static void doubleMerger(int[] input,int[] assist,int left,int right){
        int middle = (left + right)/2;
        int i = left;
        int j = middle +1;
        int k = 0;
        while (i <= middle && j <= right){
            if (input[i] < input[j]){
                assist[k] = input[i];
                k++;
                i++;
            }else {
                assist[k] = input[j];
                k++;
                j++;
            }
        }
        while (i <= middle){
            assist[k] = input[i];
            k++;
            i++;
        }
        while (j <= right){
            assist[k] = input[j];
            k++;
            j++;
        }
        //复制回去
        for (int c = 0;c < k;c++ ) {
            input[left + c] = assist[c];
        }
    }

    public static void mergeSort(int[] input){
        internalMergeSort(input,new int[input.length],0,input.length -1);
    }

    /**
     * 注意其思想
     * @param input
     */
    public static void quickSort(int[] input){
        internalQuickSort(input,0,input.length-1);
    }

    /**
     * 其中注意一趟的思想
     * @param input
     * @param left
     * @param right
     */
    private static void internalQuickSort(int[] input,int left,int right ){
        if (left < right) {
            int flag = input[left];
            int i = left;
            int j = right;
            while (i < j) {
                while (i < j && input[i] < flag) {
                    i++;
                }
                while (i < j && input[j] > flag) {
                    j--;
                }
                if (i < j) {
                    swap(i, j, input);
                }else {
                    // 注意 i = j 的情况
                    i++;
                }
            }
            internalQuickSort(input, left, i);
            internalQuickSort(input, i + 1, right);
        }
    }


    public static void main(String[] args){

        mergeSort(input);
        count(input);


    }


}
