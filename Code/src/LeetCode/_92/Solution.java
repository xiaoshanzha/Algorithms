package _92;

public class Solution {

    /**
     * 将链表分成三段， m 到 n 段 利用头插法建立新链表 进行反转
     * 将 三段链表 串起来 返回
     * */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null||head.next==null||m==n){
            return head;
        }
        ListNode two_head = new ListNode(0);
        ListNode pre = new ListNode(0);
        pre.next = head;
        int i = 0;
        ListNode before = null;

        // m 结点前保持不变
        while(i < m){
            before = pre;
            pre = pre.next;
            i++;
        }

        // 尾插法 建新链表将 m至n 结点进行反转，表头结点为 two_head.next
        ListNode temp;
        ListNode after = null;
        while(i<=n){
            temp = new ListNode(pre.val);
            if(i==m){
                after = temp;
            }
            ListNode p = two_head.next;
            two_head.next = temp;
            temp.next = p;
            i++;
            pre = pre.next;
        }
        // 三段进行串接
        after.next = pre;
        before.next = two_head.next;

        // m = 1 时，before指向最开始 pre 位置
        if(m==1){
            return two_head.next;
        }else {
            return head;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(new int[]{1,2,3,4,5});
        System.out.println(solution.reverseBetween(l1,2,4));
    }
}
