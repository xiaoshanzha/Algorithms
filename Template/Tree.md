[TOC]
# 树
## 二叉树框架
```java
//明确一个节点要做的事，剩下扔给框架
void traverse(TreeNode root){
    // root节点需要做什么，在这里做，剩下不用root操心
    traverse(root.left);
    traverse(root.right);
}

// eg：二叉树所有节点值加一
void plusOne(TreeNode root){
    if(root == null){
        return;
    }
    root.val += 1;
    plusOne(root.left);
    plusOne(root.right);
}
```
### 判断两棵树是否相同
```java
public boolean isSame(TreeNode a, TreeNode b){
    // 都为空显然相同
    if(a == null && b == null){
        return true;
    }
    //一个空，一个非空，显然不同
    if(a == null || b == null){
        return false;
    }
    //两个非空，但val不同也不可
    if(a.val != b.val){
        return false;
    }
    // a节点和b节点比较完，开始比较两个节点的左子树和右子树
    return isSame(a.left,b.left) && isSame(a.right,b.right);
}
```
### 判断一个树是不是另一个树的子树
![](https://upload-images.jianshu.io/upload_images/10460153-ca00748289ea5b0e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//递归
public boolean isSubtree(TreeNode s, TreeNode t) {
    if(t == null){
        return true;
    }
    if(s == null){
        return false; //此处t不为null，只要s为null，肯定是false
    }
    //用isSame()判断两树是否相同
    return isSame(s,t) || isSubtree(s.left,t) || isSubtree(s.right,t);
}
```
## 前中后序遍历
### 递归
```java
/*
* 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式
* 先序：根左右   中序：左根右   后序：左右根
* eg:        1
*          2   3
*         4 5 6 7
* 递归：实际访问上树节点顺序：先访问根节点，再访问左子树，若无左子树，回到根节点，访问右子树，若无右，回到根。
*       1，2，4，4，4，2，5，5，5，2，1，3，6，6，6，3，7，7，7，3，1
*       每个节点都会被访问3次，如果将打印的动作放在第一次访问该节点时，为先序，第二次为中序，第三次为后序
*/
private static void preOrderRecur(Node head){
    if(head == null){
        return;
    }
    System.out.print(head.value + " ");
    preOrderRecur(head.left);
    preOrderRecur(head.right);
}
private static void InOrderRecur(Node head){
    if(head == null){
        return;
    }
    InOrderRecur(head.left);
    System.out.print(head.value + " ");
    InOrderRecur(head.right);
}
private static void PosOrderRecur(Node head){
    if(head == null){
        return;
    }
    PosOrderRecur(head.left);
    PosOrderRecur(head.right);
    System.out.print(head.value + " ");
}
```
### 非递归
```java
/* 非递归：先序：1.申请一个新栈，将头结点head压入栈中
*               2.从栈中弹出栈顶节点，打印该节点值，再将节点的右孩子(不为空的话)压入栈中，
*                 最后将左孩子(不为空的话)压入栈中。
*               3.重复2过程，直到栈为空。
*         中序：1.申请一个栈，初始时，令cur = head；
*               2.先把cur节点压入栈中，对以cur节点为头的整棵树来说，依次把左边界压入栈中，
*                 即cur = cur.left,依次重复该步骤，直到发现cur为空；
*               3.此时从栈中弹出一个节点，打印该节点，(保证每次取出的都是未打印部分最左部分的数)
*                 并让cur = cur.right，重复步骤2；
*               4.当栈为空时，结束
*         后序：和先序思路一样，先序要求：根左右， 后序要求：左右根
*               先序进栈时，先压右 再压左。 如果先压左再压右，即可实现根右左。
*               然后将前序打印的时机换为进入一个新栈，最后打印新栈的节点
* */
private static void PreOrderUnRecur(Node head){
    System.out.print("pre-order: ");
    if(head == null){
        return;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(head);
    Node cur = null;
    while(!stack.isEmpty()){
        cur = stack.pop();
        System.out.print(cur.value + " ");
        if(cur.right != null){
            stack.push(cur.right);
        }
        if(cur.left != null){
            stack.push(cur.left);
        }
    }
    System.out.println();
}

private static void InOrderUnRecur(Node head){
    System.out.print("In-order: ");
    if(head == null){
        return;
    }
    Stack<Node> stack = new Stack<>();
    Node cur = head;
    Node node = null;
    while(!stack.isEmpty() || cur != null){
        if(cur != null){
            stack.push(cur);
            cur = cur.left;
        }else {
            node = stack.pop();
            System.out.print(node.value + " ");
            cur = node.right;
        }
    }
    System.out.println();
}
private static void PosOrderUnRecur(Node head){
    System.out.print("Pos-order: ");
    if(head == null){
        return;
    }
    Stack<Node> stack = new Stack<>();
    Stack<Node> new_stack = new Stack<>();
    stack.push(head);
    Node cur = null;
    while(!stack.isEmpty()){
        cur = stack.pop();
        new_stack.push(cur);
        if(cur.left != null){
            stack.push(cur.left);
        }
        if(cur.right != null){
            stack.push(cur.right);
        }
    }
    while (!new_stack.isEmpty()){
        System.out.print(new_stack.pop().value + " ");
    }
    System.out.println();
}
```
## BST
### 判断二叉搜索树
```java
//root需要和整个左子树和整个右子树所有节点进行比较
boolean isValidBST(TreeNode root){
    //通过增加函数参数列表，在参数中携带额外的信息
    return isValidBST(root,null,null);
}

boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
    if(root == null){
        return true;
    }
    if(min != null && root.val <= min.val){
        return false;
    }
    if(max != null && root.val >= max.val){
        return false;
    }
    return isValidBST(root.left,min,root) && isValidBST(root.right,root,max);
}
```
### BST中查找一个数是否存在
```java
//不需要递归的搜索两边，利用左小右大特性排除一边
boolean isInBST(TreeNode root,int target){
    if(root == null){
        return false;
    }
    if(root.val == target){
        return true;
    }
    if(root.val < target){
        return isInBST(root.right,target);
    }
    return isInBST(root.left,target);
}
```
### BST中插入一个数
```java
//涉及到“改”，函数就要返回TreeNode类型，并且对递归调用的返回值进行接收
TreeNode insertIntoBST(TreeNode root,int var){
    if(root == null){
        return new TreeNode(var);
    }
    /*if(root.val == var){
        //BST一般不会插入已存在的元素
    }*/
    if(root.val < var){
        root.right = insertIntoBST(root.right,var);
    }
    if(root.val > var){
        root.left = insertIntoBST(root.left,var);
    }
    return root;
}
```
### BST中删除一个数
```java
//在BST中删除一个数
TreeNode deleteNode(TreeNode root,int key){
    if(root.val == key){
        /*
        * 找到了该值进行删除(共三种情况)
        * 1.恰好是末端节点，两个子节点都为空，直接删除
        * 2.只有一个非空子节点，让这个子节点接替自己的位置
        * 3.有两个非空子节点，找出左子树最大的节点或者右子树中最小的节点接替自己的位置
        * */
        if(root.left == null){
            return root.right;
        }
        if(root.right == null){
            return root.left;
        }
        //统一找出右子树最小节点,
        //将要删除节点的值变为右子树最小值，然后删除右子树最小值
        TreeNode minNode = getMin(root.right);
        root.val = minNode.val;
        root.right = deleteNode(root.right,minNode.val);
    }else if(root.val > key){
        root.left = deleteNode(root.left,key);
    }else if(root.val < key){
        root.right = deleteNode(root.right,key);
    }
    return root;
}
TreeNode getMin(TreeNode node){
    //BST最左边的值就是最小值
    while(node.left != null){
        node = node.left;
    }
    return node;
}
```
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