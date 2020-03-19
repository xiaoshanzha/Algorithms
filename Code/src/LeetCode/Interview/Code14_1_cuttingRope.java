package LeetCode.Interview;

public class Code14_1_cuttingRope {
    /*
    * 贪心：2 = 1 + 1； 1*1 < 2,所以 不拆分，
    *       3 = 1 + 2   1*2 < 3 ,所以 不拆分
    *       4 = 2 + 2   2*2 > 4 ,所以 拆分
    *       5 = 3 + 2   3*2 > 5 ,所以 拆分
    *       6 = 3 + 3   3*3 > 6 ,所以 拆分
    *       7 = 3 + 4   3*4 > 7 ,所以 拆分
    *
    * 贪心策略：
    * 第一优先级：3；把数字 n 拆成尽可能多的 3 之和；
    *   特殊情况： 拆完后，如果余数是 1；则应把最后的 3 + 1 替换为 2 + 2，因为后者乘积更大；
    * 第二优先级：2；留下的余数如果是 2，则保留，不再拆为 1+1。
     * */
    public int cuttingRope(int n) {
        if(n <= 3){
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;
        if(b == 0){
            return (int) Math.pow(3,a);
        }
        if(b == 1){
            return (int) Math.pow(3,a-1) * 4;
        }
        return (int)Math.pow(3, a) * 2;
    }
}
