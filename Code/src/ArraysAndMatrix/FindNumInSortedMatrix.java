package ArraysAndMatrix;

public class FindNumInSortedMatrix {
    /*
    * 在行列都排好序的矩阵中找数:
    *       给定一个有N*M的整型矩阵matrix和一个整数K，matrix的每一行和每一列都是排好序的。
    *       实现一个函数，判断K是否在matrix中。
    * 要求: 时间复杂度为O(N+M)，额外空间复杂度为O(1)。
    * eg:  0 1 2 5
    *      2 3 4 7
    *      4 4 4 8
    *      5 7 7 9    如果k = 7,返回true;如果k = 6,返回false;
    * 思路: 1.从矩阵最右上角的数开始寻找
    *       2.比较当前数matrix[row][col]与k的关系：
    *          如果与k相等，返回true
    *          如果比k大，则没必要继续在第col列进行寻找，因为下面的都大，令col = col - 1;重复步骤2.
    *          如果比k小，则没必要继续在第row行进行寻找，因为左边的都小，令row = row + 1;重复步骤2.
    *       3.如果找到越界都没发现相等的数，返回false；
    * */

    private static boolean isContains(int[][] matrix , int k){
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0){
            if(matrix[row][col] == k){
                return true;
            }else if(matrix[row][col] > k){
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },
                { 10, 12, 13, 15, 16, 17, 18 },
                { 23, 24, 25, 26, 27, 28, 29 },
                { 44, 45, 46, 47, 48, 49, 50 },
                { 65, 66, 67, 68, 69, 70, 71 },
                { 96, 97, 98, 99, 100, 111, 122 },
                { 166, 176, 186, 187, 190, 195, 200 },
                { 233, 243, 321, 341, 356, 370, 380 }};
        System.out.println("233:"+isContains(matrix, 233));
        System.out.println("250:"+isContains(matrix, 250));
    }
}
