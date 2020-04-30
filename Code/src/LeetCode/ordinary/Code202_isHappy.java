package LeetCode.ordinary;

import java.util.HashSet;
import java.util.Set;

public class Code202_isHappy {
    //使用快慢指针进行链表找环
    public int squareSum(int n){
        int sum = 0;
        while(n > 0){
            int r = n % 10;
            sum += r * r;
            n /= 10;
        }
        return sum;
    }
    public boolean isHappy1(int n) {
        int slow = n , fast = squareSum(n);
        while (slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return slow == 1;
    }

    //使用集合Set
    public static boolean isHappy(int n) {
        int sum = 0;
        int r = 0;
        while (n != 0){
            r = n % 10;
            sum += r*r;
            n = n / 10;
        }
        if(sum == 1){
            return true;
        }
        Set<Integer> set = new HashSet<Integer>();
        set.add(sum);
        while (true){
            int cur = sum;
            sum = 0;
            r = 0;
            while (cur != 0){
                r = cur % 10;
                sum += r * r;
                cur = cur / 10;
            }
            if(sum == 1){
                return true;
            }
            if(set.contains(sum)){
                return false;
            }
            set.add(sum);
        }
    }

    public static void main(String[] args) {
        System.out.println(isHappy(20));
    }
}
