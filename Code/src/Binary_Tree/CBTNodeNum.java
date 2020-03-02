package Binary_Tree;

public class CBTNodeNum {
    /*
    * 已知一棵完全二叉树，求其节点的个数
    * 要求：时间复杂度低于O(N)，N为这棵树的节点个数
    *
    * 思路: 1.空树直接返回0
    *       2.不是空树先求树的高度h，树的最左节点能到层即为树的高度。
    *       3.判断右树是否也能到达该层，如果可到，则左树为满树，高度为h的满树共有2^h个节点
    *         所以左侧(左树+根)共有2^h + 1个节点。如果不到h，则右数为满树，高度为(h-1),
    *         所以右侧共有2^(h-1)+1个节点。
    *
    *       所以每层只需遍历一个节点便可得到一半树的节点数。但每次遍历节点时，都会查看该节点右数最左值。
    *       时间复杂度O((logN)^2)
    * */
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    private static int getNum(Node head){
        if (head == null) {
            return 0;
        }
        return bs(head,1,mostLeftLevel(head,1));
    }
    private static int bs(Node node,int level,int h){
        if(level == h){
            return 1;
        }
        //左边为满二叉树，继续遍历右孩子节点
        if (mostLeftLevel(node.right, level + 1) == h) {
            //1 << (h - level)   ==   2^(h - level)
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        }
        //右边为满二叉树，高度为(h - level - 1)，继续遍历左孩子节点
        else {
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(getNum(head));

    }
}
