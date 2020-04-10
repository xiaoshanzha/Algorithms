package LeetCode.ordinary;

public class Code151_reverseWords {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        if(chars == null || chars.length == 0){
            return s;
        }
        reverse(chars,0,chars.length - 1);
        int l = -1;
        int r = -1;
        // 确定每个单词的起始和终止位置
        for (int i = 0; i < chars.length; i++) {
            //确定起始位
            l = i == 0 || chars[i - 1] == ' ' ? i : l;
            //确定终止位
            r = i == chars.length - 1 || chars[i + 1] == ' ' ? i : r;

            if(l != -1 && r != -1){
                reverse(chars,l,r);
                l = -1;
                r = -1;
            }
        }

        int left = 0;
        int right = chars.length - 1;
        //去掉字符串开头的空白字符
        while (left <= right && chars[left] == ' ')
            left++;
        //去掉字符串末尾的空白字符
        while (left <= right && chars[right] == ' ')
            right--;
        //去掉字符串中间多余的空白字符
        StringBuilder sb = new StringBuilder();
        while (left <= right){
            char c = chars[left];
            if(c != ' '){
                sb.append(c);
            }
            else if(chars[left - 1] != ' '){  //c为' '并且前一字符不为' '
                sb.append(c);
            }
            left++;
        }
        return sb.toString();
    }

    //翻转字符串
    public void reverse(char[] chars,int start,int end){
        char temp = 0;
        while (start < end){
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        Code151_reverseWords c = new Code151_reverseWords();
        System.out.println(c.reverseWords("   sad  sda  zsda  "));
    }
}
