package LeetCode.Interview;

import LeetCode.ordinary.Base.ListNode;

public class Code22_getKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode first = head;
        ListNode last = head;
        for (int i = 0; i < k; i++) {
            first = first.next;
        }
        while (first != null){
            first = first.next;
            last = last.next;
        }
        return last;
    }
}
