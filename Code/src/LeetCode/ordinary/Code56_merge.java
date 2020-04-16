package LeetCode.ordinary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code56_merge {
    /*
    * 先将数组按照左值递增进行排序，然后顺序遍历数组，用另外的内存进行存储
    * 每次取出排序数组中的值，取出数组左值和上个已确定区间右值进行比较
    * 如果取出左值大于上个右值，直接将取出数组加入，因为没有重复的区间
    * 如果取出左值小于上个右值，则存在重复区间，再将取出数组右值和上个右值进行比较，更新上个右值为较大者
    * */
    public static int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return intervals;
        }
        Arrays.sort(intervals,(t0,t1) -> {
            int res = t0[0] - t1[0];
            if(res == 0){
                res = t1[1] - t0[1];
            }
            return res;
        });
        List<int[]> list = new ArrayList<>();
        int last = 0;
        for (int[] item : intervals) {
            if(list.size() == 0){
                list.add(item);
                last = item[1];
                continue;
            }
            if(item[0] > last){
                list.add(item);
                last = item[1];
            }else {
                list.get(list.size() - 1)[1] = Math.max(last,item[1]);
                last = Math.max(last,item[1]);
            }
        }
        int size = list.size();
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3},{2,6},{2,16},{15,18}};
        int[][] res = merge(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i][0] + " " + res[i][1]);
        }
    }
}
