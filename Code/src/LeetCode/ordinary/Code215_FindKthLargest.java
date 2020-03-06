package LeetCode.ordinary;

import java.util.PriorityQueue;

public class Code215_FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        //建立小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int i = 0;
        for ( i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        while (i < nums.length){
            if(queue.peek() < nums[i]){
                queue.poll();
                queue.add(nums[i]);
            }
            i++;
        }
        return queue.peek();
    }
}
