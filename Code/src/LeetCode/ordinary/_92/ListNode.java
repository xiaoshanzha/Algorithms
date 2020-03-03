package _92;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
    }
    /*
    * 链表构造函数，将arr转成链表
    * */
    public ListNode(int[] arr){
        if(arr == null || arr.length==0){
            throw new IllegalArgumentException("arr is empty!");
        }
        this.val = arr[0];
        ListNode p = this;
        for(int i = 1;i < arr.length;i++){
            ListNode node = new ListNode(arr[i]);
            p.next = node;
            p = p.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode p = this;
        while(p!=null){
            res.append(p.val+" -> ");
            p = p.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
