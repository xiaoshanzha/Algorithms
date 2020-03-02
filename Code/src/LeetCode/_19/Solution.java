package _19;

import java.util.List;

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 添加哑结点，用来简化一些极端情况（eg:列表中只含有一个结点，或需要删除列表的头部。）
        ListNode p = new ListNode(0);
        p.next = head;
        ListNode cur = head;
        int sum = 0;
        while (cur!=null){
            sum ++;
            cur = cur.next;
        }
        int loc = sum - n;
        cur = p;
        while (loc>0){
            cur = cur.next;
            loc--;
        }
        cur.next = cur.next.next;
        return p.next;
    }

    /**
     * 双指针 一次遍历
     *
     * 第一个指针先从头结点向前移动 n 步；
     * 第二个结点 仍指向头结点，此时两节点位置相差 n;
     *
     * 一起向前移动,当第一个指针的next为空时，此时第二个指针的 next为要删除的结点
     * */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode p = new ListNode(0);
        p.next = head;
        ListNode first = p;
        ListNode second = p;
        for(int i = 1;i<=n;i++){
            first = first.next;
        }
        while (first.next!=null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return p.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,2,3,4,5});
        Solution solution = new Solution();
        System.out.println(solution.removeNthFromEnd1(head,2));
    }
}
