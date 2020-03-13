package LeetCode.Interview;

import java.util.HashMap;
import java.util.HashSet;

public class Code50_firstUniqChar {
    public static char firstUniqChar(String s) {
        if(s.length() == 0){
            return ' ';
        }
        char[] chars = s.toCharArray();
        int i = 0;
        int cur = 0;
        int len = chars.length;
        HashMap<Character,Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        while (cur < len){
            // 没出现过
            if(!map.containsKey(chars[cur])){
                chars[i++] = chars[cur];
                map.put(chars[cur],1);
                set.add(chars[cur]);
            }else {
                if(map.get(chars[cur]) == 1){
                    set.remove(chars[cur]);
                }
                map.put(chars[cur],2);
            }
            cur++;
        }
        for (int j = 0; j < i; j++) {
            if(set.contains(chars[j])){
                return chars[j];
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("aab"));
    }
}
