package LeetCode.ordinary;

public class Code11_maxArea {
    public static int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int Area = 0;
        while (start < end){
            Area = Math.max(Area,Math.min(height[start],height[end]) * (end - start));
            if(Math.min(height[start],height[end]) == height[start]){
                start++;
            }else {
                end--;
            }
        }
        return Area;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
