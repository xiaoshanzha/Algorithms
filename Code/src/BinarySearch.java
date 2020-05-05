public class BinarySearch {
    /*
    * 二分查找：不要出现else,而是把else if写清楚，计算mid时要防止溢出
    * */

    /*
    * 寻找一个数的左边界:
    * 存在返回最左边界的索引，不存在返回-1
    *
    * 搜索区间[left,right)左闭右开，
    * 所以while(left < right),并且left = mid + 1,right = mid;
    *
    * 因为要找最左侧边界，所以相等时不立即返回，而是收紧右边界
    * */
    private static int left_bound(int[] nums,int target){
        if(nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length;

        while (left < right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                right = mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid;
            }
        }
        //target比所有数都大
        if(left == nums.length){
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    /*
     * 寻找一个数的右边界:
     * 存在返回最右边界的索引，不存在返回-1
     *
     * 跟找左边界不同的是，相同时收紧左边界left = mid + 1；
     * 所以无论返回left还是right，必须减一
     * */
    private static int right_bound(int[] nums,int target){
        if(nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length;

        while (left < right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                left = mid + 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid;
            }
        }
        if(left == 0){
            return -1;
        }
        return nums[left - 1] == target ? ( left - 1 ) : -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,3,3,4,5};
        System.out.println(left_bound(arr,2));
        System.out.println(left_bound(arr,3));
        System.out.println(left_bound(arr,6));
        System.out.println(right_bound(arr,2));
        System.out.println(right_bound(arr,3));
        System.out.println(right_bound(arr,6));
    }
}
