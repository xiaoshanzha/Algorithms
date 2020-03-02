package Classic;

import java.util.Arrays;

public class SmallSum {
    /*
    * 小和问题：在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
    *
    * 是归并排序的应用
    *
    * 思路：利用归并排序，采用分治的思想
    * 小和 = 左侧小和 + 右侧小和 + merge 小和
    * eg : [3 4 1 2 5]  -> [ [3 4 1] | [2 5] ]
    *                   -> [ [ [3 4] | [1] ]  || [ [2] | [5] ] ]
    *                   -> [[[[3] | [4]] || [1] ] ||| [[2] | [5]]]
    *    先分治成以上结构  3 和 4 直接merge小和为3  合并为 3 4；
    *    然后 3 4 为一组跟1 合并，没有炸出小和，合并为1 3 4
    *    2 5 进行合并，炸出小和为2，合并为 2 5；
    *    1 3 4 跟 2 5 进行合并，炸出小和 1*2 + 3*1 + 4*1 = 9
    *    SmallSum = 3+2+9 = 14
    * */
    private static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return mergeSort(arr,0,arr.length-1);
    }

    private static int mergeSort(int[] arr, int l, int r) {
        if(l == r){
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr,l,mid) + mergeSort(arr,mid+1,r) + merge(arr,l,mid,r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0 ;
        int p1 = l;
        int p2 = mid +1;
        int res = 0;
        while (p1 <= mid && p2 <= r){
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            if(smallSum(arr1) != comparator(arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed?"Nice!":"Fuck!");
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

    private static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0;j < i;j++){
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
