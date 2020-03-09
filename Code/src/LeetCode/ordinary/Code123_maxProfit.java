package LeetCode.ordinary;

public class Code123_maxProfit {
    /*
    * 注意base case的设置
    * */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int[][][] dp = new  int[prices.length][3][2];
        dp[0][1][0]  = dp[0][0][0] = dp[0][2][0] = 0;
        dp[0][0][1]  = Integer.MIN_VALUE;
        dp[0][1][1] = dp[0][2][1] = - prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int k = 2; k >= 1; k--) {
                dp[i][k][0] = Math.max(dp[i-1][k][0],dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1],dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][2][0];
    }

    public static void main(String[] args) {
        int[] arr = {3,3,5,0,0,3,1,4};
        int[] arr1 = {7,6,4,3,1};
        System.out.println(maxProfit(arr));
    }
}
