package LeetCode.ordinary;

public class Code289_gameOfLife {
    /*
    * 方式一：复制一个新数组，统计后改变原数组状态
    * 方式二：不需要新的数组，设置复合状态，一个数可以看出更新前后的两种状态，最后进行改变
    * eg: 0 -> 1  设置为2
    *     1 -> 0 设置为-1
    * */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0){
            return;
        }
        int[][] new_board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                new_board[i][j] = board[i][j];
            }
        }
        int[] neighbors = new int[]{-1,0,1};
        for (int i = 0; i < new_board.length; i++) {
            for (int j = 0; j < new_board[0].length; j++) {
                //开始统计每个位置活细胞的数量
                int livenums = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(!(neighbors[k] == 0 && neighbors[l] == 0)){
                            int r = (i + neighbors[k]);
                            int c = (j + neighbors[l]);
                            //统计该位置相邻位置的细胞状态
                            if((r >= 0 && r < new_board.length)
                            && (c >= 0 && c < new_board[0].length)
                            && new_board[r][c] == 1){
                                livenums++;
                            }
                        }
                    }
                }
                //周围细胞状态统计完后，开始更改状态
                if(new_board[i][j] == 1 && (livenums > 3 || livenums < 2)){
                    board[i][j] = 0;
                }
                if(new_board[i][j] == 0 && livenums == 3){
                    board[i][j] = 1;
                }
            }
        }
    }

    public void gameOfLife2(int[][] board) {
        if (board == null || board.length == 0){
            return;
        }
        int[] neighbors = new int[]{-1,0,1};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //开始统计每个位置活细胞的数量
                int livenums = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(!(neighbors[k] == 0 && neighbors[l] == 0)){
                            int r = (i + neighbors[k]);
                            int c = (j + neighbors[l]);
                            //统计该位置相邻位置的细胞状态
                            if((r >= 0 && r < board.length)
                                    && (c >= 0 && c < board[0].length)
                                    && (board[r][c] == 1 || board[r][c] == -1)){
                                livenums++;
                            }
                        }
                    }
                }
                //周围细胞状态统计完后，开始更改状态
                if(board[i][j] == 1 && (livenums > 3 || livenums < 2)){
                    board[i][j] = -1;
                }
                if(board[i][j] == 0 && livenums == 3){
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 2){
                    board[i][j] = 1;
                }else if(board[i][j] == -1){
                    board[i][j] = 0;
                }
            }
        }
    }
}
