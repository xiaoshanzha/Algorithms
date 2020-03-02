package Linked_list;

import java.util.Stack;

public class IsPalindromeList {
    /*
    * 判断一个链表是否为回文结构:
    * 1->2->1，返回true。 1->2->2->1，返回true。 1->2->3，返回false
    * 普通：利用栈结构，遍历链表，把每个节点压入栈中，然后栈弹出值和顺序遍历链表进行比对
    *       需要栈的额外空间复杂度：O(N)
    *       优化：把整个链表的右半部分压入栈中，如果N是奇数，忽略最中间节点，压入右半区。
    *            即将链表的右半区域着过去，和左半部分进行比对
    * 进阶：不需要栈和其他的结构，额外空间复杂度为O(1)，时间O(N);
    *       首先确定链表的右区域，然后进行反转比对，最后在返回结果时应该恢复原来链表的结构
    * */
    public static class Node{
        private int value;
        private Node next;
        public Node(int data){
            this.value = data;
        }
    }
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        //压栈
        while(cur != null ){
            stack.push(cur);
            cur = cur.next;
        }
        while (head !=  null){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }
    public static boolean isPalindrome2(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node slow = head;
        Node fast = head;
        /*
        * 快指针fast一次以步长2前进，判断时应该fast.next 和fast.next.next一起进行判断,
        * 如果只判断fast.next.next可能会出现空指针错误
        * 右半区域为slow节点之后的部分
        * */
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<Node>();
        Node cur = slow.next;
        //压栈
        while(cur != null ){
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
    }

    /*
    * 跟方法二一样确定右部分的头结点，
    * 然后改变右区域结构，进行反转，跟左区域进行对比，在返回最后结果之前把链表恢复成原来的结构。
    * */
    public static boolean isPalindrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node right_start = slow.next;//right_start指向右部分第一个节点
        Node reverse_head = reverseList(right_start);  // reverse_head指向右部分反转后头节点，即右部分的尾结点
        slow.next = null;
        boolean flag = true;
        Node Right_cur = reverse_head;
        Node Left_cur  = head;
        while (Right_cur != null && Left_cur != null){
            if(Right_cur.value != Left_cur.value){
                flag = false;
                break;
            }
            Right_cur = Right_cur.next;
            Left_cur = Left_cur.next;
        }
        Node Right_Real_head = reverseList(reverse_head);
        slow.next = Right_Real_head;
        return flag;
    }

    public static Node reverseList(Node head){
        Node pre = null;  //上一结点
        Node tmp = null;  //临时结点，用于保存当前节点的下一结点
        Node cur = head;
        while (cur != null) {
            //反转指针域的指向
            tmp = cur.next;
            cur.next = pre;

            //指针往下进行移动
            pre = cur;
            cur = tmp;
        }
        head.next = null;
        return pre;
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
