package LeetCode.ordinary;

import java.util.LinkedList;

public class Code239_GetMaxWindow {
    public static  int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k < 1|| k > nums.length){
            return new int[0];
        }
        LinkedList<Integer> window = new LinkedList<>();
        int index = 0;
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //给双端队列添加元素时，先将无效的值移出去
            while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]){
                window.pollLast();
            }
            window.addLast(i);

            //检查队列首元素所在窗口是否过期
            if(window.peekFirst() == i - k){
                window.pollFirst();
            }
            //遍历位置大于一个窗口后，每次都给res添加元素
            if(i >= k - 1){
                res[index++] = nums[window.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int TestTime = 50000;
        Boolean flag = true;
        for (int i = 0; i < TestTime; i++) {
            int[] arr1 = generateArray(20,10);
            int[] final_arr1 = maxSlidingWindow(arr1,arr1.length - 5);
            int[] arr2 = copyArray(arr1);
            int[] final_arr2 = comparator(arr2,arr2.length - 5);
            if(!isEqual(final_arr1,final_arr2)){
                flag = false;
                System.out.println("fuck!!");
                printArray(arr1);
                printArray(final_arr1);
                printArray(final_arr2);
                break;
            }
        }
        if (flag){
            System.out.println("Nice!");
        }
    }
    private static void printArray(int[] arr) {
        for(int i = 0; i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
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

    private static int[] comparator(int[] arr2, int k) {
        int[] res = new int[arr2.length - k + 1];
        int index = 0;
        for (int i = 0; i <= arr2.length - k; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i ; j < i + k;j++){
                max = arr2[j] > max ? arr2[j] : max;
            }
            res[index++] = max;
        }
        return res;
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

    private static int[] generateArray(int maxsize, int maxvalue) {
        int arr[] = new int[(int)((maxsize+1)*Math.random()) + 6];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxvalue+1)*Math.random());
        }
        return arr;
    }
}
