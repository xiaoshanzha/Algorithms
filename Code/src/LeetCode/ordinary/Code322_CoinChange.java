package LeetCode.ordinary;

import java.util.Arrays;

public class Code322_CoinChange {
    /*
    * dp[i] 存放兑换总金额i时，最少所需硬币数，先初始化为amount + 1，因为最小面值1，此值无效
    * */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if(coin <= i){
                    dp[i] = Math.min(dp[i],dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {

    }
}
