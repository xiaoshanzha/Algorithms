package Sort;

import java.util.Arrays;

public class SelectionSort {
    //选择排序 ，每次遍历确定未排序最小数的位置， 分别和 0，1，2...位置进行交换。
    private static void selectionSort(int[] arr) {
        if(arr == null||arr.length<2){
            return;
        }
        for(int i = 0;i < arr.length;i++){
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j:minIndex;
            }
            swap(arr,i,minIndex);
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
            selectionSort(arr1);
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
        selectionSort(arr);
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
