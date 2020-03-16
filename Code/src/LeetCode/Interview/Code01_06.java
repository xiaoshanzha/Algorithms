package LeetCode.Interview;

public class Code01_06 {
    public static String compressString(String S) {
        if(S.length() <= 2){
            return S;
        }
        String res = "";
        char[] S_to_char = S.toCharArray();
        char temp = S_to_char[0];
        int left = 0;
        int right = 0;
        for (int i = 1; i < S_to_char.length; i++) {
            if(S_to_char[i] == temp){
                right = i;
                if(right == S_to_char.length - 1){
                    res = res + temp + (right - left + 1);
                }
            }else {
                int num = i - left;
                res = res + temp + num;
                left = i;
                if(left < S_to_char.length){
                    temp = S_to_char[left];
                }
                if(left == S_to_char.length - 1){
                    res = res + temp + 1;
                }
            }
        }
        return res.length() < S.length() ? res : S;
    }

    public static void main(String[] args) {
        System.out.println(compressString("aabbbbbc"));
    }
}
