package LeetCode.ordinary;

public class Code983_mincostTickets {

    /*
    * 从后往前推：
    *
    * 第 i 天需要出门，需要如何购票，有三种决策：(如何选择受后面天数决定，从后往前推)
    * 1.买一天期的，后面不包，
    * 2.买七天期的，包到第i + 7 - 1天，第i + 7天后不包
    * 3.买三十天期的，包到第i + 30 - 1天，第i + 30天后不包
    * dp[i] = min(决策1, 决策2, 决策3); --->
    *       = min(c[0] + dp[i + 1], c[1] + dp[i + 7], c[2] + dp[i + 30]);
    *
    * dp[i] 为第 i 天开始，所需最小费用累计
    * */
    public int mincostTickets(int[] days, int[] costs) {
        int minday = days[0];
        int maxday = days[days.length - 1];
        int[] dp = new int[maxday + 31]; //向后多扩几天，防止dp[i + 30]范围不会超出

        //只需计算maxday -> minday 区间，此区间外不需要出门，
        for (int d = maxday , i = days.length - 1; d >= minday; d--) {
            // d天需要出门
            if(d == days[i]){
                dp[d] = Math.min(dp[d + 1] + costs[0],dp[d + 7] + costs[1]);
                dp[d] = Math.min(dp[d] ,dp[d + 30] + costs[2]);
                i--;
            }else { //不需要出门
                dp[d] = dp[d + 1];
            }
        }
        return dp[minday];
    }
}
