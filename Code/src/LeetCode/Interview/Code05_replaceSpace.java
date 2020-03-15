package LeetCode.Interview;

public class Code05_replaceSpace {
    public String replaceSpace(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        char[] ss = s.toCharArray();
        String res = "";
        for (int i = 0; i < ss.length; i++) {
            if(ss[i] == ' '){
                res += "%20";
            }else {
                res += ss[i];
            }
        }
        return res;
    }
}
