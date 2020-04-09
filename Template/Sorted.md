[TOC]
## 排序
### 选择排序(selectionSort)
```java
/*
* 每次遍历数组未排序部分，确定其最小数的位置，
* 然后跟未排序部分的首元素进行交换。
* */
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
```
### 冒泡排序(BubbleSort)
```java
/*
* 依次将数组未排序部分的最大值置于最后
* 每次遍历时比较相邻的两数，若前数>后数，则进行交换
* */
private static void bubbleSort(int[] arr) {
    if(arr == null || arr.length<2){
        return;
    }
    for (int i = arr.length -1; i > 0 ; i--) {
        for (int j = 0; j < i; j++) {
            if(arr[j]>arr[j+1]){
                swap(arr,j,j+1);
            }
        }
    }
}
private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```
### 桶排序(BucketSort)
```java
/*
* 桶排序： 不基于比较的排序，
* 先遍历一遍数组找到最大值，然后准备max+1个桶，再遍历确定每个位置的词频，最后重塑该数组
*/
public static void bucketSort(int[] arr) {
    if(arr == null || arr.length < 2){
        return;
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
        max = Math.max(max,arr[i]);
    }
    int[] bucket = new int[max + 1];
    for (int i = 0; i < arr.length; i++) {
        bucket[arr[i]]++;
    }
    int i = 0;
    for (int j = 0; j < bucket.length; j++) {
        while (bucket[j]-- > 0){
            arr[i++] = j;
        }
    }
}
```
### 堆排序(HeapSort)
```java
/*
* 先建立大根堆(保证头结点是该树最大值)，对应heapInsert过程
* 然后每次将头部的数放入数组最后的位置，堆最后位置的数置于头部，
* 缩小堆范围并重新调整为大根堆，对应heapify过程
*/
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
* 将大数向上调整到合适的位置
*/
private static void heapInsert(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]){
        swap(arr,index,(index - 1) / 2);
        index = (index - 1) / 2;
    }
}
/*
* 将头节点的值向下调整到合适的位置
*/
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
```
### 插入排序(InsertionSort)
```java
/*
* 插入排序：0 - i-1 位置已经有序，选择i位置数插入相应位置
* eg: 3 1 0 2 6
* 首先 0-0位置已经排序，选择1位置数进行排序: 1 3| 0 2 6
*      0-1 位置已经排序，选择2位置数进行排序 ： 1 0 3 2 6   ->   0 1 3| 2 6
*      0-2 位置已经排序，选择3位置数进行排序：0 1 2 3| 6
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
```
### 归并排序
```java
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
```
### 快速排序
```java
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
```