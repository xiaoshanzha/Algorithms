package LeetCode.ordinary;

import java.util.Arrays;

public class Code198_rob {
   //方法一：
    /* public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0 ;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0],dp[i - 1][0] + nums[i]);
        }
        return Math.max(dp[nums.length - 1][0],dp[nums.length - 1][1]);
    }
*/
    //方法二：
    private int[] memo;
    public int rob(int[] nums) {
        //初始化备忘录
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        //强盗从第0号房间开始抢劫
        return dp(nums,0);
    }

    // 返回dp[start...]能抢到的最大值
    private int dp(int[] nums, int start) {
        if(start >= nums.length){
            return 0;
        }
        if(memo[start] != -1)
            return memo[start];
        int res = Math.max(dp(nums,start + 1),nums[start] + dp(nums,start + 2));
        memo[start] = res;
        return res;
    }
}
