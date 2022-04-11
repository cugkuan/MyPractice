package sort;

public class QuickSort  extends Sort{




    @Override
    public void sort(int[] input) {
        internalQuickSort(input,0,input.length -1);
    }
    /**
     * 其中注意一趟的思想
     * @param input
     * @param left
     * @param right
     */
    private  void internalQuickSort(int[] input,int left,int right ){
        if (left < right) {
            int flag = input[left];
            int i = left;
            int j = right;
            while (i < j) {
                while (i < j && input[j] >= flag) {
                    j--;
                }
                while (i < j && input[i] < flag) {
                    i++;
                }
                // 特别注意
                if (input[i] == input[j] && i < j) {
                    i++;
                } else {
                    swap(input,i, j);
                }
            }
            count(input);
            System.out.println("\n");
            System.out.println("i:"+i +"  "+"j:"+j);
            internalQuickSort(input, left, i);
            internalQuickSort(input, i+1, right);
        }
    }


    public static void main(String[] args){


        new QuickSort().sort(input);
//        mergeSort(input);
        count(input);


    }
}
