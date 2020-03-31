package LeetCode.ordinary;

public class Code8_myAtoi {
    public static int myAtoi(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int cur = 0;
        while(chars[cur] == ' '){
            cur++;
            if(cur == chars.length){
                return 0;
            }
        }
        boolean flag = true;
        if(chars[cur] == '-'){
            flag = false;
            cur++;
        }else if(chars[cur] == '+'){
            cur++;
        }
        if(cur == chars.length || (chars[cur] != '-' && (chars[cur] < '0' || chars[cur] > '9'))){
            return 0;
        }
        long res = 0;
        while (cur < chars.length && ( '0' <= chars[cur] && chars[cur] <= '9')){
            res = res * 10 + (chars[cur] - '0');
            if(flag && res > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            if(!flag && -res < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
            cur++;
        }
        if(!flag){
            res = -res;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi(" "));
    }
}
