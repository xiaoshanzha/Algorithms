package LeetCode.ordinary;

import java.util.ArrayDeque;
import java.util.Queue;

public class Code994_orangesRotting {
    public static class Point{
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public  static int orangesRotting(int[][] grid) {

        //新建数组，给原数组的上下左右边界各增加一列0，方便处理边界
        int[][] new_array = new int[grid.length + 2][grid[0].length + 2];
        for (int i = 0; i < new_array.length; i++) {
            for (int j = 0; j < new_array[0].length; j++) {
                if(i == 0 || i == new_array.length - 1 || j == 0 || j == new_array[0].length - 1){
                    new_array[i][j] = 0;
                }else {
                    new_array[i][j] = grid[i - 1][j - 1];
                }
            }
        }
        Queue<Point> rotten = new ArrayDeque<>();

        for(int i = 0;i < new_array.length;i ++){
            for (int j = 0; j < new_array[0].length; j++) {
                if(new_array[i][j] == 2){
                    rotten.add(new Point(i,j));
                }
            }
        }
        int minites = 0;
        // 每次遍历取出腐烂橘子，将其影响到的好橘子 加入队列并改变状态
        while (!rotten.isEmpty()){
            int rotten_num = rotten.size();
            for (int i = 0; i < rotten_num; i++) {
                Point temp = rotten.poll();
                int x = temp.x;
                int y = temp.y;
                if(new_array[x][y-1] == 1){
                    new_array[x][y-1] = 2;
                    rotten.add(new Point(x,y -1));
                }
                if(new_array[x][y+1] == 1){
                    new_array[x][y+1] = 2;
                    rotten.add(new Point(x,y + 1));
                }
                if(new_array[x - 1][y] == 1){
                    new_array[x - 1][y] = 2;
                    rotten.add(new Point(x - 1,y ));
                }
                if(new_array[x + 1][y] == 1){
                    new_array[x + 1][y] = 2;
                    rotten.add(new Point(x + 1,y ));
                }
            }
            /*for (int i = 0; i < new_array.length; i++) {
                for (int j = 0; j < new_array[0].length; j++) {
                    System.out.print(new_array[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");*/
            // 队列不为空才可+1，
            if(!rotten.isEmpty()){
                minites ++;
            }
        }
        //遍历数组看还有没新鲜橘子
        for (int i = 0; i < new_array.length; i++) {
            for (int j = 0; j < new_array[0].length; j++) {
                if(new_array[i][j] == 1){
                    return -1;
                }
            }
        }
        return minites;
    }

    public static void main(String[] args) {
        int[][] arr = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(arr));
    }
}
