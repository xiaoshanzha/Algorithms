package LeetCode.ordinary;

public class Code200_numIslands {
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
}
