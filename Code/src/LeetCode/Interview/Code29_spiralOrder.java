package LeetCode.Interview;

public class Code29_spiralOrder {
    /*
    * 转圈打印矩阵，先打印外圈矩阵，分为只有一行，只有一列，和正常
    * */
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int left_x = 0;
        int left_y = 0;
        int right_x = matrix.length - 1;
        int right_y = matrix[0].length - 1;
        int i = 0;
        //必须x，y都判断，防止错过
        while (left_x <= right_x && left_y <= right_y){
            //子矩阵只有一行时
            if(left_x == right_x){
                for (int j = left_y; j <= right_y; j++) {
                    res[i++] = matrix[left_x][j];
                }
            }else if(left_y == right_y){  //子矩阵只有一列时
                for (int j = left_x; j <= right_x; j++) {
                    res[i++] = matrix[j][left_y];
                }
            }else{//一般情况
                int cur_x = left_x;
                int cur_y = left_y;
                while (cur_y != right_y){
                    res[i++] = matrix[cur_x][cur_y++];
                }
                while (cur_x != right_x){
                    res[i++] = matrix[cur_x++][cur_y];
                }
                while (cur_y !=left_y){
                    res[i++] = matrix[cur_x][cur_y--];
                }
                while (cur_x != left_x){
                    res[i++] = matrix[cur_x--][cur_y];
                }
            }
            left_x++;
            left_y++;
            right_x--;
            right_y--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] arr1 = {{3},{2}};
        int[] res = spiralOrder(arr1);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
