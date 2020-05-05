package LeetCode.ordinary;

import java.util.HashMap;

public class Code567_checkInclusion {
    /*
    * 与最小覆盖子串不同点：
    * 1.移动left缩小窗口时机：窗口大小大于s1长度时，因为排列，所以长度肯定一样
    * 2.当count == needs.length,并且窗口大小等于s1长度，返回true
    * */
    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> needs = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();

        int left = 0,right = 0,count = 0;
        for (Character c : s1.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0) + 1);
        }
        while(right < s2.length()){
            char temp = s2.charAt(right);
            if(needs.containsKey(temp)){
                windows.put(temp,windows.getOrDefault(temp,0) + 1);
                if(windows.get(temp).equals(needs.get(temp))){
                    count ++;
                }
            }
            right++;

            //窗口大小大于s1长度时，应该缩小窗口
            if((right - left) > s1.length()){
                char c = s2.charAt(left);

                if(needs.containsKey(c)){
                    if(windows.get(c).equals(needs.get(c))){
                        count--;
                    }
                    windows.put(c,windows.get(c) - 1);
                }
                left++;
            }
            if(((right - left) == s1.length()) && count == needs.size()){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidboaoo";
        System.out.println(checkInclusion(s1,s2));
    }
}
