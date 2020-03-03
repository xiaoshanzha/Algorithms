package LeetCode.ordinary._2;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode p , q ,cur;
        cur = head;
        p = l1;
        q = l2;
        while(p!=null||q!=null){
            int x = p!=null?p.val:0;
            int y = q!=null?q.val:0;
            int sum = x+y+carry;
            carry = (sum)/10;
            cur.next= new ListNode(sum%10);
            cur = cur.next;
            if(p!=null){
                p = p.next;
            }
            if(q!=null){
                q = q.next;
            }
        }
        if(carry>0){
            cur.next = new ListNode(carry);
        }
        return head.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(new int[]{1});
        ListNode l2 = new ListNode(new int[]{9,9});
        System.out.println( solution.addTwoNumbers(l1,l2));
    }
}
