public class Manacher {

    //生成新的字符串数组，解决差异性，长度都变成奇数个
    public char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
    public String longestPalindrome(String s) {
        if(s == null || s.length()==0){
            return "";
        }
        char[] charArr = manacherString(s);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        int final_index = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i],pR - i):1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1){
                if(charArr[i+pArr[i]] == charArr[i-pArr[i]] ){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] > pR){
                pR = i + pArr[i];
                index = i;
            }
            if(max < pArr[i]){
                max = pArr[i];
                final_index = i /2 - (max - 1)/2 ;
            }
        }

        String ss = s.substring(final_index , final_index + max -1);
        return ss;
    }


    //214题
    public char[] manacherStringByReverse(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = res.length-1; i >= 0 ; i--) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
    public String shortestPalindrome(String s) {
         if(s == null || s.length()==0){
            return "";
        }
        char[] charArr = manacherStringByReverse(s);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i],pR - i):1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1){
                if(charArr[i+pArr[i]] == charArr[i-pArr[i]] ){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] > pR){
                pR = i + pArr[i];
                index = i;
            }
            if(pR == charArr.length){
                maxContainsEnd = pArr[i];
                break;
            }
        }

        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = charArr[i * 2 + 1];
        }

        return String.valueOf(res) + s;

    }

    public static void main(String[] args) {
        Manacher manacher = new Manacher();
        System.out.println(manacher.shortestPalindrome("abcd"));
    }
}

/*
public class Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (pR == charArr.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2));

    }

}
*/
