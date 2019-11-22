package _82;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 单结点或空链直接返回
        if(head==null||head.next==null){
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p = pre;
        // second来遍历，second结点值不等于前一个结点值并且 （不等于后结点值或者后结点为空）则为有效结点，串在p结点之后
        ListNode first = head;
        ListNode second = first.next;

        //防止 一二结点值不同，直接跳过头结点
        if(first.val!=second.val){
            p.next = first;
            p = p.next;
        }
        while(second!=null){
            if(second.val!=first.val&&(second.next==null||second.val!=second.next.val)){
                p.next = second;
                p = p.next;
            }
            first = first.next;
            second = second.next;
        }
        //记得 尾结点 之后置空，不然 会将最后重复元素结点串起来
        p.next = null;
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,1});
        Solution solution = new Solution();
        System.out.println(solution.deleteDuplicates(head));
    }
}
