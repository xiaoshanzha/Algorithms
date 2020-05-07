package LeetCode.ordinary;

public class Code42_Rain {
    public static int trap(int[] height) {
        int[] leftmax = new int[height.length];
        int[] rightmax = new int[height.length];
        int[] final_height = new int[height.length];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += height[i];
            leftmax[i] = max;
            max = Math.max(max,height[i]);
        }
        max = 0;
        int final_sum = 0;
        for (int i = height.length - 1; i >= 0 ; i--) {
            rightmax[i] = max;
            int min = Math.min(rightmax[i],leftmax[i]);
            final_height[i] = min > height[i] ? min : height[i];
            final_sum += final_height[i];
            max = Math.max(max,height[i]);
        }
        return final_sum - sum;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(arr));
    }
}
