package JD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
有n位乘客乘坐一列列车，列车一共会依次经过105个站点，从1到105编号。
        我们已知每一位乘客的上车站点和下车站点，但是不知道这些乘客的订票顺序。
        当一位乘客订票时，他会在当前还空余的座位中选择一个他喜欢的位置，但是我们不知道乘客的喜好，所有他具体订哪个位置我们是不知道的。
        现在你需要计算列车最少需要安排多少座位，可以使得无论乘客的订票情况和顺序是怎么样的，所有乘客都有座位可以坐。
        举个例子，有三位乘客：
        A：1→2
        B：2→3
        C：1→3
        若订票顺序是A, C, B，那么只需要两个座位就一定能满足。当A订票时，他会选择一个座位，当C订票时，可用座位只剩下一个，他会订这个剩余的座位，当B订票时，可用座位也只有一个，他会订这个座位(即最开始A的那个座位)；
        若订票顺序是A, B, C，那么有可能会需要三个座位，A订了一个座位，B订了与A不同的座位，此时C来订票时他只能订第三个座位。
        所以对于这组例子，答案是3。

        输入：   10
                84 302
                275 327
                364 538
                26 364
                29 386
                545 955
                715 965
                404 415
                903 942
                150 402
         输出： 6 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int[][] person = new int[n][2];
        for (int i = 0; i < n; i++) {
            strings = bf.readLine().split(" ");
            person[i][0] = Integer.parseInt(strings[0]);
            person[i][1] = Integer.parseInt(strings[1]);
        }
        Arrays.sort(person,(o1,o2)->{
            int res = o2[0] - o1[0];
            if(res == 0){
                res = o2[1] - o1[1];
            }
            return res;
        });
        for (int i = 0; i < n; i++) {
            System.out.println(person[i][0]+"  "+ person[i][1]);
        }
    }

   /* public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        boolean[] flag = new boolean[n];
        for (int j = 0; j < n; j++) {
            int[][] board = new int[6][2];
            for (int i = 0; i < 6; i++) {
                strings = bf.readLine().split(" ");
                board[i][0] = Math.min(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
                board[i][1] = Math.max(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
            }
            Arrays.sort(board,(o1,o2)->{
                int res = o1[0] - o2[0];
                if(res == 0){
                    res = o1[1] - o2[1];
                }
                return res;
            });
            if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] == board[3][0]
                    && board[0][1] == board[1][1]&& board[0][1] == board[4][0]&& board[0][1] == board[5][0]
                    && board[2][1] == board[3][1]&& board[2][1] == board[4][1]&& board[2][1] == board[5][1]){
                flag[j] = true;
            }else {
                flag[j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            if(flag[i]){
                System.out.println("POSSIBLE");
            }else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }*/
}






















/*BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        for (int i = 0; i < n; i++) {
            strings = bf.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                System.out.println(Integer.parseInt(strings[j]));
            }
        }*/

       /* //不知道多少组测试数据，读取输入，直到没有整型数据可读
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()){
            // 读取N 和 M
            int N = cin.nextInt();
            int M = cin.nextInt();
            System.out.println(String.format("%d %d", N, M));
            // 读取接下来M行
            for (int i=0; i<M; i++) {
                // 读取每行的a b c
                int a = cin.nextInt(),
                        b = cin.nextInt(),
                        c = cin.nextInt();
                System.out.println(String.format("%d %d %d", a, b, c));
            }
        }*/