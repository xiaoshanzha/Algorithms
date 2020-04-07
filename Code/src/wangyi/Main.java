package wangyi;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 0){
                    res[i][j] = 0;
                }else {
                    res[i][j] = getmin(arr,i,j);
                }
            }
        }
    }

    private static int getmin(int[][] arr, int i, int j) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i,j});
        int num = 0;
        while (!stack.isEmpty()){
            int size = stack.size();
            int index = 0;
            num++;
            boolean flag = false;
            while (index++ < size){
                int[] tmp = stack.pop();
                int r = tmp[0];
                int c = tmp[1];
                if(arr[r][c] == 0){
                    break;
                }
            }
            if(flag){
                break;
            }
        }
        return num;
    }
}

/*public class Main {
    public static class Node{
        private int pofang ;
        private int shanghai ;

        public Node(int pofang, int shanghai) {
            this.pofang = pofang;
            this.shanghai = shanghai;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int D = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            b[i] = sc.nextInt();
        }
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(a[i],b[i]);
        }
        Arrays.sort(nodes,(t0, t1) -> t0.pofang - t1.pofang);
        int start = 0;
        int end = 0;
        while (start < n && end <= n){
            if(end == n){
                System.out.println(start + " "+ end);
                Arrays.sort(nodes,start,end ,(t0, t1) -> t0.shanghai - t1.shanghai);
                break;
            }
            if(nodes[end].pofang == nodes[start].pofang){
                end++;
            }else {
                Arrays.sort(nodes,start,end,(t0, t1) -> t0.shanghai - t1.shanghai);
                start = end;
            }

        }
        for (int i = 0; i < n; i++) {
            System.out.println(nodes[i].pofang + "  " + nodes[i].shanghai);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if( D >= nodes[i].pofang){
                D = D + 1;
            }else {
                res = res + nodes[i].shanghai;
            }
        }
        System.out.println(res);
    }
}*/















   /* BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] strings = bf.readLine().split(" ");
    int n = Integer.parseInt(strings[0]);
        strings = bf.readLine().split(" ");*/