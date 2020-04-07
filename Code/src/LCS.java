public class LCS {
    /*
    * 最长公共子序列
    * */
    public static int[][] GetDP(String text1, String text2) {
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
            return null;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        //dp[i][j] 含义： chars1[1...i] 和 chars2[1...j]的最长公共子序列长度
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];

        //base case：将索引为0的行和列表示空串，所以dp[0][i] dp[i][0]都为0，表示空串和其他字符串的最长公共长度
        for (int i = 0; i < chars2.length; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < chars1.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < chars1.length + 1; i++) {
            for (int j = 1; j < chars2.length + 1; j++){
                /*
                * dp[i][j] 先从dp[i - 1][j] 和 dp[i][j - 1]选择大值
                * dp[i - 1][j] 表示 chars1[1...i-1]和chars2[1...j]的LFS
                * dp[i][j - 1] 表示 chars2[1...i]和chars2[1...j-1]的LFS
                * */
                dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
                if(chars1[i - 1] == chars2[j - 1]){  //注意此处为i-1和j-1,
                    dp[i][j] = Math.max(dp[i][j],dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    //根据求的dp数组还原出最长公共子序列
    public static String LCSforStr(String text1,String text2){
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
            return "";
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int[][] dp = GetDP(text1,text2);

        int m = chars1.length;
        int n = chars2.length;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;

        /*
        * 从矩阵的右下角开始。有三种移动方式：向上，向左，向左上
        * dp[m][n] == dp[m - 1][n]，说明在计算dp[m][n]时，dp[m - 1][n - 1] + 1不是必须选择的策略。向上移动
        * dp[m][n] == dp[m][n - 1]，向左移动
        * dp[m][n] == dp[m - 1][n - 1] + 1,说明一定选择了决策dp[m - 1][n - 1] + 1,向左上方移动。
        * */
        while (index >= 0){
            if(n > 0 && dp[m][n] == dp[m][n - 1]){
                n--;
            }else if(m > 0 && dp[m][n] == dp[m - 1][n]){
                m--;
            }else {
                res[index--] = chars1[m - 1];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }
    public static void main(String[] args) {
        int[][] dp = GetDP("ace","babcde");
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(LCSforStr("ace","babcde"));
    }
}
