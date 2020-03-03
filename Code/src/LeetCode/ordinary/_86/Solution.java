package _86;

public class Solution {
    // 哈哈，跟官方题解完全一样，就是效率不高
    public ListNode partition(ListNode head, int x) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode samll_head = new ListNode(0);
        ListNode big_head = new ListNode(0);
        ListNode small = samll_head;
        ListNode big = big_head;
        ListNode p = head;
        while (p!=null){
            if(p.val<x){
                small.next = p;
                small = small.next;
            }else {
                big.next = p;
                big = big.next;
            }
            p = p.next;
        }
        big.next = null;
        small.next = big_head.next;
        return samll_head.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(new int[]{1,4,3,2,5,2});
        System.out.println(solution.partition(l1,3));
    }
}
