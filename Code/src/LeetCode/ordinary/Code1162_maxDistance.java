package LeetCode.ordinary;

import java.util.ArrayDeque;
import java.util.Queue;

public class Code1162_maxDistance {
    public int maxDistance(int[][] grid) {
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length;
        int n = grid[0].length;

        //先把所有的陆地都入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    queue.offer(new int[]{i,j});
                }
            }
        }

        //然后从各个陆地开始，来感染海洋，最后遍历到的海洋就是离陆地最远的海洋
        boolean hasOcean = false;
        int[] point = null;
        while (!queue.isEmpty()){
            //取出一块陆地
            point = queue.poll();
            int x = point[0],y = point[1];
            //将其四周的海洋加入
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                // 更新该位置为第几批到达的，
                grid[newX][newY] = grid[x][y] + 1;
                hasOcean = true;
                queue.offer(new int[]{newX,newY});
            }
        }
        // 没有陆地或者没有海洋，返回-1。
        if (point == null || !hasOcean) {
            return -1;
        }

        return grid[point[0]][point[1]] - 1;
    }
}
