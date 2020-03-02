package Linked_list;

import java.util.HashMap;

public class CopyListWithRand {
    /*
    * 复制含有随机指针节点的链表
    * Node类中除过next指针外，还有rand指针，这个指针可能指向链表中的任意一个节点，也可能指向null。
    * 给定一个由Node节点类型组成的无环单链表的头节点head，完成对链表中所有结构的复制，返回新链表的头节点。
    * 普通：使用HashMap,时间复杂度为O(N),额外空间复杂度O(N)，哈希表增删改查的操作时间复杂度都是O(1);
    *       1.遍历链表，对每个节点复制生成相应的副本，将对应关系放入map中。map<1,1'>表示节点1的副本为1'
    *       2.再次遍历链表，设置每一个副本节点的next和rand指针 eg:1.rand = 3,通过map表，设置1'.rand = 3';
    * 进阶：不适用哈希表，仅用有限的几个变量。时间复杂度为O(N),额外空间复杂度O(1)
    *       1.遍历链表，每个节点生成相应的副本节点copy，然后将copy放在cur和下一个要遍历节点的中间。
    *           eg：1->2->3->null,  变成 1->1'->2->2'->3->3'->null;
    *       2.再次遍历，设置副本节点的rand指针。
    *           eg：1->1'->2->2'->3->3'->null;  1.rand = 3,   通过1.next.rand = 1.rand.rand 来设置
    *       3.将链表中节点和副本节点分离
    * */
    public static class Node{
        private int value;
        private Node next;
        private Node rand;
        public Node(int data){
            this.value = data;
        }
    }
    private static Node CopyListWithRand1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    private static Node CopyListWithRand2(Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node tmp = null; //用来记录当前节点的下一节点
        while (cur != null){
            tmp = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = tmp;
            cur = tmp;
        }
        cur = head;
        // 设置复制节点的rand指针
        while (cur != null){
            cur.next.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }
        Node res = head.next;
        cur = head;
        Node curCopy = null;
        // 拆分
        while(cur != null){
            tmp = cur.next.next;
            curCopy = cur.next;
            cur.next = tmp;
            curCopy.next = tmp != null ? tmp.next : null;
            cur = tmp;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = CopyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = CopyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = CopyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = CopyListWithRand2(head);
        printRandLinkedList(res2);

    }
}
