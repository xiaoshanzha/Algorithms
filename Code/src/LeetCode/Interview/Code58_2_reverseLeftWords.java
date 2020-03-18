package LeetCode.Interview;

public class Code58_2_reverseLeftWords {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n,s.length()) +
                s.substring(0,n);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(s.substring(s.length() - 3,s.length() ) +
                s.substring(0,s.length() - 3));
    }
}
