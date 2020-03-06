package _21;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(-1);
        ListNode p = h;
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1==null?l2:l1;
        return h.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(new int[]{1,2,4});
        ListNode l2 = new ListNode(new int[]{1,3,4});
        System.out.println(solution.mergeTwoLists(l1,l2));
    }
}
