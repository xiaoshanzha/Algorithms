package _24;

public class Solution {
       public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode t = pre;
        /**
         * 结点两两交换，若两个需交换的结点为start和end; start前结点为 t结点
         *
         * 则需改变的有：t.next;   start.next;  end.next;
         * */
        while (t.next!=null&&t.next.next!=null){
            ListNode start = t.next;
            ListNode end = t.next.next;

            t.next = end;
            start.next = end.next;
            end.next = start;

            t = start;
        }
        return pre.next;
    }

    /**
     * 递归实现，函数传进head结点，只将 head结点和紧跟的 after 结点进行交换，
     * next结点后面的利用 递归来实现。
     * */
    public ListNode swapPairs1(ListNode head) {
           if(head==null||head.next==null){
               return head;
           }
           ListNode after = head.next;
           head.next = swapPairs1(after.next);
           after.next = head;
           return after;
    }

    public static void main(String[] args) {
           Solution solution = new Solution();
           System.out.println(solution.swapPairs(new ListNode(new int []{1,2,3,4})));
    }
}
