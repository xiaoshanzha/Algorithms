package LeetCode.Interview;

public class Code13_movingCount {
    public int m;
    public int n;
    public int k;
    public int[][] map;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.map = new int[m][n];
        dfs(0,0);
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1){
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(int i , int j){
       if(i < 0 || i >= m || j < 0 || j >= n || map[i][j] == 1){
           return;
       }
       if(getsum(i,j) <= k){
           map[i][j] = 1;
           dfs(i + 1,j);
           dfs(i - 1,j);
           dfs(i , j - 1);
           dfs(i ,j + 1);
       }
    }

    public int getsum(int i,int j){
        int temp = 0;
        int res = 0;
        while(i > 0){
            temp += i % 10;
            i /= 10;
        }
        res += temp;
        temp = 0;
        while(j > 0){
            temp += j % 10;
            j /= 10;
        }
        res += temp;
        return res;
    }

    public static void main(String[] args) {
        Code13_movingCount c = new Code13_movingCount();
        System.out.println(c.movingCount(3,1,0));
    }
}
