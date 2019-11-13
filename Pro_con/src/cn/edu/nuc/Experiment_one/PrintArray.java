package cn.edu.nuc.Experiment_one;

public class PrintArray {
    public static void main(String[] args) {
        int[][] array = new int [4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = i+j;
                System.out.print(array[i][j]+"  ");
            }
            System.out.print("\n");
        }
    }
}
