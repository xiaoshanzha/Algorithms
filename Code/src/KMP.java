import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KMP {

    //根据match串获取next数组
    public static int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while(pos < next.length){
            if(ms[pos - 1] == ms[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                next[pos++] = 0;
            }
        }
        return next;
    }

 public int getIndexOf(String s,String m){
        if(s == null || m == null || m.length() < 1 || m.length() > s.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] next = getNextArray(ms);
        int si = 0;
        int mi = 0;
        while (si < ss.length && mi < ms.length){
            if(ss[si] == ms[mi]){
                si++;
                mi++;
            }else if (next[mi] == -1){
                si++;
            }else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }


    public static ArrayList<Integer> getIndexOfByniuke(String s, String m){
        if(s == null || m == null || m.length() < 1 || m.length() > s.length()){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(-1);
            return list;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] next = getNextArray(ms);
        int si = 0;
        int mi = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (si < ss.length ){
            if(ss[si] == ms[mi]){
                si++;
                mi++;
            }else if (next[mi] == -1){
                si++;
            }else {
                mi = next[mi];
            }

            if(mi == ms.length){
                list.add(si - mi);
                mi = 0;
            }
        }
        if(list.size() == 0){
            list.add(-1);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        String match = bf.readLine();
        ArrayList<Integer> list = getIndexOfByniuke(str,match);
        int i = 0;
        while (i < list.size() -1 ){
            System.out.print(list.get(i++)+" ");
        }
        System.out.print(list.get(i));
    }
}
