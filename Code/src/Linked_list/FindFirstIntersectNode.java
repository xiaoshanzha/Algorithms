package Linked_list;

public class FindFirstIntersectNode {
    /*
    * 两个单链表相交的一系列问题:(难)
    *
    * 单链表可能有环，也可能无环。给定两个单链表的头节点 head1和head2，
    * 这两个链表可能相交，也可能不相交。请实现一个函数，
    * 如果两个链表相交，请返回相交的第一个节点，如果不相交，返回null 即可。
    * 要求：如果链表1的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外空间复杂度请达到O(1)。
    *
    * 思路：拆分成以下三个子问题
    * 1.如何判断一个链表是否有环，如果有，返回第一个进入环的节点。没有返回null
    * 2.如何判断两个无环链表是否相交，相交返回第一个相交的节点，没有返回null
    * 3.如何判断两个有环链表是否相交，相交返回第一个相交的节点，没有返回null
    * 注意：如果一个有环，一个没环，不可能相交，直接返回null
    * */

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    private static  Node getIntersectNode(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null && loop2 == null){
            return noloop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothloop(head1,loop1,head2,loop2);
        }
        return null;
    }

    /*
    * 问题一：得到链表入环节点
    * 思路：如果一个链表无环，遍历一定可以访问到null；有环，将无限循环
    *      链表有环找第一个入环节点的过程
    *      1.设置两个快慢指针slow和fast，如果两指针相遇，则有环
    *      2.两指针相遇时，fast指针重新回到head位置，slow指针不动。然后两指针都以
    *        步长为1进行移动。
    *      3.fast和slow指针一定会再次相遇，并且在第一个节点处相遇。
    * */
    public static Node getLoopNode(Node head) {
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast){
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /*
    * 问题二：两个无环链表相交问题
    *
    * 思路： 1.遍历两链表，分别记录长度和两个的尾节点
    *       2.如果两个尾节点不相等则不相交，返回null
    *         否则通过两个链表长度得到两个链表长度差值，
    *         再次遍历时，长的先走(长-短)步，再一起走，两个链表第一次走到一起的节点即所求
    * */
    private static Node noloop(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        //两链表都没算最后一个节点，方便尾结点的比较
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }
        //cur1指向长的单链表的头结点，cur2指向短的
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /*
    * 问题三：两个有环链表相交问题
    * 思路：1.得到两个有环链表的入环节点loop1，loop2
    *       2.loop1 == loop2时，只需考虑head1到loop1这段和head2到loop2这段在哪相交
    *         类似于问题二，这里是把loop1(loop2)作为链表的终点
    *       3.loop1 != loop2时， 有两种，一种相交共用一环，不同的环入点
    *         另一种不相交，为两个无关的有环链表
    *  步骤3情况判断：链表一从loop1出发再回到loop1时，如果没遇到loop2，则为两个无关的有环链表
    *               遇到的话，则为共用一环，返回loop1，2 都可以
    *
    * */
    private static Node bothloop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            //两链表都没算最后一个节点，方便尾结点的比较
            while (cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if(cur1 == loop2){
                   // return loop1;
                    return loop2;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
