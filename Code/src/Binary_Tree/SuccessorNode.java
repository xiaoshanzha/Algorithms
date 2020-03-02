package Binary_Tree;

public class SuccessorNode {
    /*
    * 在二叉树中找一个节点的后继节点：
    *
    * 后继节点：在二叉树的中序遍历中，node的下一个节点叫做node的后继节点
    * 该Node结构比普通的node结构多了指向父节点的parent指针。以该Node类型的节点
    * 组成的二叉树，每个节点都正确指向字节的父节点，头结点的怕parent指向null，
    * 现给定该树中某节点node，返回node的后继节点。
    *
    * 思路一：根据node指向的父节点的指针，一直往上移动，找到头结点，然后中序遍历。
    *        时间和额外空间复杂度都为O(N)
    * 思路二：情况1：如果node有右子树，那么后继节点就是右子树上最左边的节点。
    *        情况2：如果node没有右子树，那么先看node节点是不是父节点的左孩子，
    *               如果是，那么父节点就是后继节点，如果是右孩子，继续向上寻找，
    *               假设向上移动到的节点是S,S的父节点是P,如果发现S是P的左孩子，
    *               那么节点P就是node的后继，否则继续向上移动。
    *        情况三：如果情况二一直找，找到空还没找到，说明node没有后继。
    * */
    public static class Node{
        private int value;
        private Node left;
        private Node right;
        private Node parent;
        public Node(int data){
            this.value = data;
        }
    }
    private static Node getNextNode(Node node){
        if(node == null){
            return null;
        }
        if(node.right != null){
            return getLeftMost(node.right);
        }
        Node cur = node;
        Node parent = node.parent;
        while(parent != null){
            if(parent.left == cur){
                return parent;
            }
            cur = parent;
            parent = cur.parent;
        }
        return null;
    }

    private static Node getLeftMost(Node node) {
        if(node == null){
            return node;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getNextNode(test));
    }
}
