package LeetCode.Interview;

public class Code21_exchange {
    public static int[] exchange(int[] nums) {
        if(nums == null || nums.length == 0){
            return nums;
        }
        //left索引前面数组 为奇数，right索引后面数组为偶数
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            if((nums[left] & 1) == 0){ //& 1 ,0为偶，1为奇
                int t = nums[right];
                nums[right] = nums[left];
                nums[left] = t;
                right-- ;
            }else {
                left++ ;
            }
        }
        return nums;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String[] args) {
        int[] nums = {1};
        int[] res = exchange(nums);
        printArray(res);
    }
}
