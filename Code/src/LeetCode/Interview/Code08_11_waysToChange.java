package LeetCode.Interview;

public class Code08_11_waysToChange {
    public static int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] coins = new int[]{1,5,10,25};
        /*
        * 先遍历硬币，保证在考虑一枚硬币的情况时，没有较大的硬币影响，
        * 这样，我们最终每种组合情况，都是以硬币的面额大小非递减组合。
        * 保证了同样的情况，调换顺序后重复计算的情况。
        * */
        for(int coin : coins) {
            for(int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
                System.out.println(i + " : " + dp[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(waysToChange(6));
    }
}
