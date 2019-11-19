package _83;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode h = new ListNode(0);
        ListNode p = h;
        p.next = head;
        p = p.next;
        ListNode cur = head;
        while(cur!=null){
            System.out.println("p:"+p.val+"   cur:"+cur.val);
            if(p.val==cur.val){
                cur = cur.next;
                // 没有以下语句会出现 末结点重复删除不掉
                p.next = null;
            }else {
                p.next = cur;
                cur = cur.next;
                p = p.next;
            }
        }
        return h.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(new int[]{1,1,2,3,3});
        System.out.println(solution.deleteDuplicates(l1));
    }


}
