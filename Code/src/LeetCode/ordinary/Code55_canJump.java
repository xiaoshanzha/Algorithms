package LeetCode.ordinary;

public class Code55_canJump {
    /*
    * 1.如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
    * 2.可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
    * 3.如果可以一直跳到最后，就成功了。
    * */
    public boolean canJump(int[] nums) {
        int rightMost = 0; //记录可以到达的最远距离
        for (int i = 0; i < nums.length; i++) {
            if(i > rightMost){ //当前格子不可到达，直接false
                return false;
            }
            if(rightMost >= nums.length - 1){  //已经可到达最远格子
                return true;
            }
            rightMost = Math.max(rightMost,i + nums[i]); //每次更新可到达的最远格子
        }
        return true;
    }
}
