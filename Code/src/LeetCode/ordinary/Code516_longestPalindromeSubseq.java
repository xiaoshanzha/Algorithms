package LeetCode.ordinary;

import java.util.Arrays;

public class Code516_longestPalindromeSubseq {
    //最长回文子序列
    public int longestPalindromeSubseq(String s) {
        //dp定义：在子串 s[i..j]中，最长回文子序列的长度为 dp[i][j]
        int[][] dp = new int[s.length()][s.length()];
        //dp数组全部初始化为0
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i],0);
        }
        //base case:如果只有一个字符，最长回文子序列即自己本身，长度为1；
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        //状态转移：须知左、下、左下三位置
        for (int i = s.length() - 1; i >= 0 ; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i + 1][j],dp[i][j - 1]);
                }
            }
        }
        //返回整个s的最长回文子串长度
        return dp[0][s.length() - 1];
    }
}
