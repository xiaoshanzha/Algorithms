package ArraysAndMatrix;

public class PrintMatrix_By_SpiralOrder {
    /*
    * 给定一个整型矩阵matrix，请按照转圈的方式打印它:
    * 要求：额外空间复杂度为O(1)。
    * eg：1  2  3  4
    *     5  6  7  8
    *     9  10 11 12
    *     13 14 15 16
    * 打印结果：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10
    *
    * 处理：将矩阵分圈处理，给定左上角点(a,b)和右下角点(c,d)可表示一个子矩阵
    *      先将子矩阵的外层转圈打印出来，然后改变两点坐标(a++,b++),(c--,d--),将矩阵往里缩一层。
    *       再把这个子矩阵的外层打印...，如果左上角坐标跑到了右下角坐标的右方或下方，过程停止。
    *
    * 以下代码中printEdge就是转圈打印一个子矩阵的外层。
    * */

    public static void spiralOrderPrint(int[][] matrix){
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while(a <= c && b <= d){
            printEdge(matrix,a++,b++,c--,d--);
        }
    }

    /*
    * 打印外层矩阵：
    * 判断左上角点和右下角点是否在同一行，是否在同一列。
    * */
    private static void printEdge(int[][] matrix, int a, int b, int c, int d) {
        if(a == c){
            for (int i = b; i <= d; i++) {
                System.out.println(matrix[a][i]+" ");
            }
        }else if(b == d){
            for (int i = a; i <= c; i++) {
                System.out.println(matrix[i][b]+" ");
            }
        }else{
            // curR和curC分别表示转圈打印矩阵时当前位置的行列坐标
            int curR = a;
            int curC = b;
            while(curC != d){
                System.out.println(matrix[curR][curC]);
                curC++;
            }
            while(curR != c){
                System.out.println(matrix[curR][curC]);
                curR++;
            }
            while(curC != b){
                System.out.println(matrix[curR][curC]);
                curC--;
            }
            while(curR != a){
                System.out.println(matrix[curR][curC]);
                curR--;
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }
}
