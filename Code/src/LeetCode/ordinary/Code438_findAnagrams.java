package LeetCode.ordinary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code438_findAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();


        HashMap<Character,Integer> needs = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();

        int left = 0,right = 0,count = 0;
        for (Character c : p.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0) + 1);
        }
        while(right < s.length()){
            char temp = s.charAt(right);
            if(needs.containsKey(temp)){
                windows.put(temp,windows.getOrDefault(temp,0) + 1);
                if(windows.get(temp).equals(needs.get(temp))){
                    count ++;
                }
            }
            right++;

            //窗口大小大于s1长度时，应该缩小窗口
            if((right - left) > p.length()){
                char c = s.charAt(left);

                if(needs.containsKey(c)){
                    if(windows.get(c).equals(needs.get(c))){
                        count--;
                    }
                    windows.put(c,windows.get(c) - 1);
                }
                left++;
            }
            if(((right - left) == p.length()) && count == needs.size()){
                res.add(left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abab" ;
        String p = "ab";
        System.out.println(findAnagrams(s,p));
    }
}
