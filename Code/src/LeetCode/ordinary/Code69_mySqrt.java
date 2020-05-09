package LeetCode.ordinary;

public class Code69_mySqrt {
    public static int mySqrt(int x) {

        // 如果是大数，int不能通过
        long left = 1;
        long right = x;
        while(left < right){
            long mid = (left + right) / 2;
            if(mid * mid == x){
                return (int) mid;
            }else if(mid * mid < x){
                left = mid + 1;
            }else if(mid * mid > x){
                right = mid ;
            }
        }
        return left * left > x ? (int) left - 1 : (int) left;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(1));
        System.out.println(mySqrt(2));
        System.out.println(mySqrt(3));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(5));
        System.out.println(mySqrt(6));
        System.out.println(mySqrt(7));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(9));
    }
}
