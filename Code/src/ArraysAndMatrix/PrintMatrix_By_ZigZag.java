package ArraysAndMatrix;

public class PrintMatrix_By_ZigZag {
    /*
    * "之"字形打印矩阵：
    * 要求：额外空间复杂度为O(1)。
    * eg: 1  2  3  4
    *     5  6  7  8
    *     9  10 11 12
    * "之"字形打印结果：1，2，5，9，6，3，4，7，10，11，8，12
    * 思路：（宏观）
    *     将打印的矩阵分成很多条斜线打印，每次通过两坐标点确定一条斜线。
    *     1.上坐标(a,b)初始为(0,0),先沿着矩阵第一行移动(b++)，
    *       当到达第一行最右边的元素后，沿着矩阵最后一列移动(a++)
    *     2.下坐标(c,d)初始为(0,0),先沿着矩阵第一列移动(c++)，
    *       当到达第一列最下边的元素后，沿着矩阵最后一行移动(d++)
    *     3.上坐标和下坐标同步移动，每次移动后的上下坐标连线就是矩阵的一条斜线
    *     4.用boolean值表示斜线是从上向下打印还是从下向上打印。
    * */
    private static void printMatrixZigZag(int[][] matrix){
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (a != endR + 1){
            printLevel(matrix,a,b,c,d,fromUp);
            // 判断上坐标的纵坐标是否到最后一列，不到：横不变，纵++；到： 横++，纵不变
            // 用纵坐标进行判断，变换应先横再纵
            a = b == endC ? a + 1 : a;
            b = b == endC ? b : b + 1;
            // 判断下坐标的横坐标是否到最后一行，不到：横++，纵不变；到： 横不变，纵++；
            // 用横坐标进行判断，变换应先纵再横
            d = c == endR ? d + 1 : d;
            c = c == endR ? c : c + 1;
            fromUp = !fromUp;
        }
    }

    private static void printLevel(int[][] matrix, int a, int b, int c, int d, boolean fromUp) {
        if(fromUp){
            while (b >= d){
                System.out.println(matrix[a++][b--] + " ");
            }
        }else {
            while (c >= a){
                System.out.println(matrix[c--][d++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);
    }
}
