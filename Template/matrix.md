[TOC]
## 矩阵问题
###  盛最多水的容器
![](https://upload-images.jianshu.io/upload_images/10460153-609cff90200dab0f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-54f30d16aa1e983d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//其实质就是在移动的过程中不断消去不可能成为最大值的状态。
public static int maxArea(int[] height) {
    int start = 0;
    int end = height.length - 1;
    int Area = 0;
    while (start < end){
        Area = Math.max(Area,Math.min(height[start],height[end]) * (end - start));
        if(Math.min(height[start],height[end]) == height[start]){
            start++;
        }else {
            end--;
        }
    }
    return Area;
}
```
### 统计[优美子数组]数量(双指针窗口问题)
![](https://upload-images.jianshu.io/upload_images/10460153-21ce8a8bcec2b2d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int numberOfSubarrays(int[] nums, int k) {
    int left = 0, right = 0, oddCnt = 0, res = 0;
    while (right < nums.length) {
        // 右指针先走，每遇到一个奇数则 oddCnt++。
        if ((nums[right++] & 1) == 1) {
            oddCnt++;
        }
        //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
        if (oddCnt == k) {
            // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
            // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
            int tmp = right;
            while (right < nums.length && (nums[right] & 1) == 0) {
                right++;
            }
            int rightEvenCnt = right - tmp;
            // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
            int leftEvenCnt = 0;
            while ((nums[left] & 1) == 0) {
                leftEvenCnt++;
                left++;
            }
            // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
            // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
            // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
            // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
            // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
            res += (leftEvenCnt + 1) * (rightEvenCnt + 1);
            // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
            left++;
            oddCnt--;
        }
    }
    return res;
}
```

### 滑动窗口问题
#### 最小覆盖子串
![](https://upload-images.jianshu.io/upload_images/10460153-0957a64d49393055.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 思路：
* 1.使用左右双指针技巧，[left,right)左闭右开为一个窗口
* 2.先不断增加right扩大窗口，直到窗口中的字符串符合要求
* 3.停止增加right，不断增加left缩小窗口，直到窗口中的字符串不符合要求，
* 4.重复2，3，直到right到达字符串S的尽头
* */
public String minWindow(String s, String t) {
    HashMap<Character,Integer> needs = new HashMap<>();
    HashMap<Character,Integer> windows = new HashMap<>();

    int left , right , count = 0,minLen = Integer.MAX_VALUE; //记录窗口的起始和终止，count表示窗口内已经符合要求的字符数量
    int start = 0,end = 0;

    //初始化needs
    for (Character c : t.toCharArray()) {
        needs.put(c,needs.getOrDefault(c,0) + 1); //needs如果包含c，则取出值加一，不包含取0 + 1；
    }

    left = right = 0; //窗口开始为[0,0);
    while (right < s.length()){
        char temp = s.charAt(right); //temp为更新窗口时，最右侧字符

        if(needs.containsKey(temp)){
            windows.put(temp,windows.getOrDefault(temp,0) + 1);

            //Integer不能用==进行比较，要用compareTo
            if(windows.get(temp).compareTo(needs.get(temp)) == 0){
                //字符temp出现次数符合要求时，count++
                count++;
            }
        }
        right ++; //此时窗口右边界已经右移一位

        //符合要求的字符个数正好是t中所有字符，获得一个可行解
        while (count == needs.size()){
            if(right - left < minLen){
                start = left;
                end = right;
                minLen = end - start;
            }
            //左边界 进行缩小，即删除s[left]
            char c = s.charAt(left);

            //当前删除的字符包含于t，更新windows对应字符出现的次数，如果更新后的次数<t中的次数，符合要求的字符数减一
            if(needs.containsKey(c)){
                windows.put(c,windows.get(c) - 1);
                if(windows.get(c) < needs.get(c)){
                    count--;
                }
            }
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(start,end);
}
```
#### 字符串的排列
![](https://upload-images.jianshu.io/upload_images/10460153-7f1468261e9ef3b5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 与最小覆盖子串不同点：
* 1.移动left缩小窗口时机：窗口大小大于s1长度时，因为排列，所以长度肯定一样
* 2.当count == needs.length,并且窗口大小等于s1长度，返回true
* */
 //窗口大小大于s1长度时，应该缩小窗口
if((right - left) > s1.length()){
    char c = s2.charAt(left);

    if(needs.containsKey(c)){
        if(windows.get(c).equals(needs.get(c))){
            count--;
        }
        windows.put(c,windows.get(c) - 1);
    }
    left++;
}
if(((right - left) == s1.length()) && count == needs.size()){
    return true;
}
```
#### 无重复字符的最长子串
![](https://upload-images.jianshu.io/upload_images/10460153-a9723946e7459207.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int lengthOfLongestSubstring(String s) {
    HashMap<Character,Integer> window = new HashMap<>();
    int left = 0 , right = 0;
    int res = 0;
    while(right < s.length()){
        char c = s.charAt(right);
        window.put(c,window.getOrDefault(c,0) + 1);
        right++;
        while (window.get(c) > 1){
            char d = s.charAt(left);
            left++;
            //窗口内数据进行更新
            window.put(d,window.get(d) - 1);
        }
        res = Math.max(res,right - left);
    }
    return res;
}
```

### 旋转矩阵
![](https://upload-images.jianshu.io/upload_images/10460153-ea42a8bbd3e7c706.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//进行分圈处理，左上角和右下角两个点确定一个圈。
public void rotate(int[][] matrix) {
    int tR = 0;
    int tC = 0;
    int dR = matrix.length - 1;
    int dC = matrix[0].length - 1;
    while (tR < dR){
        rotateEdge(matrix,tR++,tC++,dR--,dC--);
    }
}

private void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
    // times表示总的组数
    int times = dR - tR;
    int tmp = 0;
    for (int i = 0; i < times; i++) {
        tmp = matrix[tR][tC + i];
        matrix[tR][tC + i] = matrix[dR - i][tC];
        matrix[dR - i][tC] = matrix[dR][dC - i];
        matrix[dR][dC - i] = matrix[tR + i][dC];
        matrix[tR + i][dC] = tmp;
    }
}
```
### 01矩阵
![](https://upload-images.jianshu.io/upload_images/10460153-585128bcfd31a8a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//思路：0位置为完成计算位置，先将所有0位置加入队列，依次感染四周

public int[][] updateMatrix(int[][] matrix) {
    if(matrix == null || matrix.length == 0){
        return matrix;
    }
    int n = matrix.length;
    int m = matrix[0].length;
    int[][] res = new int[n][m];
    //标记该位置 是否计算完成
    boolean[][] visited = new boolean[n][m];
    Queue<int[]> queue = new LinkedList<>();
    //将值为0的位置全部入队，用来感染四个方向
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if(matrix[i][j] == 0){
                queue.offer(new int[]{i,j});
                visited[i][j] = true;
            }
        }
    }
    int[][] dirs = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    while (!queue.isEmpty()){
        int[] top = queue.poll();
        int x = top[0];
        int y = top[1];
        //BFS搜索四个方向：每次将已完成计算的位置取出，来进行感染
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX < 0 || newX >= n || newY < 0 || newY >= m || visited[newX][newY]){
                continue;
            }
            // 感染后更新状态，并加入队列继续感染
            res[newX][newY] = res[x][y] + 1;
            visited[newX][newY] = true;
            queue.offer(new int[]{newX,newY});
        }
    }
    return res;
}
```
### 岛屿数量
![](https://upload-images.jianshu.io/upload_images/10460153-f42c71a477c04b24.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* bfs :遍历一个位置所能到达的所有位置 改变状态,保证每个位置只会被感染一次
* */
//i,j代表当前位置，M,N分别为矩阵的行数和列数
public void infect(char[][] m,int i,int j,int M,int N){
    //越界或者位置上不是1，退出感染过程
    if(i < 0 || i >= M || j < 0 || j >= N || m[i][j] != '1'){
        return;
    }
    m[i][j] = '2';
    infect(m,i - 1, j,M , N);
    infect(m,i + 1, j,M , N);
    infect(m,i , j - 1,M , N);
    infect(m,i , j + 1,M , N);
}
public int numIslands(char[][] grid) {
    if(grid == null || grid[0] == null){
        return 0;
    }
    int M = grid.length;
    int N = grid[0].length;
    int num = 0;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if(grid[i][j] == '1'){
                num++;
                infect(grid,i,j,M,N);
            }
        }
    }
    return num;
}
```

### 岛屿的最大面积
![](https://upload-images.jianshu.io/upload_images/10460153-27df97e2c2f07ceb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int nums_2 = 0;
int[][] grid = null;
public int maxAreaOfIsland(int[][] grid) {
    this.grid = grid;
    if(grid == null || grid.length == 0){
        return 0;
    }
    int max = 0 ;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if(grid[i][j] == 1){
                int a = nums_2;
                infect(i , j);
                int nums = nums_2 - a;
                max = Math.max(nums,max);
            }
        }
    }
    return max;
}
public void infect(int i ,int j){
    if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length){
        return;
    }
    if(grid[i][j] == 1){
        nums_2++;
        grid[i][j] = 2;
        infect(i - 1,j);
        infect(i + 1,j);
        infect(i ,j - 1);
        infect(i ,j + 1);
    }
    return;
}
```