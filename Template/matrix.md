[TOC]
## 矩阵问题
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