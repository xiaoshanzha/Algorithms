package LeetCode.ordinary;

import java.util.HashMap;

public class Code3_lengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int cur = 0;
        int length = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        while (cur < chars.length){
            if(map.containsKey(chars[cur]) && map.get(chars[cur]) >= left){
                length = Math.max(length,cur - left);
                int last = map.get(chars[cur]);
                left = last + 1;
                map.put(chars[cur],cur);
            }else if(map.containsKey(chars[cur]) && map.get(chars[cur]) < left){
                map.put(chars[cur],cur);
            }else {
                map.put(chars[cur],cur);
            }
            cur++;
        }
        return Math.max(length,cur - left);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
