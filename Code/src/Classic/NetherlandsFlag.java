package Classic;

public class NetherlandsFlag {
    /*
    * 荷兰国旗问题：给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，
    *             等于num的数放在数组的中间，大于num的数放在数组的右边。
    * 要求额外空间复杂度O(1)，时间复杂度O(N)
    *
    * 解决： 0-less区域都为 小于num的数   more - N-1区域都为大于num的数
    *      [小于区][等于区][待定区][大于区]
    *      cur = num ,cur跳下一个
    *      cur < num ,cur和小于区域下一个数进行交换，小于区域向后扩一位，cur跳下一个,即cur++,less++
    *      cur > num ,cur和大于区域前一个数进行交换，大于区域向前扩一位，cur不变(因为换来的是待定区域的数)，继续进行比较，即more--
    *      cur 跟 more 撞上时停止，数组排完
    * */
    private static int[] partition(int[] arr,int l,int r,int num){
        int cur = l;
        int less = l - 1;
        int more = r + 1;
        while(cur < more){
            if(arr[cur] < num){
                swap(arr,++less,cur++);
            }else if(arr[cur] > num){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        //返回的是等于区的范围
        return new int[]{less + 1 , more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
    }

    // for test
    private static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = generateArray();

        printArray(test);
        int[] res = partition(test, 0, test.length - 1, 1);
        printArray(test);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
