package LeetCode.ordinary;

public class Code221_maximalSquare {
    public static int maximalSquare(char[][] matrix) {
        /*
        * 动态规划：
        * dp[i][j] 表示以matrix[i][j]为右下角的正方形的最大边长
        * dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
        *
        * 解释：若某格子值为1，则以此格为右下角的正方形的最大边长为
        *       上面格子，左边格子，左上格子作为右下角形成正方形最小的那个加上此格子
        * */

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0 ){
            return 0;
        }
        /*
        * 也可int[][] dp = new int[height + 1][width + 1];
        * 多一行一列，相当于已经预处理新增一行一列，就不用像下边那样处理边界
        * */
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i == 0 || j == 0){
                    if(matrix[i][j] == '1'){
                        dp[i][j] = 1;
                        max = Math.max(dp[i][j],max);
                    }else {
                        dp[i][j] = 0;
                    }
                }else {
                    if(matrix[i][j] == '1'){
                        dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]);
                        dp[i][j] = Math.min(dp[i][j],dp[i][j-1]) + 1;
                        max = Math.max(dp[i][j],max);
                    }else {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        return max * max;
    }
}
