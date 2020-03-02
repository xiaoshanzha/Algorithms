package Sort;

import java.util.Arrays;

public class QuickSort {
    /*
    * 经典快排：一次划分只搞定一个数，而不是同一个数，划分成[小于等于x区域][x][大于x区域]，
    *          小于等于区域里等于x的数仍需进行递归
    *
    * 快速排序的改进：
    * 荷兰国旗问题的扩展，数组进行partition过程，搞定数组中同一个数x的位置
    * 划分成[小于x区域][等于x区域][大于x区域]
    * 小于和大于区域里面再次选择一个数进行partition过程, 进行递归
    *
    * 以下解法每次选择数组中最后一个数进行划分，
    * 复杂度跟数据状况有关  O(n²)~O(N*logN)
    * 最差情况： 6 5 4 3 2 1
    * 最好情况： 确定的x位置每次都是数组的正中间  T(N) = 2T(N/2) + O(N)
    * */
    private static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if(l < r){
            //随机快排，每次选择数组中任一位置的数置于末尾，作为比较的数
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr,l,r);
            quickSort(arr,l,p[0] - 1);
            quickSort(arr,p[1] + 1,r);
        }

    }

    private static int[] partition(int[] arr, int l, int r) {
        int cur = l;
        int less = l - 1;
        int more = r + 1;
        int num = arr[r];
        while(cur < more){
            if(arr[cur] < num){
                swap(arr,++less,cur++);
            }else if(arr[cur] > num){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        return new int[] {less + 1,more - 1} ;

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
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
        quickSort(arr);
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
