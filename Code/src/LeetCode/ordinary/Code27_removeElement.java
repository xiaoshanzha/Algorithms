package LeetCode.ordinary;

public class Code27_removeElement {
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            if(nums[left] == val){
                int t = nums[left];
                nums[left] = nums[right];
                nums[right--] = t;
            }else {
                left++;
            }
        }
        return nums[left] == val ? left : left + 1;
    }
}
