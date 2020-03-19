package LeetCode.ordinary;

import java.util.HashMap;

public class Code409_longestPalindrome {
    public int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0 ; i < s.length();i++){
            if(map.containsKey(chars[i])){
                map.put(chars[i],map.get(chars[i]) + 1);
            }else {
                map.put(chars[i],1);
            }
        }
        int res = 0;
        boolean ji = false;
        for (Character c : map.keySet()) {
            if(map.get(c) % 2 != 0){
                ji = true;
                res += (map.get(c) - 1);
            }else {
                res += map.get(c);
            }
        }
        if(ji){
            res = res + 1;
        }
        return res ;
    }
    public int longestPalindromeByArray(String s) {
        int[] arr = new int[128];
        for (char c : s.toCharArray()){
            arr[c] ++;
        }
        // count用来记录个数为奇数的个数
        int count = 0;
        for(int i : arr){
            count += (i % 2);
        }
        return count == 0 ? s.length() : (s.length() - count + 1);
    }
}
