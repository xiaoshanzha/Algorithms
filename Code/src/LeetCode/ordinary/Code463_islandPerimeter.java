package LeetCode.ordinary;

public class Code463_islandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        int sum = 0;
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == 1){
                    sum += getsum(grid,i,j);
                }
            }
        }
        return sum;
    }
    public static int getsum(int[][] grid,int i,int j) {
        int sum = 0;
        if(i-1 >= 0){
            if(grid[i-1][j] == 1){
                sum++;
            }
        }
        if(i+1 < grid.length){
            if(grid[i+1][j] == 1){
                sum++;
            }
        }
        if(j-1 >= 0){
            if(grid[i][j-1] == 1){
                sum++;
            }
        }
        if(j+1 < grid[0].length ){
            if(grid[i][j+1] == 1){
                sum++;
            }
        }
        int res = 0;
        res = sum == 0 ? 4 : res;
        res = sum == 1 ? 3 : res;
        res = sum == 2 ? 2 : res;
        res = sum == 3 ? 1 : res;
        res = sum == 4 ? 0 : res;
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(islandPerimeter(arr));
    }
}
