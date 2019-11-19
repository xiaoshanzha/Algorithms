package _83;

public class Solution {
    /*
    * 初
    * */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode h = new ListNode(0);
        ListNode p = h;
        p.next = head;
        p = p.next;
        ListNode cur = head;
        while(cur!=null){
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

    /*
    * 改：
    * 将结点的值与它之后的结点进行比较来确定它是否为重复结点。
    * 如果它是重复的，更改当前结点的 next 指针，
    * 以便它跳过下一个结点并直接指向下一个结点之后的结点。
    * */
    public ListNode deleteDuplicates1(ListNode head) {
        // cur一直指向相同数字第一次出现的结点
        ListNode cur = head;
        while(cur!=null&&cur.next!=null){
            if(cur.val==cur.next.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(new int[]{1,1,2,3,3});
        System.out.println(solution.deleteDuplicates1(l1));
    }
}
