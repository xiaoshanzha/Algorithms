package LeetCode.Interview;

import java.util.ArrayList;
import java.util.List;

public class Code58_1_reverseWords {
    public static String reverseWords(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        char[] s_char = s.toCharArray();
        List<String> lists = new ArrayList<>();
        int l = 0;
        int r = -1;
        int i = 0;
        while(i < s.length()){
            if(s_char[i] != ' ' && (i == 0 || s_char[i - 1] == ' ')){
                l = i;
            }
            if(s_char[i] != ' ' && (i == s.length() - 1 || s_char[i + 1] == ' ')){
                r = i;
            }
            if(r >= l){
                String t = s.substring(l,r + 1);
                lists.add(t);
                r = -1;
            }
            i++;
        }
        String res = "";
        int len = lists.size();
        //可能出现只有空格的情况，len = 0；
        if(len > 0){
            for (int j = len - 1; j > 0; j--) {
                res += lists.get(j);
                res += " ";
            }
            res += lists.get(0);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(" "));
    }
}
