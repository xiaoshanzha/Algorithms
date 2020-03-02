package Linked_list;

public class ListPartition {
    /*
    * 将单向链表按某值划分成左边小，中间相等，右边大的形式。
    * 进阶要求：时间复杂度O(N),额外空间复杂度O(1)并且大小区域节点的相对位置不变。
    *  普通解：先遍历链表，得到链表的长度N，生成长度为N的Node类型的数组。
    *          将节点依次存放在数组，然后对数组进行partition的过程，
    *          最后将数组中的节点依次重连起来。
    *           时间复杂度O(N),额外空间复杂度O(N)
    * 进阶解法；将原链表中的所有节点依次划分进三个链表，small,equal,big分别代表左中右部分
    *           最后将三个链表重新串起来。注意对null节点的判断和处理。
    * */
    public static class Node{
        private int value;
        private Node next;
        public Node(int data){
            this.value = data;
        }
    }
    public static Node listPartion1(Node head ,int pivot){
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while(cur != null){
            cur = cur.next;
            i++;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for(i = 0;i < nodeArr.length;i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr,pivot);
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }
    private static void arrPartition(Node[] nodeArr, int pivot) {
        int cur = 0;
        int less = - 1;
        int more = nodeArr.length;
        while(cur < more){
            if(nodeArr[cur].value < pivot){
                swap(nodeArr,++less,cur++);
            }else if(nodeArr[cur].value > pivot){
                swap(nodeArr,--more,cur);
            }else {
                cur++;
            }
        }
    }
    private static void swap(Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }


    public static Node listPartion2(Node head ,int pivot){
        Node sH = null;  //小的头
        Node sT = null;  //小的尾
        Node eH = null;  //相等的头
        Node eT = null;  //相等的尾
        Node bH = null;  //大的头
        Node bT = null;  //大的尾
        Node next =  null;  //用来保存下一个节点
        //将所有节点分进三个链表中
        while(head != null){
            next = head.next;
            head.next = null;  //将当前节点的next置空
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else{
                if(bH == null){
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        //小链和等链连接
        if(sT != null){
            sT.next = eH;
            eT = eT==null ? sT : eT;
        }
        //所有的连接
        if(eT != null){
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        printLinkedList(listPartion1(head1, 4));
    //    printLinkedList(listPartion2(head1, 4));

    }
}
