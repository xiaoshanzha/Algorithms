package LeetCode.ordinary;

public class Code66_plusOne {
    public static int[] plusOne_1(int[] digits) {
        boolean flag = false;
        int[] res = new int[digits.length];
        for (int i = digits.length - 1; i >= 0 ; i--) {
            if(i == digits.length - 1 && digits[i] != 9){
                digits[i]++;
                return digits;
            }
            if(i == digits.length - 1 && digits[i] == 9){
                flag = true;
                res[i] = 0;
            }else if(digits[i] == 9 && flag){
                flag = true;
                res[i] = 0;
            }else if(flag){
                res[i] = digits[i] + 1;
                flag = false;
            }else {
                flag = false;
                res[i] = digits[i];
            }
        }
        if(!flag){
            return res;
        }else {
            int[] arr = new int[digits.length + 1];
            arr[0] = 1;
            for (int i = 0; i < res.length; i++) {
                arr[i + 1] = res[i];
            }
            return arr;
        }
    }

    /*
    * 只要进入循环，默认有进位，直接加1取余，取余后如果不是0，说明没有进位了，直接返回
    * */
    public static int[] plusOne_2(int[] digits) {
        for (int i = digits.length - 1; i >= 0 ; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if(digits[i] != 0 ){
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        int[] res = new int[]{9,9,8,9};
        int[] f = plusOne_1(res);
        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i] + " ");
        }
    }
}
