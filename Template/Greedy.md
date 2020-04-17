## 贪心算法
### 1.跳跃游戏(是否可到达最后位置)
![](https://upload-images.jianshu.io/upload_images/10460153-2f2ad82c924b8e0b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
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
```
### 2.跳跃游戏Ⅱ(到终点的最少跳跃次数)
![](https://upload-images.jianshu.io/upload_images/10460153-aa44954f0df80566.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int jump1(int[] nums) {
    int ans = 0; //记录跳跃的次数
    int end = 0;
    int rightMost = 0; //新的跳跃范围内能跳到的最远距离
    for (int i = 0; i < nums.length - 1; i++) {
        rightMost = Math.max(nums[i] + i ,rightMost);

        // 上个范围跳完之后，更新新的跳跃范围和跳跃次数
        if(i == end){
            end = rightMost;
            ans++;
        }

        if(end >= nums.length - 1){
            return ans;
        }
    }
    return ans;
}
```
![](https://upload-images.jianshu.io/upload_images/10460153-fccbc592194cc3b2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)