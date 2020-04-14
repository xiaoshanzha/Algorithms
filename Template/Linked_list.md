[TOC]
## 链表问题总结

单链表节点
```java
public class Node{
        private int value;
        private Node next;
        public Node(int data){
            this.value = data;
        }
    }
```
双向链表节点
```
public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }
```

### 1.链表反转(全部反转和部分反转)
```
迭代解决：时间复杂度O(N),空间复杂度O(1)
递归解决：时间复杂度O(N),空间复杂度O(n)，递归将会使用隐式栈空间。递归深度可能会达到 n 层。
```
```java
public class ReverseList {
    /*
    * 单向：A -> B  变成  B -> A  即可。
    *
    * 迭代思路：遍历，将当前节点的下一个节点缓存后更改当前节点指针
    *
    * 递归实现：我子节点下的所有节点都已经反转好了，
    *          现在就剩我和我的子节点 没有完成最后的反转了，所以反转一下我和我的子节点。
    * eg:1->2->3->4->5
    * reverseListByRec(5): p:5->null
    * reverseListByRec(4): p:5->4->null
    * reverseListByRec(3): p:5->4->3->null
    * */
    public static Node reverseList(Node head){
        Node pre = null;  //上一结点
        Node tmp = null;  //临时结点，用于保存当前节点的下一结点
        Node cur = head;
        while (cur != null) {
            //反转指针域的指向
            tmp = cur.next;
            cur.next = pre;

            //指针往下进行移动
            pre = cur;
            cur = tmp;
        }
        head.next = null;
        return pre;
    }
    //递归
    public static Node reverseListByRec(Node head) {
        if(head == null||head.next == null){
            return head;
        }
        Node p = reverseListByRec(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    //双向链表迭代
    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode tmp = null;
        DoubleNode cur = head;
        while (cur != null){
            tmp = cur.next;
            cur.next = pre;
            cur.last = tmp;

            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /*
    * 反转部分单链表：反转链表中第from个到第to个
    * eg：1->2->3->4->5->NULL,   m = 2, n = 4   输出：1->4->3->2->5->NULL
    *
    * 思路：找到第from-1个节点fpre 和第to+1个节点tpos，反转中间部分，再连接不用反转的部分
    *      如果fpre为空，说明反转包含头结点，直接返回新头结点
    *      如果fpre不为空，返回旧头节点
    * */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int len = 0;
        ListNode cur = head;
        ListNode fpre = null;
        ListNode tpos = null;
        while (len <= n + 1 && cur != null){
            len++;
            fpre = len == m - 1 ? cur : fpre;
            tpos = len == n + 1 ? cur : tpos;
            cur = cur.next;
        }
        ListNode pre = fpre == null ? head : fpre.next;
        cur = pre.next;
        pre.next = tpos; //反转部分第一个节点连接最后部分
        ListNode next = null;
        while(cur != tpos){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if(fpre != null){
            fpre.next = pre;
            return head;
        }
        return pre;
    }
}

```
### 2.合并k个排序链表
![](https://upload-images.jianshu.io/upload_images/10460153-5a24ad8983de34e4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
/*
* 优先级队列：
* 先将每个首结点组成大小为k的小根堆，每次取出堆头，再放入该节点的next节点
* */
public ListNode mergeKLists_1(ListNode[] lists) {
    if(lists == null || lists.length == 0){
        return null;
    }
    PriorityQueue<ListNode> pq = new PriorityQueue<>((o1,o2) -> o1.val - o2.val);
    for (ListNode listnode : lists) {
        if(listnode == null){
            continue;
        }
        pq.add(listnode);
    }
    ListNode head = new ListNode(0);
    ListNode cur = head;
    while (!pq.isEmpty()){
        ListNode node = pq.poll();
        cur.next = node;
        cur = cur.next;
        if(node.next != null){
            pq.add(node.next);
        }
    }
    return head.next;
}
```
```java
//分治：
public ListNode mergeKLists_2(ListNode[] lists) {
    if(lists == null || lists.length == 0 ){
        return null;
    }
    if(lists.length == 1){
        return lists[0];
    }
    if(lists.length == 2){
        return merge(lists[0],lists[1]);
    }

    int mid = lists.length / 2;
    ListNode[] l1 = new ListNode[mid];
    for (int i = 0; i < mid; i++) {
        l1[i] = lists[i];
    }

    ListNode[] l2 = new ListNode[lists.length - mid];
    for (int i = mid,j = 0; i < lists.length; i++) {
        l2[j++] = lists[i];
    }
    return merge(mergeKLists_2(l1),mergeKLists_2(l2));
}
public ListNode merge(ListNode node1, ListNode node2){
    if(node1 == null){
        return node2;
    }
    if(node2 == null){
        return node1;
    }
    ListNode head = null;
    if(node1.val <= node2.val){
        head = node1;
        head.next = merge(node1.next,node2);
    }else {
        head = node2;
        head.next = merge(node1,node2.next);
    }
    return head;
}
```

### 3.两链表相加
![](https://upload-images.jianshu.io/upload_images/10460153-28f132f6bb881c5a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    // 利用栈来存储
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    while (l1 != null){
        stack1.push(l1.val);
        l1 = l1.next;
    }
    while (l2 != null){
        stack2.push(l2.val);
        l2 = l2.next;
    }

    int carry = 0; //进位标志
    ListNode head = null;
    while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
        int sum = carry;
        sum += stack1.isEmpty() ? 0 : stack1.pop();
        sum += stack2.isEmpty() ? 0 : stack2.pop();

        //每次新建节点next节点指向head，然后改变head
        ListNode node = new ListNode(sum % 10);
        node.next = head;
        head = node;
        carry = sum / 10;
    }
    return head;
}
```