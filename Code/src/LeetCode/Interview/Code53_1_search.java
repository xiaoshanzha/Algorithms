package LeetCode.Interview;

public class Code53_1_search {
    public static int search(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }
        //先寻找左侧边界
        int left = 0;
        int right = nums.length;
        //寻找的范围是[left,right) ,所以是 <
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                right = mid;  //缩小搜索边界的上界right 在[left,mid)中继续找，不断向左收缩
            }else if(nums[mid] > target){
                right = mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }

        int left_index;
        //target比所有数都大,不存在 该数
        if(left == nums.length){
            left_index = -1;
            return 0;
        }
        left_index = nums[left] == target ? left : -1;
        if(left_index == -1){
            return 0;
        }
        //寻找右侧边界
        left = 0;
        right =  nums.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                left = mid + 1;  //mid = left - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid;
            }
        }
        int right_index ;
        if(left == 0){
            right_index = -1;
        }
        /*
         * 因为我们对	left的更新必须是	left=mid+1，就是说while循环结束时，
         * nums[left]⼀定不等于target了，⽽nums[left-1]可能是target。
         * */
        right_index = nums[left - 1] == target ? (left - 1) : -1;
        System.out.println(left_index + " "+right_index);
        return (right_index - left_index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {5,7,7,8,8,10};
        System.out.println(search(arr,6));
    }
}
