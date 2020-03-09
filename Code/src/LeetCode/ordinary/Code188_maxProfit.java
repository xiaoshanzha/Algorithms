package LeetCode.ordinary;

public class Code188_maxProfit {
    //无限制求解
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

    public static int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int dp[][][] ;
        /*
        * k无限大时,如果直接new数组，会内存溢出，
        * 如果将k变成最大的有效值prices.length/2再new时，又会出现超时的错误
        * */
        if(k > prices.length / 2 ){
            return maxProfit(prices);
        }
        dp = new int[prices.length][k + 1][2];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i][0] = 0;
            if(i == 0){
                dp[0][i][1] = Integer.MIN_VALUE;
            }else {
                dp[0][i][1] = dp[0][i][1] = -prices[0];
            }
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0],dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1],dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k][0];
    }

    public static void main(String[] args) {
        int[] arr = {3,2,6,5,0,3};
        int[] arr1 = {1};
        System.out.println(maxProfit(1000000000,arr));
    }
}
