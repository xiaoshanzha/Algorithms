package LeetCode.ordinary;

public class Code121_MaxProfit {

    /*
    * 买卖股票dp模板
    * dp[i][k][0..1] : 第i天 最多允许k次交易 持有(1)和卖出(0)时的最大收益
    *
    * 状态转移方程：
    * base case:
    * dp[-1][k][0]	=	dp[i][0][0]	=	0
    * (i = -1 意味着还没开始，利润为0，k = 0意味不允许交易，利润为0)
    * dp[-1][k][1]	=	dp[i][0][1]	=	-infinity(负无穷 表示不可能)
    * (i = -1 意味着还没开始，不可能持有股票， k = 0不允许交易，不可能持有股票)
    *
    * dp[i][k][0]	=	max(dp[i-1][k][0],	dp[i-1][k][1]	+	prices[i])
    * dp[i][k][1]	=	max(dp[i-1][k][1],	dp[i-1][k-1][0]	-	prices[i])
    *
    * 此题k = 1,对状态转移没有影响
     * */
    public static int maxProfit_By_dp(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] , dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1] ,  - prices[i]);
            //注意该处不能写dp[i-1][k-1][0] - prices[i]，因为只允许依次交易，要买时 前面肯定没有买过
        }
        return dp[prices.length - 1][0];
    }
    /*
    * 只需记录当前 i 值前面出现过的最小值，然后相减得该天卖出的最大效益
    * */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int[] left_min = new int[prices.length];
        left_min[0] = Integer.MAX_VALUE;
        left_min[1] = prices[0];
        int max = Integer.MIN_VALUE;
        max = Math.max(max,prices[1] - left_min[1]);
        for (int i = 2; i < prices.length; i++) {
            left_min[i] = Math.min(left_min[i - 1],prices[i - 1]);
            max = Math.max(max,prices[i] - left_min[i]);
        }
        return max > 0 ? max : 0;
    }

    public static void main(String[] args) {
        int[] arr = {};
        System.out.println(maxProfit_By_dp(arr));
    }
}
