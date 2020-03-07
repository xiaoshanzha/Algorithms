package LeetCode.ordinary;

public class Code06_Convert {
    public static String convert(String s, int numRows) {
        if(numRows == 1 || numRows >= s.length()){
            return s;
        }
        int[] rows = new int[2 * numRows - 2];
        int i = 0;
        for (i = 0; i < numRows; i++) {
            rows[i] = i;
            System.out.print(i + " ");
        }
        for (int j = numRows - 2; i < rows.length; j--,i++) {
            rows[i] = j;
        }
        String[] res = new String[numRows];
        for (i = 0; i < s.length(); i++) {
            if(i < numRows){
                res[i] = "";
            }
            int index = i % rows.length;
            res[rows[index]] += s.charAt(i);
        }
        String ss = "";
        for (String sss : res) {
            ss += sss;
        }
        return ss;
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING",3));
    }
}
