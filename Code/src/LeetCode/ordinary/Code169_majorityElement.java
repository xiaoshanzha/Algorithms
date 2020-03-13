package LeetCode.ordinary;

import java.util.HashMap;

public class Code169_majorityElement {
    public static int majorityElement(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        int mid = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                int new_nums = map.get(nums[i]) + 1;
                if(new_nums > mid){
                    return nums[i];
                }
                map.put(nums[i],new_nums);
            }else {
                map.put(nums[i],1);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {2};
        System.out.println(majorityElement(arr));
    }
}
