
## 动态规划

[TOC]
### 动态规划设计流程
* 明确dp数组所存数据的含义。
* 根据dp数组的定义，运用数学归纳法的思想，假设dp[0...i-1]都已知，想办法求dp[i]。
* 如果无法完成上一步，很可能dp数组定义不够恰当，需要重新定义其含义，或者dp数组存储的信息还不够，不足以推出下一步答案，将dp数组扩维。
* 最后需要想问题的base case，以此来初始化dp数组。


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
