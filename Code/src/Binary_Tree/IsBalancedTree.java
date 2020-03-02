package Binary_Tree;

public class IsBalancedTree {
    /*
    * 判断二叉树是否是平衡二叉树：
    *    平衡二叉树即要么是一棵空树，要么任何一个节点的左右子树高度差的绝对值不超过1；
    *
    * 思路：对于任何一个节点来说，先遍历node的左子树，遍历过程中需要收集两个信息，
    *       一是node的左子树是否是平衡二叉树，二是node的左子树最深到哪一层。
    *       如果不平衡。无需进行后续过程，对于右树也一样。
    *
    * 套路：列出可能性，整理出返回值类型，通过递归过程得到子树的信息。
    *       整合子树的信息，加工信息返回。
    * eg :          1
    *            2     3
    *         4    nu3
    *      nu1 nu2
    * 上树中，nu都表示null节点，按下面代码流程，
    *        nu1返回(true,0),nu2返回(true,0),4返回(true,1) nu3返回(true,0)
    *        2返回(true,2),3返回(true,1),所以1返回(true,3)
    *
    * */
    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int data){
            this.value = data;
        }
    }
    public static class ReturnData{
        private boolean isB;
        private int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }
    public static boolean isB(Node head){
        return process(head).isB;
    }
    public static ReturnData process(Node head){
        if(head == null){
            return new ReturnData(true,0);
        }
        ReturnData leftData = process(head.left);
        if(!leftData.isB){
            return new ReturnData(false,0);
        }
        ReturnData rightData = process(head.right);
        if(!rightData.isB){
            return new ReturnData(false,0);
        }
        if(Math.abs(leftData.h - rightData.h) > 1){
            return new ReturnData(false,0);
        }
        return new ReturnData(true,Math.max(leftData.h,rightData.h) + 1);
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isB(head));

    }
}
