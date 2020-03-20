package LeetCode.Interview;

import java.util.HashMap;

public class Code48_lengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        int max_len = 0;
        int left = 0;
        int i ;
        for (i = 0; i < chars.length; i++) {
            if(!map.containsKey(chars[i]) || map.get(chars[i]) < left){
                map.put(chars[i],i);
            }else {
                max_len = Math.max(max_len,i - left);
                left = map.get(chars[i]) + 1;
                map.put(chars[i],i);
            }
        }
        return Math.max(max_len , i - left);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("ASDF"));
    }
}
