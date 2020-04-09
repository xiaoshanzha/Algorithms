package Binary_Tree;

public class GetMaxBST {
    public class Node{
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    /*
    *//*
    * 可能性分析：以X为头结点的子树
    * 1.最大的BST，是左子树的最大BST
    * 2.最大的BST，是右子树的最大BST
    * 3.如果X左子树的最大BST是左子树全体，右子树的最大BST是右子树全体，
    *   并且X值大于左子树所有节点值，小于右子树所有节点值，则最大BST是X为头结点的树。
    * *//*
    public class ReturnType{
        public Node maxBSThead;
        public int maxBSTSize;
        public int min;
        public int max;

        public ReturnType(Node maxBSThead, int maxBSTSize, int min, int max) {
            this.maxBSThead = maxBSThead;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
            this.max = max;
        }
    }
    public ReturnType process(Node x){
        //base case:如果子树是空树，则最大值为系统最小，最小值为系统最大
        if(x == null){
            return new ReturnType(null,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
        }

        //默认直接得到左右子树的信息
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        //信息整合
        int min = Math.min(x.value,Math.min(leftData.min,rightData.min));
        int max = Math.max(x.value,Math.max(leftData.max,rightData.max));

        //如果只考虑可能性一二
        int maxBSTSize = Math.max(leftData.maxBSTSize,rightData.maxBSTSize);
        Node maxBSTHead = leftData.maxBSTSize >= rightData.maxBSTSize ? leftData.maxBSThead : rightData.maxBSThead;

        //判断可能性三
        if(leftData.maxBSThead == x.left && rightData.maxBSThead == x.right
            && x.value > leftData.max && x.value < rightData.min){
            maxBSTHead = x;
            maxBSTSize = leftData.maxBSTSize + rightData.maxBSTSize + 1;
        }
        return new ReturnType(maxBSTHead,maxBSTSize,min,max);
    }*/

    /*
    * 可能性分析：
    * 1.最大距离可能是左子树上的最大距离
    * 2.最大距离可能是右子树上的最大距离
    * 3.最大距离可能是左子树高度 + 右子树高度 + 1
    * */
    public class ReturnType{
        public int maxDistance;
        public int height;

        public ReturnType(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public ReturnType process(Node head){
        if(head == null){
            return new ReturnType(0,0);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);
        int height = Math.max(leftData.height,rightData.height) + 1;
        int maxDistance = Math.max(leftData.height + rightData.height + 1
                ,Math.max(leftData.maxDistance,rightData.maxDistance));
        return new ReturnType(maxDistance,height);
    }
}
