package NowCoder;

import java.io.*;
import java.util.LinkedList;

/*
* 最大值减最小值小于或等于num的子数组数量
* */
public class GetNumByMaxMin {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        String[] strings = line.split(" ");
        int n = Integer.parseInt(strings[0]);
        int num = Integer.parseInt(strings[1]);
        line = bf.readLine();
        String[] s = line.split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        System.out.println(getNum(arr,num));
    }

    public static int getNum(int[] arr,int num){
        int res = 0;
        int i = 0;
        int j = 0;
        //数组值从小到大
        LinkedList<Integer> window_min = new LinkedList<>();
        //数组值从大到小，
        LinkedList<Integer> window_max = new LinkedList<>();
        for ( i = 0; i < arr.length; i++) {
            while (j < arr.length){
                //从后弹出无效索引 再进
                while(!window_min.isEmpty() && arr[window_min.peekLast()] >= arr[j]){
                    window_min.pollLast();
                }
                window_min.addLast(j);
                while(!window_max.isEmpty() && arr[window_max.peekLast()] <= arr[j]){
                    window_max.pollLast();
                }
                window_max.addLast(j);

                if(arr[window_max.peekFirst()] - arr[window_min.peekFirst()] > num){
                    break;
                }
                j++;
            }
            res += j - i;
            //更新结构看下标是否过期
            if(window_min.peekFirst() == i ){
                window_min.pollFirst();
            }
            if(window_max.peekFirst() == i){
                window_max.pollFirst();
            }
        }
        return res;
    }
    
}
