package LeetCode.ordinary;

import java.util.Arrays;

public class Code45_jump {
    public static int jump(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        int[] least = new int[nums.length];
        Arrays.fill(least,nums.length);
        int rightMost = 0;
        least[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            rightMost = Math.max(rightMost,i + nums[i]);
            for (int j = i + 1; j <= rightMost; j++) {
                least[j] = Math.min(least[j],least[i] + 1);
                if(least[nums.length - 1] != nums.length){
                    return least[nums.length - 1];
                }
            }
        }
        return least[nums.length - 1];
    }

    public static int jump1(int[] nums) {
        int ans = 0; //记录跳跃的次数
        int end = 0;
        int rightMost = 0; //新的跳跃范围内能跳到的最远距离
        for (int i = 0; i < nums.length - 1; i++) {
            rightMost = Math.max(nums[i] + i ,rightMost);

            // 上个范围跳完之后，更新新的跳跃范围和跳跃次数
            if(i == end){
                end = rightMost;
                ans++;
            }

            if(end >= nums.length - 1){
                return ans;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2};
        System.out.println(jump1(arr));
    }
}
