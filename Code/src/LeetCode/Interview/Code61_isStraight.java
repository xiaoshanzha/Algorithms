package LeetCode.Interview;

public class Code61_isStraight {
    public static boolean isStraight(int[] nums) {
        int[] times = new int[14];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0 && times[nums[i]] != 0){
                return false;
            }else if(nums[i] == 0){
                times[nums[i]] ++;
            }else {
                times[nums[i]] ++;
                max = Math.max(nums[i],max);
                min = Math.min(nums[i],min);
            }
        }
        if(((max - min) <= 4 ) || times[0] == 5){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,0,0,0};
        System.out.println(isStraight(arr));
    }
}
