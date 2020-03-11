package LeetCode.ordinary;

public class Code1013_canThreePartsEqualSum {
    public static boolean canThreePartsEqualSum(int[] A) {
        if(A.length <= 2){
            return false;
        }
        int sum = 0;
        int ave = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        if(sum % 3 != 0){
            return false;
        }
        ave = sum / 3;
        // 将i、j设置成-1，可以看出i，j有没发生过改变
        int i = -1;
        int j = -1;
        int temp_sum = 0;
        for (int cur = 0; cur < A.length; cur++) {
            temp_sum += A[cur];
            //可能出现相加和为0的情况，如果不设置i，j 是否被改变的条件，当满足ave，必满足2ave
            //使得i，j均停在第一次就满足的索引
            if(temp_sum == ave && i < 0){
                i = cur;
                continue;
            }
            // 此时不能break： 因为可能先出现 2ave，再1ave,再2ave，再3ave。
            if(temp_sum == 2 * ave && j < 0 && i >= 0 ){
                j = cur;
            }
        }
/*        System.out.println("sum : " + sum + " ave : " + ave);
        System.out.println("i : " + i + " j : " + j);*/
        if(i < j && j < A.length - 1 && i >=0 ){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {0,2,1,-6,6,-7,9,1,2,0,1};
        int[] arr1 = {0,2,1,-6,6,7,9,-1,2,0,1};
        int[] arr2 = {3,3,6,5,-2,2,5,1,-9,4};
        int[] arr3 = {3,3,1,4};
        int[] arr4 = {6,1,1,13,-1,0,-10,20};
        int[] arr5 = {29,31,27,-10,-67,22,15,-1,-16,-29,59,-18,48};
        int[] arr6 = {10,-10,10,-10,10,-10,10,-10,0};
        System.out.println(canThreePartsEqualSum(arr));
        System.out.println(canThreePartsEqualSum(arr1));
        System.out.println(canThreePartsEqualSum(arr2));
        System.out.println(canThreePartsEqualSum(arr3));
        System.out.println(canThreePartsEqualSum(arr4));
        System.out.println(canThreePartsEqualSum(arr5));
        System.out.println(canThreePartsEqualSum(arr6));
    }
}
