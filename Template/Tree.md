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