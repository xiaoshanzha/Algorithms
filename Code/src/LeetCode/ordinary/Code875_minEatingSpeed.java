package LeetCode.ordinary;

public class Code875_minEatingSpeed {
    //暴力解决：根据速度一个个试
    public static int minEatingSpeed(int[] piles, int H) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max,piles[i]);
        }
        //每小时吃香蕉的速度最慢为1，最快为max
        for (int speed = 1; speed <= max; speed++) {
            // 以speed速度是否能在H小时内吃完香蕉
            if(canFinish(piles,speed,H)){
                return speed;
            }
        }
        return max;
    }

    // 根据以上代码for循环能看出可以用搜索左侧边界的二分查找实现
    public static int minEatingSpeedBy_erFen(int[] piles, int H) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max,piles[i]);
        }
        int left = 1;
        int right = max + 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(canFinish(piles,mid,H)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
    private static boolean canFinish(int[] piles, int speed, int h) {
        long time = 0;
        for (int i : piles) {
            //以速度speed吃完每堆需要的时间
            int i_times = (i / speed) + ((i % speed > 0) ? 1 : 0);
            time += i_times;
        }
        return time <= h;
    }

    public static void main(String[] args) {
        int[] arr = {332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
        int h = 823855818;
    }
}