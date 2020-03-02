package Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndReconstructTree {
    /*
    * 二叉树的序列化和反序列化：
    * 序列化：二叉树被记录成文件的过程
    * 反序列化：通过文件内容重建原来二叉树的过程。
    *
    * 注意：需要用空占位符和结束的标志，eg:"#"表示结点为空，"_"表示一个值的结束
    *
    * 方法1：按先序遍历实现
    *       序列化：
    *       反序列化：重做先序遍历，遇到“#”生成null节点，结束生成后续子树
    *
    * 方法2：按层遍历实现
    *       序列化：使用队列，先加入头结点，每次弹出一个节点并加入其左右节点。如果为空则不加入，只改变str
    *               直到栈为空
    *       反序列化：重做层遍历，遇到“#”,生成null节点，不把null节点放队列即可
    * */
    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int data){
            this.value = data;
        }
    }
    public static String serialByPre(Node head){
        if(head == null){
            return "#_";
        }
        String  res = head.value +"_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String str){
        String[] values = str.split("_");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    private static String serialByLevel(Node head){
        if(head == null){
            return "#_";
        }
        String res = head.value + "_";
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            if(head.left != null){
                res += head.left.value + "_";
                queue.offer(head.left);
            }else {
                res += "#_";
            }
            if(head.right != null){
                res += head.right.value + "_";
                queue.offer(head.right);
            }else {
                res += "#_";
            }
        }
        return res;
    }

    private static Node reconByLevelString(String str){
        String[] values = str.split("_");
        int index = 0;
        Node head = generateNodeByString (values[index++]);
        Queue<Node> queue = new LinkedList<Node>();
        if(head != null){
            queue.offer(head);
        }
        Node node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString (values[index++]);
            node.right = generateNodeByString (values[index++]);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static Node generateNodeByString(String value) {
        if(value.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(value));
    }


    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

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

}
