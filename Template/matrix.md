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