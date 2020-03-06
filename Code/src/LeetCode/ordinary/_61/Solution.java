package LeetCode.ordinary._61;
import LeetCode.ordinary.Base.ListNode;

public class Solution {

    /**
     * 将链表每个节点向右移动 k 个位置,即将链表后面（k % sum）个结点前置，
     *
     * 设 将结点 N 后结点前置，head结点到N结点为A段，后为B段，则需将 A->B ，变为 B->A;
     * 需执行操作： N.next为首结点;末结点.next = head; N.next = null;
     * */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode t = head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        int sum = 0;
        while(t!=null){
            sum++;
            t = t.next;
        }
        t = pre;
        if(sum == 0||k%sum==0){
            return head;
        }
        // sum为分界结点N，应将sum=0的情况置于此语句之前；
        sum = sum-(k % sum);
        int i = 0;
        while(i<sum){
            t= t.next;
            i++;
        }
        pre.next = t.next;
        ListNode temp = t.next;
        t.next = null;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = head;
        return pre.next;
    }

    public ListNode rotateRight1(ListNode head, int k) {
        ListNode t = head;
        int sum = 0;
        while(t!=null){
            sum++;
            t = t.next;
        }
        if(sum == 0||k%sum==0){
            return head;
        }
        // sum为从末结点往前数 共sum个结点需要前置，利用双指针，相差sum,
        sum = k % sum;
        ListNode first = head;
        ListNode second = head;
        while(sum>0){
            first = first.next;
            sum--;
        }
        while(first.next!=null){
            first = first.next;
            second = second.next;
        }
        first.next = head;
        ListNode re_head = second.next;
        second.next = null;
        return  re_head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int []{0,1,2});
        Solution solution = new Solution();
        System.out.println(solution.rotateRight1(head,3));
    }
}
