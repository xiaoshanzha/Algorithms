package LeetCode.ordinary;

public class Code09_isPalindrome {
    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        int a = x;
        int res = 0;
        while (a != 0){
            int temp = a % 10;
            a = a / 10;
            res = res*10 + temp;
        }
        return res == x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(-121));
    }
}
