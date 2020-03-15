package LeetCode.ordinary;

public class Code695_maxAreaOfIsland {
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

    public static void main(String[] args) {
        Code695_maxAreaOfIsland code = new Code695_maxAreaOfIsland();
        int[][] arr = {{0,0,1,1,1,0,0,0}};
        System.out.println(code.maxAreaOfIsland(arr));
    }
}
