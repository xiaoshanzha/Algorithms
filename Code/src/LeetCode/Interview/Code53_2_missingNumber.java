package LeetCode.Interview;

public class Code53_2_missingNumber {
    public static int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length;
        while(left < right){
            int mid = ( left + right ) / 2;
            if(nums[mid] == mid){
                left = mid + 1;
            }else{
                right = mid;
            }
            System.out.println(mid + " : "+ left + " : "+ right);
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,9};
        System.out.println(missingNumber(arr));
    }
}
