package LeetCode.Interview;

public class Code40_getMinKByheap {
    /*
    * 返回数组中最小的k个数，用堆实现，复杂度:O(N*logk) ,BFPRT 复杂度：O(N)
    *
    * 堆：一直维护一个大小为k的大根堆，遍历到的数小于堆顶元素，则交换，并调整堆
    * */
    private int[] Kheap = null;
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0){
            return new int[0];
        }
        Kheap = new int[k];
        for (int i = 0; i < k; i++) {
            heapInsert(arr[i] , i);
        }

        for (int i = k; i < arr.length; i++) {
            if(Kheap[0] > arr[i]){
                Kheap[0] = arr[i];
                heapify(0,k);
            }
        }
        return Kheap;
    }

    public void heapInsert(int value , int index){
        Kheap[index] = value;
        while (index != 0 ){
            int parent = (index - 1) / 2;
            if(Kheap[parent] < Kheap[index]){
                swap(Kheap,parent,index);
                index = parent;
            }else {
                break;
            }
        }
    }

    //调整堆,将堆顶的小值调整到合适的位置
    public void heapify(int i , int heapSize){
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        //调整的前提：至少有左孩子
        while (left < heapSize){
            if(Kheap[left] > Kheap[largest]){
                largest = left;
            }
            if(right < heapSize && Kheap[right] > Kheap[largest]){
                largest = right;
            }
            // largest代表 自己、左值、右值中值最大的位置
            if(largest != i){
                swap(Kheap,i,largest);
            }else {
                break;
            }
            i = largest;
            left = 2 * i + 1;
            right = 2 * i + 2;
        }
    }

    public void swap (int[] arr, int a ,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        Code40_getMinKByheap c = new Code40_getMinKByheap();
        int[] arr1 = {3,22,1,4,5,442,1};
        int[] arr = c.getLeastNumbers(arr1,5);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
