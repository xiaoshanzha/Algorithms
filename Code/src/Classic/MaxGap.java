package Classic;

import java.util.Arrays;

public class MaxGap {
    /*
    * 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N)，且要求不能用非基于比较的排序。
    *
    * 借用桶的概念，N个数，准备N+1个桶，先遍历数组，找到min 和 max;
    * min放在0号桶，max放在N号桶，中间范围min ~ max 等分成N + 1份，属于哪个范围就放在哪个桶里
    * eg: 数组9个数，准备10个桶，min = 0,max = 99;则0-9放在1号桶，10-19放2号桶......90-99放10号桶里
    *     相邻两数可来自同一桶，也可来自不同桶；
    * 放完之后中间必有空桶，设置空桶的目的：最大差值肯定不是同一桶内的两数(只杀死可能性)，但答案不一定就是空桶两侧非空桶
    *
    * 桶内只记录进入该桶数的min 和 max,还有 bool(表示该桶是否进过数)
    * 遍历将数组中的数全部进入桶，得到桶内信息，
    * 然后遍历桶，空桶直接跳下一桶，非空桶则寻找前一非空桶，其前一非空桶的max与自己桶的min计算差值
    * */

    private static int maxGap(int[] nums){
        if(nums == null || nums.length < 2){
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        if(min == max ){
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bucked_id = 0;
        for (int i = 0; i < len; i++) {
            bucked_id = bucket(nums[i],len,min,max);
            mins[bucked_id] = hasNum[bucked_id] ? Math.min(mins[bucked_id],nums[i]) : nums[i];
            maxs[bucked_id] = hasNum[bucked_id] ? Math.max(maxs[bucked_id],nums[i]) : nums[i];
            hasNum[bucked_id] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for ( i = 1; i <= len ; i++) {
            if(hasNum[i]){
                res = Math.max(res,mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    /*
    *  确定nums属于哪个桶
    * */
    private static int bucket(long num, long len, long min, long max) {
        return (int)((num - min) * len / (max - min));
    }

    private static int comparator(int[] nums){
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1],gap);
        }
        return gap;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    private static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck!");
    }

}
