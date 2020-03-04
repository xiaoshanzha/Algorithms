package LeetCode.ordinary;

public class Code130_SurroundArea {
    public static void infect(char[][] board,int i,int j) {
        if(i < 0 || i >= board.length||j < 0 || j>=board[0].length || board[i][j] != 'O'){
            return;
        }
        board[i][j] = 'A';
        infect(board,i - 1,j);
        infect(board,i + 1,j);
        infect(board,i ,j - 1);
        infect(board,i ,j + 1);
    }

    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < n; i++) {
            if(board[0][i] == 'O'){
                infect(board,0,i);
            }
            if(board[m - 1][i] == 'O'){
                infect(board,m - 1,i);
            }
        }
        for (int i = 0; i < m; i++) {
            if(board[i][0] == 'O'){
                infect(board,i,0);
            }
            if(board[i][n -1] == 'O'){
                infect(board,i,n - 1);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'}
        ,{'X','X','O','X'},{'X','O','X','X'}};
        solve(board);
    }
}
