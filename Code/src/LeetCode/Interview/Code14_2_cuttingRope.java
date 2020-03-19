package LeetCode.Interview;

public class Code14_2_cuttingRope {
    public static int cuttingRope(int n) {
        if(n <= 3){
            return n-1;
        }
        int a = n / 3;
        int b = n % 3;
        long res = 1;
        for (int i = 0; i < a - 1; i++) {
            res = res * 3 % 1000000007;
        }
        if(b == 0){
            res = res * 3 % 1000000007;
        }
        if(b == 1){
            res = res * 4 % 1000000007;
        }
        if(b == 2){
            res = res * 6 % 1000000007;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(cuttingRope(120));

    }
}
