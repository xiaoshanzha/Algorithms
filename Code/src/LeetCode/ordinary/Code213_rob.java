package LeetCode.ordinary;

public class Code213_rob {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }

        /*
        * 首尾房间不能同时被抢，共三种情况
        * 1.首尾都不抢
        * 2.抢首不抢尾
        * 3.抢尾不抢首
        * 情况2、3对于房子的选择余地更大，房子里钱数非负，所以选择余地大的，最优决策结果不会小
        * */
        return Math.max(robRange(nums,0,n - 2),robRange(nums,1,n - 1));
    }

    // 仅计算闭区间[start,end]的最优结果
    private int robRange(int[] nums, int start, int end) {
        int dp_i_1 = 0; //表示i+1位置最大利益
        int dp_i_2 = 0; //表示i+2位置
        int dp_i = 0;
        for (int i = end; i >= start ; i--) {
            dp_i = Math.max(dp_i_1,nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
