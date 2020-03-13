package LeetCode.ordinary;

public class Code79_exist {
    private char[] word ;
    private char[][] board ;
    private boolean[][] mark ;
    private int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    public boolean exist(char[][] board, String word) {
        this.word  = word.toCharArray();
        this.board = board;
        mark = new boolean[board.length][board[0].length];
        if(board == null || board.length == 0){
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(dfs(i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(int i , int j,int len){
        // dfs函数 如果判断到最后全成立 返回true，
        // 如果该位置匹配。继续上下左右匹配下一位置，不匹配则回溯，
        // 该位置不匹配直接false.
        if(len == word.length - 1){
            return word[len] == board[i][j];
        }
        if(word[len] == board[i][j]){
            //mark用来标记 该位置是否经过，
            mark[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int new_x = i + dir[k][0];
                int new_y = j + dir[k][1];
                //没走过该位置 并且 在区域内
                if(((new_x >= 0 && new_x < board.length)
                        && (new_y >= 0 && new_y < board[0].length))&&!mark[new_x][new_y] ){
                    if(dfs(new_x,new_y,len + 1)){
                        return true;
                    }
                }
            }
            mark[i][j] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        Code79_exist c = new Code79_exist();
        char[][] chars = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(c.exist(chars,"ABCB"));
    }
}
