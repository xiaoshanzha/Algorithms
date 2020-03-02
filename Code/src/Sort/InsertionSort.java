package Sort;

import java.util.Arrays;

public class InsertionSort {
    /*
    * 插入排序：0 - i-1 位置已经有序，选择i位置数插入相应位置
    * eg: 3 1 0 2 6
    * 首先 0-0位置已经排序，选择1位置数进行排序: 1 3| 0 2 6
    * 0-1 位置已经排序，选择2位置数进行排序 ： 1 0 3 2 6   ->   0 1 3| 2 6
    * 0-2 位置已经排序，选择3位置数进行排序：0 1 2 3| 6
    *
    * 复杂度与数据状态有关，O(N) ~ O(N²)
    * */
    public static void insertionSort(int[] arr){
        if(arr == null||arr.length<2){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for(int j = i-1;j >= 0 && arr[j] > arr[j + 1];j--){
                swap(arr,j,j+1);
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            insertionSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed?"Nice!":"Fuck!");

        int[] arr = generateRandomArray(maxSize,maxValue);
        printArray(arr);
        insertionSort(arr);
        printArray(arr);

    }

    private static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /*
     * 判断两数组是否相等；
     * 两数组均不为空且长度相等再进行比较
     * */
    private static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] copyArray(int[] arr) {
        if(arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //生成长度随机的整型数组
    private static int[] generateRandomArray(int maxSize, int maxValue) {
        /*
         * 数组长度随机，Math.random() ->[0,1)
         * (int)((size+1)*Math.random()) -> [0,size] 且等概率
         * */
        int[] arr = new int[(int)((maxSize+1)*Math.random())];

        for (int i = 0; i < arr.length; i++) {
            //产生等概率随机数[-Value,Value]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
