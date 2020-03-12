package LeetCode.Interview;

public class Code42_maxSubArray {
    public static int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max,sum);
            if(sum <= 0){
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {};
        System.out.println(maxSubArray(nums));
    }
}
