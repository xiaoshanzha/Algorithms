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

    public static int lengthOfLongestSubstring1(String s) {
        HashMap<Character,Integer> window = new HashMap<>();
        int left = 0 , right = 0;
        int res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            window.put(c,window.getOrDefault(c,0) + 1);
            right++;
            while (window.get(c) > 1){
                char d = s.charAt(left);
                left++;
                //窗口内数据进行更新
                window.put(d,window.get(d) - 1);
            }
            res = Math.max(res,right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("pwwkew"));
    }
}
