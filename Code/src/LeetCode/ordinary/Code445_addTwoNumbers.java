package LeetCode.ordinary;


import LeetCode.ordinary.Base.ListNode;

import java.util.Stack;

public class Code445_addTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        StringBuilder s1 = new StringBuilder("");
        StringBuilder s2 = new StringBuilder("");
        while(l1 != null){
            s1.append(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.append(l2.val);
            l2 = l2.next;
        }
        int len_1 = s1.length();
        int len_2 = s2.length();
        String add = "";
        for (int i = 0; i < Math.abs(len_1 - len_2); i++) {
            add += '0';
        }
        String no = "";
        if(len_1 > len_2){
            add += s2.toString();
            no = s1.toString();
        }else {
            add += s1.toString();
            no = s2.toString();
        }
        char[] chars1 = add.toCharArray();
        char[] chars2 = no.toCharArray();
        int[] arr = new int[no.length()];
        boolean flag = false;
        for (int i = chars1.length - 1; i >= 0; i--) {
            int sum = 0;
            if(flag){
                sum = chars1[i] - '0' + ( chars2[i] - '0' ) + 1;
                flag = false;
            }else {
                sum = chars1[i] - '0' + ( chars2[i] - '0' );
            }
            if(sum >= 10){
                chars1[i] = (char) ('0' + (sum - 10));
                flag = true;
            }else {
                chars1[i] = (char) ('0' + sum);
            }
        }
        ListNode head = null;
        int cur = 0;
        if(flag){
            head = new ListNode(1);
            cur = 0;
        }else {
            head = new ListNode(chars1[cur] - '0');
            cur = 1;
        }
        ListNode node = head;
        for (int i = cur; i < chars1.length; i++) {
            node.next = new ListNode(chars1[i] - '0');
            node = node.next;
        }
        return head;
    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0; //进位标志
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            int sum = carry;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode res = addTwoNumbers(l1,l2);
        while (res != null){
            System.out.print(res.val + " -> ");
            res = res.next;
        }
    }
}
