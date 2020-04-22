## 栈和队列问题总结
[toc]
### 1.栈实现队列，队列实现栈
```java
public class StackAndQueueConvert {
    /*
     * 用两个队列实现栈：data队列和help队列，进栈时，只进data队列。
     *                 出栈时将data队列中的数据留一个，剩下的数据倒在help队列里，
     *                 然后help和data队列互换引用，保持data队列存放数据，help队列为空，作为辅助队列
     *
     * 用两个栈实现队列：push栈和pop栈，进队列时只进push栈，出队列时只从pop栈出
     *                 push栈给pop栈倒东西时，需要一次倒完；
     *                 pop栈有东西时，push栈一定不要倒。不然会发生混乱
     *                 保证pop栈的数据是最先进入push栈的，pop栈的数据用完之后，push栈再将自己的数据全部倒进pop栈
     *
     * 队列是push()和poll();
     * 栈是push()和pop();
     * */
    private static class TwoQueuesStack{
        private Queue<Integer> data;
        private Queue<Integer> help;
        public TwoQueuesStack(){
            data = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }
        public void push(int pushInt){
            data.add(pushInt);
        }
        public int peek(){
            if(data.isEmpty()){
                throw new RuntimeException("Stack is empty");
            }
            while (data.size() != 1){
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        public int pop(){
            if(data.isEmpty()){
                throw new RuntimeException("Stack is empty");
            }
            while (data.size() != 1){
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> tmp = help;
            help = data;
            data = tmp;
        }
    }
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;
        public TwoStacksQueue(){
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }
        public void push(int pushInt){
            stackPush.push(pushInt);
        }
        public int poll(){
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty");
            }else if(stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }
        public int peek(){
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty");
            }else if(stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }
}
```
### 2.双端队列(窗口问题最值问题)
#### 滑动窗口最大值
![](https://upload-images.jianshu.io/upload_images/10460153-4bb6448de93438b0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
  /*
    * 思路：双端队列中存放数组值的索引，从前往后为数组值大到小； 遍历到arr[i]
    *       放入规则：1.如果队列为空，直接放
    *                2.如果不为空，取出当前队尾存放的下标，设为j
    *           如果arr[j]>arr[i],直接将i放入队尾，放入结束(将i存入原因：i比j晚过期，有可能成为窗口最大值)
    *           如果arr[j]<=arr[i],将j弹出，重复放入规则(j弹出原因：i比j晚过期，j不可能成为窗口最大值，已无效)
    *       弹出规则：队头下标等于i-k后，说明当前队头下标已过期，弹出队头下标
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
```
#### 最大值减最小值小于等于num的子数组数量
```java
 /*
    * 思路：子数组类似于窗口问题，用两个双端队列，一个存大到小，一个存小到大
    *       窗口左边界先不动，改变右边界，直到不满足后，此时以左边界开头的子数组数量将全部得到
    *       然后左边界依次右移，
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

```
### 3.单调栈结构
```java
public class Monotonic_Stack_Structure {
    /*
    * 问题：给定一个不含重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
    *
    * 思路：找最近且比arr[i]小的位置，栈结构从栈顶到栈底应该严格递减
    *       找最近且比arr[i]大的位置，栈结构从栈顶到栈底应该严格递增
    * 以找最近且比arr[i]小的位置为例：
    *       进栈时：若该值大于栈顶位置所代表的值，则直接放入，
    *              若小于，则弹出栈顶位置，继续进行比较，
    *       i出栈时：因为j值<i值，所以i右边最近且小的值为j
    *               左边最近且小的值为自己在栈中下面相邻的值。
    *       遍历结束清算栈中元素 ：因为是清算才弹出，所有右边没有比自己小的值，为-1
    *               栈中最后一个值没有下面相邻的值所以左边也没有比自己小的值，为-1
    * */
    public int[][] getNearLessNoRepeat(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            //先将栈中比arr[i]大的值弹出
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                int popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        //清算栈中元素
        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][1] = -1;
        }
        return res;
    }

    /*
    * 如果数组有重复值:改变栈结构，直接将值相同的索引压在一起，
    * */
    public int[][] getNearLess(int[] arr){
        Stack<List<Integer>> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> list = stack.pop();
                //此时左边最近且小的索引应为 栈顶list中最后进的值索引
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek()
                        .get(stack.peek().size() - 1);
                for (Integer popi : list) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            // 进栈时应该区分是否有相同的值
            if(!stack.isEmpty() && arr[i] == stack.peek().get(0)){
                stack.peek().add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //清算栈元素
        while (!stack.isEmpty()){
            List<Integer> list = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek()
                    .get(stack.peek().size() - 1);
            for (Integer popi : list) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }
}
```
#### 最大矩形
![](https://upload-images.jianshu.io/upload_images/10460153-a3dfd86b8ce625ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public int maximalRectangle(char[][] matrix) {
    if(matrix == null || ( matrix.length == 0)){
        return 0;
    }
    int maxArea = 0;
    //height[i] 表示以第i行为底的情况下，每个位置往上连续是1的数量
    int[] height = new int[matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
        }
        //以i行为底，将以上部分看作一个大的直方图，求最大矩形面积
        maxArea = Math.max(largestRectangleArea(height),maxArea);
    }
    return maxArea;
}

//单调栈结构，找出每根柱子向左和向右能扩到的位置
//实质就是找柱子左边和右边离他最近且小的位置，计算面积
public int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i < heights.length; i++) {
        while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
            int j = stack.pop();
            //l 表示 j柱子左边最近且小的柱子位置  ,此时i表示右边且近
            int l = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (i - l -1) * heights[j];
            maxArea = Math.max(maxArea,curArea);
        }
        stack.push(i);
    }
    while(!stack.isEmpty()){
        int j = stack.pop();
        int l = stack.isEmpty() ? -1 : stack.peek();
        int curArea = (heights.length - l -1) * heights[j];
        maxArea = Math.max(maxArea,curArea);
    }
    return maxArea;
}
```

### 4.判断括号合法性
```java
/*
* 处理一种括号：假设字符串中只有圆括号
* 括号合法原则：每个右括号 ) 的左边必须有⼀个左括号 ( 和它匹配。
* */
boolean isValid(String str){
    //待匹配的左括号数量
    int left = 0;
    for (char c : str.toCharArray()) {
        if(c == '('){
            left++;
        }else{
            left--;
        }
        
        if(left < 0) {
            return false;
        }
    }
    return left == 0;
}

/*
* 处理多种括号：
* 遇到左括号就入栈，遇到右括号就去栈中寻找最近的左括号，看是否匹配
* */
boolean isValid1(String str){
    Stack<Character> left = new Stack<>();
    for (char c : str.toCharArray()) {
        if(c == '(' || c == '[' || c == '{'){
            left.push(c);
        }else {
            if(!left.isEmpty() && leftOf(c) == left.peek()){
                left.pop();
            }else {
                return false;
            }
        }
    }
    //检查是否所有的左括号都被匹配了
    return left.isEmpty();
}

    char leftOf(char c) {
    if(c == '('){
        return ')';
    }
    if(c == '['){
        return ']';
    }
    return '{';
}
```