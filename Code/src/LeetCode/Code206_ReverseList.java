package LeetCode;

import java.util.List;

public class Code206_ReverseList {
    public static class ListNode{
        private int value;
        private ListNode next;
        public ListNode(int data){
            this.value = data;
        }
    }
    //迭代实现
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode tmp = null;
        ListNode cur = head;
        while(cur!=null){
            tmp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /*
    * 递归实现：
    * 我子节点下的所有节点都已经反转好了，
    * 现在就剩我和我的子节点 没有完成最后的反转了，所以反转一下我和我的子节点。
    *
    * eg:1->2->3->4->5
    * reverseListByRec(5): p:5->null
    * reverseListByRec(4): p:5->4->null
    * reverseListByRec(3): p:5->4->3->null
    * */
    public static ListNode reverseListByRec(ListNode head) {
        if(head == null||head.next == null){
            return head;
        }
        ListNode p = reverseListByRec(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void printLinkedList(ListNode head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);
    }
}
