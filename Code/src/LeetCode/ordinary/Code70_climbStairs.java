package LeetCode.ordinary;

public class Code70_climbStairs {

    /*
    * 动态规划
    * */
    public static int climbStairs(int n) {
        if( n == 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2 ];
        }
        return dp[n];
    }

    /*
    * 暴力
    * */
    public static int climbStairs_1(int n) {
        return climb(0,n);
    }
    // i 表示当前阶数，n表示目标阶数
    public static int climb(int i , int n) {
        if(i == n){
            return 1;
        }
        if(i > n){
            return 0;
        }
        return climb(i + 1 , n) + climb(i + 2 , n);
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
    }
}
