package Sort;

import java.util.Arrays;

public class MergeSort {
    /*
    * 归并排序： 先左排，再右排，再整体合并； 左排和右排均为递归
    * eg: 5 3 6 | 2 8 1
    * 先左右分别排好： 3 5 6 | 1 2 8
    * 再利用辅助数组，类似外排进行合并
    * 快的实质： 小组内元素不用重复进行比较，小组内比较次数不会浪费掉
    *           小范围 -> 大范围时 只用进行跨区间比较
    *
    * master公式 ：T(N) = a*T(N/b) + O(N^d)
    * T(N):样本量为N情况下时间复杂度
    * a:子过程发生次数
    * b:子过程样本量
    * O(N^d):除去调用子过程外所需代价
    *
    * log(b,a) > d  :   复杂度为O(N^log(b,a))
    * log(b,a) = d  :   复杂度为O(N^d * logN)
    * log(b,a) < d  :   复杂度为O(N^d)
    *
    * 时间复杂度：O(N*logN)  利用master公式可得 T(N) = 2*T(N/2) + O(N)
    * 额外空间复杂度： O(N)
    */
    private static void mergeSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        mergeSort(arr,0,arr.length-1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if(l == r){
            return;
        }
        //不写 mid = (l+r)/2 是为了防止溢出
        int mid = l+((r-l)/2); // mid = l+(r-l)>>1; 位运算比算数运算快
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r-l+1];
        int i = 0;
        int p1 = l;
        int p2 = mid+1;
        while(p1 <= mid && p2 <= r){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){
            help[i++] = arr[p1++];
        }
        while(p2 <= r){
            help[i++] = arr[p2++];
        }
        for(i = 0; i < help.length ; i++){
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
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
        mergeSort(arr);
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
