package Linked_list;

public class ReverseList {
    /*
    * 反转单向和双向链表
    * 要求： 如果链表长度为N,时间复杂度要求为O(N),额外空间复杂度为O(1)
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
    public static class Node{
        private int value;
        private Node next;
        public Node(int data){
            this.value = data;
        }
    }
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

    public static Node reverseListByRec(Node head) {
        if(head == null||head.next == null){
            return head;
        }
        Node p = reverseListByRec(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void printLinkedList(Node head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }


    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }
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
    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("Double Linked List: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        printDoubleLinkedList(head2);
        printDoubleLinkedList(reverseList(head2));
    }
}
