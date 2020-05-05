package LeetCode.ordinary;

import java.util.HashMap;

public class Code76_minWindow {
    /*
    * 思路：
    * 1.使用左右双指针技巧，[left,right)左闭右开为一个窗口
    * 2.先不断增加right扩大窗口，直到窗口中的字符串符合要求
    * 3.停止增加right，不断增加left缩小窗口，直到窗口中的字符串不符合要求，
    * 4.重复2，3，直到right到达字符串S的尽头
    * */
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> needs = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();

        int left , right , count = 0,minLen = Integer.MAX_VALUE; //记录窗口的起始和终止，count表示窗口内已经符合要求的字符数量
        int start = 0,end = 0;

        //初始化needs
        for (Character c : t.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0) + 1); //needs如果包含c，则取出值加一，不包含取0 + 1；
        }

        left = right = 0; //窗口开始为[0,0);
        while (right < s.length()){
            char temp = s.charAt(right); //temp为更新窗口时，最右侧字符

            if(needs.containsKey(temp)){
                windows.put(temp,windows.getOrDefault(temp,0) + 1);

                //Integer不能用==进行比较，要用compareTo
                if(windows.get(temp).compareTo(needs.get(temp)) == 0){
                    //字符temp出现次数符合要求时，count++
                    count++;
                }
            }
            right ++; //此时窗口右边界已经右移一位

            //符合要求的字符个数正好是t中所有字符，获得一个可行解
            while (count == needs.size()){
                if(right - left < minLen){
                    start = left;
                    end = right;
                    minLen = end - start;
                }
                //左边界 进行缩小，即删除s[left]
                char c = s.charAt(left);

                //当前删除的字符包含于t，更新windows对应字符出现的次数，如果更新后的次数<t中的次数，符合要求的字符数减一
                if(needs.containsKey(c)){
                    windows.put(c,windows.get(c) - 1);
                    if(windows.get(c) < needs.get(c)){
                        count--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start,end);
    }

    public static void main(String[] args) {
        /*
        * Integer类型的数据在-128～127之间时，会使用缓存，用==比较时会比较数值，
        * 超出这个范围不会拆箱，比较的是对象,应该用compareTo或者equals进行比较
        * */
        Integer a = 1;
        Integer b = 1;
        Integer c = 200;
        Integer d = 200;

        System.out.println(a == b); //true
        System.out.println(a.compareTo(b) == 0); //true
        System.out.println(c == d);  //false
        System.out.println(c.compareTo(d) == 0); //true
        System.out.println(c.equals(d)); //true
    }
}
