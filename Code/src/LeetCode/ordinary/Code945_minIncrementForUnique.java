package LeetCode.ordinary;

import java.util.Arrays;

public class Code945_minIncrementForUnique {
    /*
    * 使数组唯一的最小增量：给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
    *                    返回使 A 中的每个值都是唯一的最少操作次数。
    *
    * 1.排序法：先将数组进行排序，然后进行遍历，A[i]小于等于前一个值时，
    *          将A[i]变为A[i - 1] + 1 ;更新操作数。
    * 2.计数法：统计每个数字出现的个数，然后再次进行遍历，
    *           出现次数大于1的，记录舍弃的个数，并减少move的操作数。继续向后遍历
    *           若出现次数为0并且舍弃的个数不为0，则move+该数，意为将以前的一个重复数变为该数
    * */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int move = 0;
        for (int i = 1; i < A.length; i++) {
            if(A[i] <= A[i - 1]){
                int value = A[i - 1] + 1;
                move += (value - A[i]);
                A[i] = value;
            }
        }
        return move;
    }
    public int minIncrementForUnique2(int[] A) {
        int[] counts = new int[80000];
        for (int i = 0; i < A.length; i++) {
            counts[A[i]]++;
        }
        int movetimes = 0;
        int nums = 0; //表示舍掉的个数
        for (int i = 0; i < 80000; i++) {
            if(counts[i] >= 2){
                nums += (counts[i] - 1);
                movetimes -= i * (counts[i] - 1);
            }else if(nums > 0 && counts[i] == 0){
                movetimes += i;
                nums--;
            }
        }
        return movetimes;
    }
}
