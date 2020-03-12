package LeetCode.Interview;

public class Code10_2 {
    public static int numWays(int n) {
        if(n <= 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        int cur = 2;
        while (cur < n){
            dp[cur] = ( dp[cur - 1] % 1000000007 + dp[cur - 2] % 1000000007) % 1000000007;
            cur++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(numWays(99));
    }
}
