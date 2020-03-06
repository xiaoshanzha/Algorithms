package LeetCode.Interview;

import java.util.ArrayList;
import java.util.List;

public class Code57_2_FindContinuousSequence {
    public static int[][] findContinuousSequence(int target) {
        if(target == 1 || target == 2){
            return new int[0][];
        }
        //如果是两个数组成的，找到最远的开始节点
        int left_max = 1;
        while(2*left_max+1 < target){
            left_max++;
        }
        //根据等差数列，找到以1为首的最右端，即最右端的最早开始位置，
        // 因为长度变短后，要想得到相同的target，最右端就需右移
        int right = 2;
        while ((1+right) * right /2 < target){
            right++;
        }
        int len_max = right;
        int cur_left = 1;
        int cur_right = len_max;
        List<int[]> list = new ArrayList<>();
        //然后利用双指针
        while (cur_left <= left_max){
            int sum = (cur_left + cur_right) * (cur_right - cur_left + 1) / 2;
            if( sum > target){
                cur_left++;
            }else if(sum == target){
                int l = cur_left++;
                int r = cur_right++;
                int[] temp = new int[r - l + 1];
                for (int j = 0; j < temp.length; j++) {
                    temp[j] = l + j;
                }
                list.add(temp);
            }else {
                cur_right++;
            }
        }
        int[][] arr =  new int[list.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int arr[][] = findContinuousSequence(15);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
