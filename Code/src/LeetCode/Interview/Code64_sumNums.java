package LeetCode.Interview;

public class Code64_sumNums {
    public int sumNums(int n) {
        int sum = n;
        boolean a = ( n > 0 ) && ((sum += sumNums(n - 1)) > 0);
        return sum;
    }
}
