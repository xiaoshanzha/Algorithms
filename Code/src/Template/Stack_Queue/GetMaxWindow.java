package Template.Stack_Queue;

import java.util.LinkedList;

/*
* 利用双端队列(LinkedList)，首和尾均可进出，pollLast(),addLast(),pollFirst(),addFirst()
* 解决窗口问题
* */
public class GetMaxWindow {
    /*
    * 生成窗口最大值数组：数组nums，窗口大小为k，返回所有大小为k的窗口最大值组成的数组
    *
    * 思路：双端队列中存放数组值的索引，从前往后为数组值大到小； 遍历到arr[i]
    *       放入规则：1.如果队列为空，直接放
    *                2.如果不为空，取出当前队尾存放的下标，设为j
    *           如果arr[j]>arr[i],直接将i放入队尾，放入结束(将i存入原因：i比j晚过期，有可能成为窗口最大值)
    *           如果arr[j]<=arr[i],将j弹出，重复放入规则(j弹出原因：i比j晚过期，j不可能成为窗口最大值，已无效)
    *       弹出规则：队头下标等于i-k后，说明当前队头下标已过期，弹出队头下标
    *
    * 测试：LeetCode/ordinary/Code239_GetMaxWindow
    * */
    public static  int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k < 1|| k > nums.length){
            return new int[0];
        }
        LinkedList<Integer> window = new LinkedList<>();
        int index = 0;
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //给双端队列添加元素时，先将无效的值移出去
            while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]){
                window.pollLast();
            }
            window.addLast(i);

            //检查队列首元素所在窗口是否过期
            if(window.peekFirst() == i - k){
                window.pollFirst();
            }
            //遍历位置大于一个窗口后，每次都给res添加元素
            if(i >= k - 1){
                res[index++] = nums[window.peekFirst()];
            }
        }
        return res;
    }
    /*
    * 最大值减最小值小于等于num的子数组数量
    *
    * 思路：子数组类似于窗口问题，用两个双端队列，一个存大到小，一个存小到大
    *       窗口左边界先不动，改变右边界，直到不满足后，此时以左边界开头的子数组数量将全部得到
    *       然后左边界依次右移，
    *
    * 测试：NowCoder/GetNumByMaxMin
    * */
    public static int getNum(int[] arr,int num){
        int res = 0;
        int i = 0;
        int j = 0;
        //数组值从小到大
        LinkedList<Integer> window_min = new LinkedList<>();
        //数组值从大到小，
        LinkedList<Integer> window_max = new LinkedList<>();
        for ( i = 0; i < arr.length; i++) {
            //注意j取值，保持上次的右边界
            while (j < arr.length){
                //从后弹出无效索引 再进
                while(!window_min.isEmpty() && arr[window_min.peekLast()] >= arr[j]){
                    window_min.pollLast();
                }
                window_min.addLast(j);
                while(!window_max.isEmpty() && arr[window_max.peekLast()] <= arr[j]){
                    window_max.pollLast();
                }
                window_max.addLast(j);

                if(arr[window_max.peekFirst()] - arr[window_min.peekFirst()] > num){
                    break;
                }
                j++;
            }
            res += j - i;
            //更新结构看下标是否过期
            if(window_min.peekFirst() == i ){
                window_min.pollFirst();
            }
            if(window_max.peekFirst() == i){
                window_max.pollFirst();
            }
        }
        return res;
    }

}
