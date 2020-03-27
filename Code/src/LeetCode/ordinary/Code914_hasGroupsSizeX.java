package LeetCode.ordinary;

import java.util.HashMap;

public class Code914_hasGroupsSizeX {
    public static boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int kindNum = 0;
        for (int i = 0; i < deck.length; i++) {
            if(map.containsKey(deck[i])){
                map.put(deck[i],map.get(deck[i]) + 1);
            }else {
                kindNum++;
                map.put(deck[i],1);
            }
        }
        int[] kind_value = new int[kindNum];
        int i = 0;
        int min = Integer.MAX_VALUE;
        for (Integer a : map.keySet()) {
            kind_value[i++] = map.get(a);
            min = Math.min(min,map.get(a));
        }
        if(min == 1){
            return false;
        }
        for (int j = 2; j <= min; j++) {
            for (int k = 0; k < kindNum; k++) {
                if(kind_value[k] % j != 0){
                    break;
                }else if(k == kindNum - 1){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,2};
        System.out.println(hasGroupsSizeX(arr));
    }
}
