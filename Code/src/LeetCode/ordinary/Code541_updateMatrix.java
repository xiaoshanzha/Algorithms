package LeetCode.ordinary;

import java.util.LinkedList;
import java.util.Queue;

public class Code541_updateMatrix {
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
                // 感染后更新距离和完成状态，并加入队列继续感染
                res[newX][newY] = res[x][y] + 1;
                visited[newX][newY] = true;
                queue.offer(new int[]{newX,newY});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Code541_updateMatrix c = new Code541_updateMatrix();
        int[][] arr = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] res = c.updateMatrix(arr);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
