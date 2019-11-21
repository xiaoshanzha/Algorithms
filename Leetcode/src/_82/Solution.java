package _82;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode first = pre;
        ListNode second = first.next;
        while(second!=null){
            if()
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,2,3,3,4,4,5});
        Solution solution = new Solution();
        System.out.println(solution.deleteDuplicates(head));
    }
}
