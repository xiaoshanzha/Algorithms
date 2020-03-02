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

#### 1.链表反转(全部反转和部分反转)
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