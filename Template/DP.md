
# 动态规划

[TOC]
## 动态规划设计流程
* 明确dp数组所存数据的含义。
* 根据dp数组的定义，运用数学归纳法的思想，假设dp[0...i-1]都已知，想办法求dp[i]。
* 如果无法完成上一步，很可能dp数组定义不够恰当，需要重新定义其含义，或者dp数组存储的信息还不够，不足以推出下一步答案，将dp数组扩维。
* 最后需要想问题的base case，以此来初始化dp数组。

## 子序列问题模板
* 一维的dp数组：
    eg：最长递增子序列
    dp[i]：在子数组array[0...i]中，子序列最长递增子序列长度
* 二维的dp数组(涉及一个字符串数组)：
    eg: 最长回文子序列
        dp[i][j]：在子数组arr[i...j]中，最长回文子序列长度
* 二维的dp数组(涉及两个字符串数组)：
    eg： 
    1. 最长公共子序列
        dp[i][j]：在子数组arr1[0...i]和子数组arr2[0...j]中，最长公共子序列长度
    2. 编辑距离
        dp[i][j]：word1的前i个字母转化成word2的前j个字母所需的最少操作数。
### 最长递增子序列（LFS）
![](https://upload-images.jianshu.io/upload_images/10460153-18f95e821508cd2e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 注意：子串一定是连续的，子序列不一定是连续的。
```java
public int lengthOfLIS(int[] nums) {
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
```


### 最长回文子序列
![](https://upload-images.jianshu.io/upload_images/10460153-34c5f50c6008a6ea.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

解：
dp 数组的定义：在子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j]。
![](https://upload-images.jianshu.io/upload_images/10460153-91c568901a20559f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-3410f3ba3a70676a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
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
```

### 最长公共子序列
![](https://upload-images.jianshu.io/upload_images/10460153-62744425321fb9ed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

DP Table:

![](https://upload-images.jianshu.io/upload_images/10460153-d5f8185f35ddc594.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//获取dp数组
public static int[][] GetDP(String text1, String text2) {
    if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
        return null;
    }
    char[] chars1 = text1.toCharArray();
    char[] chars2 = text2.toCharArray();
    //dp[i][j] 含义： chars1[1...i] 和 chars2[2...j]的最长公共子序列长度
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
```



### 编辑距离
![](https://upload-images.jianshu.io/upload_images/10460153-e1672d8b512e4423.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
解：
![](https://upload-images.jianshu.io/upload_images/10460153-a05aa0caffa98ebb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-ef25d4c79e2b701f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-868b7d84d4619c81.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m ; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n ; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1,dp[i - 1][j] + 1),dp[i][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }
```
## 打家劫舍问题
### 打家劫舍I(房屋一排)
![](https://upload-images.jianshu.io/upload_images/10460153-2fa859d98df20dd4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//方法一：类似于股票问题，每个房间有两个状态：抢和不抢。定义dp[][]
//方法二：
private int[] memo;
public int rob(int[] nums) {
    //初始化备忘录
    memo = new int[nums.length];
    Arrays.fill(memo, -1);
    //强盗从第0号房间开始抢劫
    return dp(nums,0);
}

// 返回dp[start...]能抢到的最大值
private int dp(int[] nums, int start) {
    if(start >= nums.length){
        return 0;
    }
    if(memo[start] != -1)
        return memo[start];
    //该位置有两种状态
    //抢：则最大利益为nums[start]+dp[start + 2...]
    //不抢：则最大利益为dp[start + 1...]
    int res = Math.max(dp(nums,start + 1),nums[start] + dp(nums,start + 2));
    memo[start] = res;
    return res;
}
```
### 打家劫舍Ⅱ(房屋围一圈)
![](https://upload-images.jianshu.io/upload_images/10460153-48de2559411e9942.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int rob(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    if(n == 1){
        return nums[0];
    }
    /*
    * 首尾房间不能同时被抢，共三种情况
    * 1.首尾都不抢
    * 2.抢首不抢尾
    * 3.抢尾不抢首
    * 情况2、3对于房子的选择余地更大，房子里钱数非负，所以选择余地大的，最优决策结果不会小
    * */
    return Math.max(robRange(nums,0,n - 2),robRange(nums,1,n - 1));
}

// 仅计算闭区间[start,end]的最优结果
private int robRange(int[] nums, int start, int end) {
    int dp_i_1 = 0; //表示i+1位置最大利益
    int dp_i_2 = 0; //表示i+2位置
    int dp_i = 0;
    for (int i = end; i >= start ; i--) {
        dp_i = Math.max(dp_i_1,nums[i] + dp_i_2);
        dp_i_2 = dp_i_1;
        dp_i_1 = dp_i;
    }
    return dp_i;
}
```
### 打家劫舍Ⅲ(二叉树排列)
![](https://upload-images.jianshu.io/upload_images/10460153-2eaa04686a741d06.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//树形DP：计算每个节点打劫和不打劫的最大值，最后返回根节点最大值
public int rob(TreeNode root){
    int[] res = dp(root);
    return Math.max(res[0],res[1]);
}
public int[] dp(TreeNode node){
    if(node == null){
        return new int[]{0,0};
    }
    int[] left = dp(node.left);
    int[] right = dp(node.right);

    //抢该节点，不抢子节点
    int rob = node.val + left[0] + right[0];
    //抢该节点，下家可抢可不抢
    int not_rob = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
    return new int[]{not_rob,rob};
}
```

## 从后往前
### 最低票价

![](https://upload-images.jianshu.io/upload_images/10460153-a4ffa387e65f7415.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/10460153-5119ac25c024ea49.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
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
```