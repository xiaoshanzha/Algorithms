package KS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int m = Integer.parseInt(line[0]);
        String[] strings = new String[m];
        for (int i = 0; i < m; i++) {
            strings[i] = bf.readLine();
        }
        Arrays.sort(strings);
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Set<String>> temp_map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            String temp = strings[i].substring(7, strings[i].length());
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '/') {
                    String last = temp.substring(j, temp.length());
                    String pre = temp.substring(0, j);
                    if (temp_map.get(pre) == null) {
                        set = new HashSet<>();
                    } else {
                        set = temp_map.get(pre);
                    }
                    if (set.contains(last)) {
                        continue;
                    } else {
                        String fina = map.get(pre) + last;
                        map.put(pre, fina);
                    }
                    break;
                }
                if (j == temp.length() - 1) {
                    String last = " ";
                    String pre = temp.substring(0, j + 1);
                    if (temp_map.get(pre) == null) {
                        set = new HashSet<>();
                    } else {
                        set = temp_map.get(pre);
                    }
                    if (set.contains(last)) {
                        continue;
                    } else {
                        String fina = map.get(pre) + last;
                        map.put(pre, fina);
                    }
                }
            }
        }
        for (String s : map.keySet()) {
            int num = 0;
            for (String s1 : map.keySet()) {
                if (s.equals(s1) && map.get(s).equals(map.get(s1))) {
                    if (num == 0) {
                        System.out.println(s);
                    } else {
                    }
                    num++;

                }
            }
        }
    }
}
/*  4.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int m = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            strings = bf.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(strings[j]);
            }
        }
        int num = 1;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 1){
                    num++;
                    infect(matrix,i,j,num);
                    res = res + add(matrix,num);
                }
            }
        }
        System.out.println(res);
    }

    private static int add(int[][] matrix, int num) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == num){
                    if(i == 0 || i == matrix.length-1||j == 0 || j == matrix[0].length - 1){
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    private static void infect(int[][] matrix, int i, int j, int num) {
        if( i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] != 1 ){
            return;
        }
        matrix[i][j] = num;
        infect(matrix,i-1,j,num);
        infect(matrix,i+1,j,num);
        infect(matrix,i,j-1,num);
        infect(matrix,i,j+1,num);
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
