package LeetCode.ordinary;

public class Code122_MaxProfit {
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] , dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1] , dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        int[] arr = {7,6,5,4,3};
        System.out.println(maxProfit(arr));
    }
}
