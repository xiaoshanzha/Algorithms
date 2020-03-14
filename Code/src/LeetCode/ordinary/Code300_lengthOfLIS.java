package LeetCode.ordinary;

import java.util.Arrays;

public class Code300_lengthOfLIS {
    //动态规划解决： 先定义好dp[i]所表示的内容
    //假如dp[0...i-1]都已求出，求dp[i],遍历前面的，看num[i] 可以作为nums[0...i-1]中谁的末尾,寻找最大的
    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        //dp[i] 表示以i位置作为上升子序列的最后值时，该子序列的最长的长度
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public static int[] generateArray(int maxValue,int maxnums){
        int nums = (int)(Math.random() * maxnums);
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = (int)(Math.random() * maxValue);
        }
        return arr;
    }
    public static void printArray(int[] arr){
        System.out.println("原数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(10,10);
            printArray(arr);
            System.out.println("最长子序列长度：" + lengthOfLIS(arr));
        }
    }
}
