package Sort;

import java.util.Arrays;

public class HeapSort {
    /*
    * 堆排序：
    * 先建立大根堆，每次将堆最后一个位置的数与头部进行交换，然后将最后位置数减掉，
    * 再将缩小范围的堆重新调整为大根堆，直到堆的大小减完，每次将最大值依次填入最后的位置
    *
    * 加入第n个结点只跟层数有关，因为前 n - 1 个结点已经排好，只需跟其父结点进行比较
    * 将第i个结点加入堆时，复杂度为O(log(i - 1));
    * 所以数组建大根堆复杂度为 log1+log2+...+log(n-1) = O(N)
    *
    *
    * 堆排序的复杂度为O(N*logN)，额外空间复杂度O(1)
    * */
    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    /*
    * 建立大根堆 : 任何一个子树的最大值都是该树的头部所形成的结构
    * 将数组想成完全二叉树的结构，
    * 对于i结点， 父结点在数组的位置为：(i - 1) / 2  ,左孩子结点位置为：2 * i + 1,右孩子结点位置为：2 * i + 2
    * 结点值若大于父结点值，则交换位置，
    *
    * 此方法只将数组index位置的数放置在相应的位置上
    * heapInsert为新结点加入堆里面往上调整的过程
    * */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /*
    * heapify 过程： 当大根堆里的值改变时，变得不再是大根堆，将其重新调整为大根堆
    * 某值变小时，值需要往下进行调整
    * 找左右孩子中值大的 进行交换
    *
    * 此方法表示0 ~ size-1范围已经形成大根堆，此时index位置的数改变，将其再次调整为大根堆
    * */

    private static void heapify(int[] arr, int index, int size) {
        int left = index  * 2 + 1;
        while(left < size){
            //largest表示左右孩子中值比较大的位置
            int largest = left + 1 < size && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index){
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
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
            heapSort(arr1);
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
        heapSort(arr);
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
