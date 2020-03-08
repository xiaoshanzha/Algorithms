package LeetCode.ordinary;

public class Code51_maxSubArray {
    public static int maxSubArray(int[] nums) {
        /*
        * max记录当前遍历过的最大和
        * cur_sum 表示当前的子数组和，如果小于0，前面这段不可能成为最大值的左端，令cur_sum = 0;重新开始
        * */
        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur_sum = 0;
        for (int j = 0; j < nums.length; j++) {
            cur_sum += nums[j];
            max = Math.max(cur_sum,max);
            if(cur_sum <= 0){
                cur_sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }
}
