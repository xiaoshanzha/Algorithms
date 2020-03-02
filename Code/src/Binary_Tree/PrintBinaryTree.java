package Binary_Tree;

public class PrintBinaryTree {
    /*
    * 较为直观的打印一颗二叉树：
    * 思路:首先将二叉树逆时针旋转90°，来从上往下看节点时，
    *      恰好是二叉树按照右根左的顺序进行遍历的结果，根节点输出时在最左边较为容易实现并且直观。
    *   注意：1.打印节点值时应该占用统一的长度，不然出现格式对不齐，不够直观。
    *         java中整型值32位，占用最长的是(-2147483648),占用的长度最长11位,
    *         值前后分别用"H","^","v"来表示和父节点的位置关系，前后各留两个空格，共17位，
    *        2.根据节点所在层，在输出节点时，先输出对应的空格，再输出对应的内容。
    *           eg：节点在第N层，输出时，先输出N*17个空格
    * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length(); //中间输出有效内容的长度
        int lenL = (len - lenM) / 2; // 有效内容左边的空格数
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

        head = new Node(5);
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
        printTree(head);
    }
}
