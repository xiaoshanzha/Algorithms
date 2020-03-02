package LeetCode;

public class Code92_ReversePartList {
    public static class ListNode{
        private int value;
        private ListNode next;
        public ListNode(int data){
            this.value = data;
        }
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
