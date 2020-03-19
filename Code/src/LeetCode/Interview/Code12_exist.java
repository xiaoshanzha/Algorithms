package LeetCode.Interview;

public class Code12_exist {
    public char[][] board ;
    public char[] word ;
    public boolean[][] mark;
    public int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0){
            return false;
        }
        this.board = board;
        this.word = word.toCharArray();
        mark = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(dfs(i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(int i ,int j,int len){
        if(len == word.length - 1){
            return board[i][j] == word[len];
        }
        if(board[i][j] == word[len]){
            mark[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int new_x = i + dir[k][0];
                int new_y = j + dir[k][1];
                if((new_x >= 0 && new_x < board.length) && (new_y >= 0 && new_y < board[0].length)
                        && !mark[new_x][new_y]){
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
        Code12_exist c = new Code12_exist();
        char[][] board = {{1,2},{1,2}};
    }
}
