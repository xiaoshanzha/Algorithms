package LeetCode.ordinary;

public class Code260_singleNumber {
    /*
    * 异或：相同为0，不同为1；
    *
    * 0和任意数异或 为 任意数 : A ^ 0 = A
    * 自己和自己进行异或为0 : A ^ A = 0
    * 满足交换律和结合律 ： A ^ B ^ A = A ^ A ^ B = B
    * 此题先将所有值进行异或，A ^ B ^ A ^ C = B ^ C = mark得到那两个只出现一次数的异或值
    *
    * 根据该值 不能直接将mark分为B C，保留mark最末尾的1，其余位全为0 为new_mark
    * new_mark = mark & (-mark) ,再次进行遍历，num & new_mark, 将该位置为0和1的num分开(可以保证这两个数分在不同的组，且相同的数在同一组)
    * 所以最后 分开的两组 异或结果分别为 B，C
    * */
    public int[] singleNumber(int[] nums) {
        int mark = 0;
        for (int num: nums) {
            mark ^= num;
        }
        int new_mark = mark & (-mark);
        int x = 0;
        for (int num: nums) {
            if((num & new_mark) != 0){
                x ^= num;
            }
        }
        return new int[]{x,x ^ mark};
    }
}
