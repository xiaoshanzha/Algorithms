package LeetCode.Interview;

public class Code08_01_waysTostep {
    public static int waysToStep(int n) {
        // 要获取当前n数量 只需分别知道前三次的，
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        if(n < 4){
            return dp[n - 1];
        }
        int temp = 0;
        for (int j = 4; j <= n ; j++) {
            temp = ((dp[0] + dp[1]) % 1000000007  + dp[2]) % 1000000007;
            dp[(j - 1) % 3] = temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(waysToStep(61));
    }
}
