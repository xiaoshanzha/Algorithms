[TOC]
# 树
## 树的搜索(dfs、bfs)
### 括号生成
![](https://upload-images.jianshu.io/upload_images/10460153-f0a1de8f96022c30.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

解：这类问题是在一颗隐式的树上进行求解，可以用深搜和广搜，一般选择dfs，
原因：1.使用递归，直接借助系统栈完成状态的转移，2.广搜需要自己编写节点类和借助队列。

![](https://upload-images.jianshu.io/upload_images/10460153-3c0fd18911d6491f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-47089741c50a7847.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//深度优先:使用递归（回溯+剪枝）
public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    if(n == 0){
        return res;
    }
    dfs("",n,n,res);
    for (String s : res) {
        System.out.println(s);
    }
    return res;
}

//left 表示左括号剩余数量，right表示右括号剩余数量，s为目前字符串状态
private void dfs(String s, int left, int right, List<String> res) {
    //递归终止条件，终止时将目前字符串添加到结果集
    if(left == 0 && right == 0){
        res.add(s);
        return;
    }
    //产生左分支的条件
    if(left > 0){
        dfs(s+"(",left - 1,right,res);
    }
    //产生右分支的条件
    if(right > 0 && right > left){
        dfs(s+")",left ,right - 1,res);
    }
}
```

```java
/*
* 广度优先搜索：需要自己编写结点类和借助队列
* */
class Node{
    private String s; //当前得到的字符串
    private int left; //剩余左括号的数量
    private int right; //剩余右括号数量

    public Node(String s, int left, int right) {
        this.s = s;
        this.left = left;
        this.right = right;
    }
}

public List<String> generateParenthesis1(int n) {
    List<String> res = new ArrayList<>();
    if(n == 0){
        return res;
    }
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node("",n,n));
    while (!queue.isEmpty()){
        Node curNode = queue.poll();
        if(curNode.left == 0 && curNode.right == 0){
            res.add(curNode.s);
        }
        if(curNode.left > 0){
            queue.offer(new Node(curNode.s + "(", curNode.left - 1, curNode.right));
        }
        if (curNode.right > 0 && curNode.left < curNode.right) {
            queue.offer(new Node(curNode.s + ")", curNode.left, curNode.right - 1));
        }
    }
    return res;
}
```
### 二叉树的右视图
![](https://upload-images.jianshu.io/upload_images/10460153-3c5d0f284798bd01.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//BFS 层序遍历，将每层的最后结点值加入
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if(root == null){
        return list;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    TreeNode node = null;
    while (!queue.isEmpty()){
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            if(i == size - 1){
                list.add(node.val);
            }
        }
    }
    return list;
}
```
```java
//DFS   根右左，保证每层遍历到的首结点为该层的最右节点
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    dfs(res, root, 0);
    return res;
}
private void dfs(List<Integer> res, TreeNode node, int level) {
    if(node != null) {
        //表示 该层还没有节点加入
        if(res.size() == level) {
            res.add(node.val);
        }
        dfs(res, node.right, level + 1);
        dfs(res, node.left, level + 1);
    }
}
```
## 回溯
### 回溯框架
![](https://upload-images.jianshu.io/upload_images/10460153-af43de73f7f97025.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-f598d5a8f9fd6e34.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 回溯：决策树的遍历过程，搞清以下三名词
*       路径：已经做出的选择
*       选择列表：当前可以做的选择
*       结束条件：到达决策树底层，无法再做选择的条件
* 回溯框架核心： for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
* */
result = [];
void backtrack(路径，选择列表){
    if(结束条件){
        result.add(路径);
        return;
    }

    for 选择 in 选择列表{
        做选择；
        backtrack(路径，选择列表);
        撤销选择；
    }
} 
```
### 全排列(无重复数)
![](https://upload-images.jianshu.io/upload_images/10460153-fbee064dea769c5a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/10460153-52eefacd9b3495cf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
List<List<Integer>> res = new LinkedList<>();
public List<List<Integer>> permute(int[] nums) {
    List<Integer> track = new LinkedList<>();

    // visited标志该位置的元素是否已经被选择
    int[] visited = new int[nums.length];

    // nums 为选择列表，track为路径
    backtrack(nums,track,visited);

    return res;
}

private void backtrack(int[] nums, List<Integer> track,int[] visited) {
    //触发结束条件
    if(nums.length == track.size()){
        //注意此处不能写 res.add(track)；
        //因为track这个变量所指向的对象在递归的过程中只有一份，深搜完回到根节点，所以这个变量回到根节点以后都为空
        res.add(new LinkedList<>(track));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if(visited[i] == 1)
            continue;
        visited[i] = 1;
        track.add(nums[i]);
        backtrack(nums,track,visited);
        visited[i] = 0;
        track.remove(track.size() - 1);
    }
}
```
### 全排列(含重复数)
![](https://upload-images.jianshu.io/upload_images/10460153-352dc0d04594d1bf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-cc76e41bd0dae5b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
List<List<Integer>> res = new LinkedList<>();
public List<List<Integer>> permuteUnique(int[] nums) {
    List<Integer> track = new LinkedList<>();
    int[] visited = new int[nums.length];
    //将nums排序是以下剪枝的前提条件(判断下一个访问的位置是否和刚才被撤销选择的数相等)
    Arrays.sort(nums);

    // nums 为选择列表，track为路径
    backtrack(nums,track,visited);
    return res;
}

private void backtrack(int[] nums, List<Integer> track,int[] visited) {
    //触发结束条件
    if(nums.length == track.size()){
        //注意此处不能写 res.add(track)；
        //因为track这个变量所指向的对象在递归的过程中只有一份，深搜完回到根节点，所以这个变量回到根节点以后都为空
        res.add(new LinkedList<>(track));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if(visited[i] == 1 )
            continue;
        //剪枝 : 之前的该数刚刚被撤销选择，所以剪枝（前提条件 : nums数组已排序）
        if(i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0){
            continue;
        }
        visited[i] = 1;
        track.add(nums[i]);
        backtrack(nums,track,visited);
        visited[i] = 0;
        track.remove(track.size() - 1);
    }
}
```
### 组合总和
![](https://upload-images.jianshu.io/upload_images/10460153-6120b6f904257fe2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/10460153-1fd2e9d6739a8c52.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    int cur = 0;
    /*
    * 排序主要的作用是剪枝，让程序在深搜的过程中尽量排除掉不能搜索到结果的分支，以节约时间。
    * 在回溯搜索这种时间复杂度很大的算法中，先排序再剪枝有些时候是有必要的。
    * */
    Arrays.sort(candidates);
    backtrack(res,path,candidates,target,cur,0);

    for (List list : res) {
        System.out.println(list.toString());
    }
    return res;
}
/*
* begin: 本轮搜索的起点下标 , 用来去重
* */
private void backtrack(List<List<Integer>> res, List<Integer> path, int[] candidates, int target,int cur,int begin) {
    if(cur == target){
        res.add(new LinkedList<>(path));
        return;
    }
    for (int i = begin; i < candidates.length; i++) {
        if(cur > target){
            return;
        }
        path.add(candidates[i]);
        backtrack(res,path,candidates,target,cur + candidates[i],i);
        path.remove(path.size() - 1);
    }
}
```
### 组合总和Ⅱ
![](https://upload-images.jianshu.io/upload_images/10460153-60ff6f6d95a24a40.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-79e96934700345ad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    int cur = 0;
    /*
        * 排序主要的作用是剪枝，让程序在深搜的过程中尽量排除掉不能搜索到结果的分支，以节约时间。
        * 在回溯搜索这种时间复杂度很大的算法中，先排序再剪枝有些时候是有必要的。
        * */
    Arrays.sort(candidates);
    backtrack(res,path,candidates,target,cur,0);

    for (List list : res) {
        System.out.println(list.toString());
    }
    return res;
}

/*
* begin: 本轮搜索的起点下标 , 用来去重
* */
private void backtrack(List<List<Integer>> res, List<Integer> path, int[] candidates, int target,int cur,int begin) {
    System.out.println(path.toString());
    if(cur == target){
        res.add(new LinkedList<>(path));
        return;
    }
    for (int i = begin; i < candidates.length; i++) {
        //大剪枝
        if(cur > target){
            return;
        }
        //小剪枝(此处为重点)
        if(i > begin && candidates[i] == candidates[i-1]){
            continue;
        }
        path.add(candidates[i]);
        backtrack(res,path,candidates,target,cur + candidates[i],i+1);
        path.remove(path.size() - 1);
    }
}
```
## 树形DP
### 树形DP前提和套路
```
使用前提：
    如果题目求解的目标是S规则，则求解流程可以定成以每个节点为头结点的子树在S规则下的每一个答案，并且最终答案一定在其中。

套路：
    1.以某个节点X为头结点的子树中，分析答案的可能性，
      这种分析是以X的左子树，X的右子树和X整棵树的角度来考虑可能性的。
    2.根据第一步的可能性分析，列出所有需要的信息。
    3.合并第二步的信息，对左树和右树提出同样的要求，并写出信息结构。
    4.设计递归函数，递归函数是处理以X为头结点的情况下的答案。
      包括设计递归的base case，默认直接得到左树和右树的所有信息，以及把可能性做整合，并且要返回第三步的信息结构。
```
### 最大搜索二叉子树
```
搜索二叉树：节点左子树上所有节点值均小于该节点
           节点右子树上所有节点值均大于该节点
```
![](https://upload-images.jianshu.io/upload_images/10460153-cdfe2ff3c5f56ea6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 可能性分析：以X为头结点的子树
* 1.最大的BST，是左子树的最大BST
* 2.最大的BST，是右子树的最大BST
* 3.如果X左子树的最大BST是左子树全体，右子树的最大BST是右子树全体，
*   并且X值大于左子树所有节点值，小于右子树所有节点值，则最大BST是X为头结点的树。
* */
public class ReturnType{
    public Node maxBSThead;
    public int maxBSTSize;
    public int min;
    public int max;

    public ReturnType(Node maxBSThead, int maxBSTSize, int min, int max) {
        this.maxBSThead = maxBSThead;
        this.maxBSTSize = maxBSTSize;
        this.min = min;
        this.max = max;
    }
}
public ReturnType process(Node x){
    //base case:如果子树是空树，则最大值为系统最小，最小值为系统最大
    if(x == null){
        return new ReturnType(null,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
    }

    //默认直接得到左右子树的信息
    ReturnType leftData = process(x.left);
    ReturnType rightData = process(x.right);

    //信息整合
    int min = Math.min(x.value,Math.min(leftData.min,rightData.min));
    int max = Math.max(x.value,Math.max(leftData.max,rightData.max));

    //如果只考虑可能性一二
    int maxBSTSize = Math.max(leftData.maxBSTSize,rightData.maxBSTSize);
    Node maxBSTHead = leftData.maxBSTSize >= rightData.maxBSTSize ? leftData.maxBSThead : rightData.maxBSThead;

    //判断可能性三
    if(leftData.maxBSThead == x.left && rightData.maxBSThead == x.right
        && x.value > leftData.max && x.value < rightData.min){
        maxBSTHead = x;
        maxBSTSize = leftData.maxBSTSize + rightData.maxBSTSize + 1;
    }
    return new ReturnType(maxBSTHead,maxBSTSize,min,max);
}
```
### 判断是否是平衡二叉树
![](https://upload-images.jianshu.io/upload_images/10460153-f6f1d2f5c69ebed2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public class ReturnData{
    private boolean isB;
    private int h;

    public ReturnData(boolean isB, int h) {
        this.isB = isB;
        this.h = h;
    }
}
public boolean isBalanced(TreeNode root) {
    return process(root).isB;
}
public ReturnData process(TreeNode node){
    if(node == null){
        return new ReturnData(true,0);
    }
    ReturnData leftData = process(node.left);
    if(!leftData.isB){
        return new ReturnData(false,0);
    }
    ReturnData rightData = process(node.right);
    if(!rightData.isB){
        return new ReturnData(false,0);
    }
    if(Math.abs(leftData.h - rightData.h) > 1){
        return new ReturnData(false,0);
    }
    int max_h = Math.max(leftData.h,rightData.h);
    return new ReturnData(true,max_h + 1);
}
```

### 二叉树节点间的最大距离问题
![](https://upload-images.jianshu.io/upload_images/10460153-4cd4b9b27da4b03a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 可能性分析：
* 1.最大距离可能是左子树上的最大距离
* 2.最大距离可能是右子树上的最大距离
* 3.最大距离可能是左子树高度 + 右子树高度 + 1
* */
 public class ReturnType{
    public int maxDistance;
    public int height;

    public ReturnType(int maxDistance, int height) {
        this.maxDistance = maxDistance;
        this.height = height;
    }
}

public ReturnType process(Node head){
    if(head == null){
        return new ReturnType(0,0);
    }
    ReturnType leftData = process(head.left);
    ReturnType rightData = process(head.right);
    int height = Math.max(leftData.height,rightData.height) + 1;
    int maxDistance = Math.max(leftData.height + rightData.height + 1
            ,Math.max(leftData.maxDistance,rightData.maxDistance));
    return new ReturnType(maxDistance,height);
}
```

### 二叉树中的最大路径和
![](https://upload-images.jianshu.io/upload_images/10460153-fe35af04eb0fc96a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 树形dp：
* 可能性：对于以x为头结点的树
*       1.最大路径和是该节点左树上的最大路径和
*       2.最大路径和是右树上的最大路径和
*       3.最大路径和是该节点值
*       4.最大路径和是该节点值 + 包含x左孩子节点的最大路径
*       5.最大路径和是该节点值 + 包含x右孩子节点的最大路径
*       6.最大路径和是该节点值 + 包含x左、右孩子节点的最大路径
*
* 所以定义返回值类型时 需要包含的信息： 以该节点为根节点该树上的最大路径和， 包括该节点的最大路径和
* */
public class ReturnData{
    private int maxsum; //该节点为根节点该树上的最大路径和
    private int sum;  //包括该节点的最大路径和
    public ReturnData(int maxsum, int sum) {
        this.maxsum = maxsum;
        this.sum = sum;
    }
}
public ReturnData process(TreeNode node){
    if(node == null){
        //因为可能出现负数，所以不能为0；
        return new ReturnData(Integer.MIN_VALUE,Integer.MIN_VALUE);
    }
    ReturnData left = process(node.left);
    ReturnData right = process(node.right);
    int maxsum = 0;
    int sum = 0;

    // 后面计算sum、maxsum时，会出现系统最小值和负数相加溢出的情况
    if(left.sum == Integer.MIN_VALUE && right.sum == Integer.MIN_VALUE){
        maxsum = node.val;
        sum = node.val;
    }else {
        if(left.sum == Integer.MIN_VALUE){
            if(node.val < 0){
                //保证不会影响后面的计算，而且不会溢出
                left.sum = node.val ;
            }else {
                left.sum = 0;
            }
        }
        if(right.sum == Integer.MIN_VALUE){
            if(node.val < 0){
                right.sum = node.val;
            }else {
                right.sum = 0;
            }
        }
        //包含该节点的最大路径和可能 只包含该节点
        sum = Math.max(Math.max(left.sum,right.sum) + node.val,node.val);
        maxsum = Math.max(Math.max(Math.max(Math.max
                        (left.maxsum,
                                right.maxsum),
                left.sum + right.sum +node.val),
                sum),
                node.val);
    }
    return new ReturnData(maxsum,sum);
}
public int maxPathSum(TreeNode root) {
    if(root == null){
        return 0;
    }
    return process(root).maxsum;
}
```