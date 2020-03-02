package Linked_list;

public class PrintCommonPartsOfTwo {
    /*
    * 打印两个有序链表的公共部分 : 值相等打印该值
    * */
    public static class Node{
        private int value;
        private Node next;
        public Node(int data){
            this.value = data;
        }
    }
    private static void PrintCommonParts(Node head1,Node head2){
        System.out.print("Common Part: ");
        while(head1!=null && head2 != null){
            if(head1.value == head2.value){
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }else if(head1.value < head2.value){
                head1 = head1.next;
            }else {
                head2 = head2.next;
            }
        }
    }
    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(2);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(2);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printLinkedList(node1);
        printLinkedList(node2);
        PrintCommonParts(node1, node2);
    }

    private static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null){
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
