package LeetCode.Interview;

import java.util.HashSet;

public class Code13_movingCount {
    HashSet<String> set = new HashSet<>();
    public int m;
    public int n;
    public int k;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        infect(0,0);
        return set.size();
    }
    public void infect(int i , int j){
        if(i < 0 || i >= m || j < 0 || j >= n ){
            return;
        }
        if(sum(i,j) <= k && !set.contains(i + " " + j)){
            set.add(i + " " + j);
            infect(i - 1,j);
            infect(i + 1,j);
            infect(i,j - 1);
            infect(i,j + 1);
        }
        return;
    }
    public int sum(int i,int j){
        int temp = 0;
        int res = 0;
        while(i > 0){
            temp += i % 10;
            i /= 10;
        }
        res += temp;
        temp = 0;
        while(j > 0){
            temp += j % 10;
            j /= 10;
        }
        res += temp;
        return res;
    }

    public static void main(String[] args) {
        Code13_movingCount c = new Code13_movingCount();
        System.out.println(c.movingCount(2,3,1));
    }
}
