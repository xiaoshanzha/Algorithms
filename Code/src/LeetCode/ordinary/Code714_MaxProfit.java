package LeetCode.ordinary;

public class Code714_MaxProfit {
    /*
    *每次交易要⽀付⼿续费，只要把⼿续费从利润中减去即可
    * 相当于买⼊股票的价格升⾼了。
    * */
    public static int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int dp[][] = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        dp[1][1] = Math.max(dp[0][1] , dp[0][0] - prices[1] - fee);
        dp[1][0] = Math.max(dp[0][0] , dp[0][1] + prices[1]);
        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0] - prices[i] - fee);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        int[] arr = {1};
        System.out.println(maxProfit(arr,0));
    }
}
