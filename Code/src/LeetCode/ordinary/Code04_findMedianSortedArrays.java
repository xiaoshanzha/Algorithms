package LeetCode.ordinary;

public class Code04_findMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = 0 , len2 = 0 , len = 0;
        if(nums1 != null){
            len1 = nums1.length;
        }
        if(nums2 != null){
            len2 = nums2.length;
        }
        len = len1 + len2;
        int[] nums = new int[len];
        if(nums1 == null || len1 == 0){
            nums = nums2;
        }else if(nums2 == null || len2 == 0){
            nums = nums1;
        }else {
            int i = 0 , j = 0 , k = 0;
            while (i < len1 && j < len2){
                if(nums1[i] <= nums2[j]){
                    nums[k++] = nums1[i++];
                }else {
                    nums[k++] = nums2[j++];
                }
            }
            if(i == len1){
                while (j < len2){
                    nums[k++] = nums2[j++];
                }
            }else {
                while (i < len1){
                    nums[k++] = nums1[i++];
                }
            }
        }
        if(len % 2 == 0){
            double d1 = nums[len / 2 - 1];
            double d2 = nums[len / 2];
            return (d1 + d2) / 2;
        }
        return nums[len / 2] ;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2};
        int[] arr2 = new int[]{3,4};
        System.out.println(findMedianSortedArrays(arr1,arr2));
    }
}
