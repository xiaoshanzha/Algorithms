package ArraysAndMatrix;

public class RotateMatrix {
    /*
    * 旋转正方形矩阵:
    * 给定一个N*N矩阵matrix，请把该矩阵调整成顺时针旋转90度的样子。
    * 要求：额外空间复杂度为O(1)
    * eg: 1  2  3  4                13 9  5  1
    *     5  6  7  8      ----->    14 10 6  2
    *     9 10 11 12      ----->    15 11 7  3
    *    13 14 15 16                16 12 8  4
    * 思路： 仍采用分圈处理的方式，矩阵左上角的坐标(a,b)和右下角的坐标(c,d)表示一个子矩阵，确定子矩阵最外层部分
    *       如上例，1，4，16，13为一组，然后1占4位置，4占16位置...一组调完后，
    *              然后2，8，15，9为下一组，继续占据调整。最后3，12，14，5为一组，继续该过程
    *       该外层调整完成后，a++,b++,c--,d--.在确定子矩阵，重复该过程
    *       若子矩阵为M*M，共有M-1组，分别进行占据调整
    * */
    private static void rotate(int[][] matrix){
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix,a++,b++,c--,d--);
        }
    }

    private static void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int times = d - b;  //times表示确定的子矩阵有times组需要进行调整
        for (int i = 0; i < times; i++) {
            int tmp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] =  tmp;
        }
    }

    private static void printMatrix(int[][] matrix){
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
