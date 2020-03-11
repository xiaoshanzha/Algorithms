package LeetCode.ordinary;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Code118_generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if(j == 0 || j == i){
                    list.add(1);
                }else {
                    list.add(lists.get(i-1).get(j - 1) + lists.get(i-1).get(j));
                }
            }
            lists.add(list);
        }
        return lists;
    }
}
