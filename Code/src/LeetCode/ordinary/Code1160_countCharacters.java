package LeetCode.ordinary;

import java.util.HashMap;

public class Code1160_countCharacters {
    public static int countCharacters(String[] words, String chars) {
        int res = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (char c : chars.toCharArray()) {
            if(map.containsKey(c)){
                map.put(c,map.get(c) + 1);
            }else {
                map.put(c,1);
            }
        }
        for (String word : words) {
            HashMap<Character,Integer> map_word = new HashMap<>();
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if(map_word.containsKey(c)){
                    map_word.put(c,map_word.get(c) + 1);
                }else {
                    map_word.put(c,1);
                }
                if(map.containsKey(c) && map_word.get(c) <= map.get(c)){
                    flag = true;
                }else {
                    flag = false;
                    break;
                }
            }
            if(flag){
                res += word.length();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words= {"hello","world","leetcode"};
        String chars = "welldonehoneyr";
        System.out.println(countCharacters(words,chars));
    }
}
