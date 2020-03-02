package Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IsBSTAndCBT {
    /*
    * 判断是否是搜索二叉树和完全二叉树
    * 搜索二叉树(BST)：任何一个节点。左子树上的值都比它小，右子树上的值都比它大，
    *                 即 中序遍历依次升序。
    * 完全二叉树(CBT):将二叉树按层遍历每个节点。
    *               情况一：节点有右孩子没有左孩子。返回false。
    *               情况二：如果不是左右双全的即有左无右，无左无右的节点，之后遇到的节点必须是叶节点
    *                       否则返回false；
    *           eg:     1
    *               2        3
    *             4   5   6     7
    *           8           9
    *     上树中，按层遍历时，遍历到节点4时，有左无右，叶节点阶段开启，之后遇到的节点必须是叶节点。
    *       节点6有了右孩子，所以不是完全二叉树
    * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static boolean isBST(Node head){
        return InTraversal(head);
    }
    public static boolean InTraversal(Node head){
        if(head == null){
            return true;
        }
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        Node node = null;
        int tmp = Integer.MIN_VALUE;
        boolean flag = true;
        while(cur!=null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                node = stack.pop();
                if(node.value <= tmp){
                    flag = false;
                    break;
                }
                cur = node.right;
            }
        }
        return flag;
    }

    public static boolean isCBT(Node head){
        if(head == null){
            return true;
        }
        boolean leaf = false;
        Node node = null;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        while (!queue.isEmpty()){
            node = queue.poll();
            Node l = node.left;
            Node r = node.right;
            if((leaf && (l != null || r != null)) || (l == null && r != null)){

                return false;
            }
            if(l != null){
                queue.offer(l);
            }
            if(r != null){
                queue.offer(r);
            }
            //不可能出现左无右有的情况，因为第一次判断已经return。
            if(l == null || r == null){
                leaf = true;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.right = new Node(5);

        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }
}
