package Binary_Tree;

import java.util.Stack;

public class PreInPosTraversal {
    /*
    * 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式
    * 先序：根左右   中序：左根右   后序：左右根
    * eg:        1
    *          2   3
    *         4 5 6 7
    * 递归：实际访问上树节点顺序：先访问根节点，再访问左子树，若无左子树，回到根节点，访问右子树，若无右，回到根。
    *       1，2，4，4，4，2，5，5，5，2，1，3，6，6，6，3，7，7，7，3，1
    *       每个节点都会被访问3次，如果将打印的动作放在第一次访问该节点时，为先序，第二次为中序，第三次为后序
    * 非递归： 先序：1.申请一个新栈，将头结点head压入栈中
    *               2.从栈中弹出栈顶节点，打印该节点值，再将节点的右孩子(不为空的话)压入栈中，
    *                 最后将左孩子(不为空的话)压入栈中。
    *               3.重复2过程，直到栈为空。
    *         中序：1.申请一个栈，初始时，令cur = head；
    *               2.先把cur节点压入栈中，对以cur节点为头的整棵树来说，依次把左边界压入栈中，
    *                 即cur = cur.left,依次重复该步骤，指导发现cur为空；
    *               3.此时从栈中弹出一个节点，打印该节点，(保证每次取出的都是未打印部分最左部分的数)
    *                 并让cur = cur.right，重复步骤2；
    *               4.当栈为空时，结束
    *         后序：和先序思路一样，先序要求：根左右， 后序要求：左右根
    *               先序进栈时，先压右 再压左。 如果先压左再压右，即可实现根右左。
    *               然后将前序打印的时机换为进入一个新栈，最后打印新栈的节点
    * */
    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int data){
            this.value = data;
        }
    }
    private static void preOrderRecur(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    private static void InOrderRecur(Node head){
        if(head == null){
            return;
        }
        InOrderRecur(head.left);
        System.out.print(head.value + " ");
        InOrderRecur(head.right);
    }
    private static void PosOrderRecur(Node head){
        if(head == null){
            return;
        }
        PosOrderRecur(head.left);
        PosOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    private static void PreOrderUnRecur(Node head){
        System.out.print("pre-order: ");
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node cur = null;
        while(!stack.isEmpty()){
            cur = stack.pop();
            System.out.print(cur.value + " ");
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    private static void InOrderUnRecur(Node head){
        System.out.print("In-order: ");
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        Node node = null;
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                node = stack.pop();
                System.out.print(node.value + " ");
                cur = node.right;
            }
        }
        System.out.println();
    }
    private static void PosOrderUnRecur(Node head){
        System.out.print("Pos-order: ");
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> new_stack = new Stack<>();
        stack.push(head);
        Node cur = null;
        while(!stack.isEmpty()){
            cur = stack.pop();
            new_stack.push(cur);
            if(cur.left != null){
                stack.push(cur.left);
            }
            if(cur.right != null){
                stack.push(cur.right);
            }
        }
        while (!new_stack.isEmpty()){
            System.out.print(new_stack.pop().value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        InOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        PosOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        PreOrderUnRecur(head);
        InOrderUnRecur(head);
        PosOrderUnRecur(head);
       // posOrderUnRecur2(head);
    }
}
