package LeetCode.Interview;

public class Code56_1_singleNumbers {
    public int[] singleNumbers(int[] nums) {
        int mark = 0;
        for (int i = 0; i < nums.length; i++) {
            mark ^= nums[i];
        }
        int new_mark = mark & (-mark);
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            if((new_mark & nums[i]) != 0){
                value ^= nums[i];
            }
        }
        return new int[]{value,value^mark};
    }
}
