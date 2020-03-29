import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static class Node{
        int a;
        int b;
        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        strings = bf.readLine().split(" ");
        int m = Integer.parseInt(strings[0]);
        strings = bf.readLine().split(" ");
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strings[i]);
        }
        strings = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(strings[i]);
        }
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(a[i],b[i]);
        }
        Arrays.sort(nodes,(t0, t1) -> (t1.a - t0.a));
        Arrays.sort(nodes,(t0, t1) -> (t1.b - t0.b));
        int jian = 0;
        int cur = 0;
        for (int i = 0; i < m; i++) {
            jian += nodes[cur++].b * i;
        }
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += nodes[i].a;
        }
        System.out.println(sum - jian);
    }
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



/* PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t0, Integer t1) {
                return t1 - t0;
            }
        });
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int temp = i * j;
                if(((i - 1) * m + j )<= k){
                    queue.add( temp);
                }else {
                    if(queue.peek() > temp){
                        queue.poll();
                        queue.add(temp);
                    }
                    if(temp >= queue.peek()){
                        break;
                    }
                }
            }
        }
        System.out.println(queue.peek());*/
