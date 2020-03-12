package LeetCode.ordinary;

public class Code1071_gcdOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的,
        // 不进入该判断 表示x确实存在
        if( !(str1+str2).equals(str2 + str1)){
            return "";
        }
        //因为str1，str2长度都大于1，所以可从0开始，
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }
    private static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a % b);
    }

    public static void main(String[] args) {
        String str1 = "aba";
        String str2 = "aba";
        System.out.println(gcdOfStrings(str1,str2));
    }
}
