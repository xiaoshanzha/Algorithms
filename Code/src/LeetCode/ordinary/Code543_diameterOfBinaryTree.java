package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

public class Code543_diameterOfBinaryTree {
    /*
    * 以x为头结点的子树最大距离可能性分析：
    * 1.最大距离可能是左子树上的最大值
    * 2.最大距离可能是右子树上的最大值
    * 3.最大距离可能是x的左子树离x最远的节点，经过x然后到达x右子树离x最远的节点（左子树高度+右子树高度+1）
    * */
    public static class ReturnDistance{
        private int height;
        private int maxDistance;

        public ReturnDistance(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }
    public static ReturnDistance process(TreeNode node){
        if(node == null){
            return new ReturnDistance(0,0);
        }
        ReturnDistance left  = process(node.left);
        ReturnDistance right  = process(node.right);
        int height = Math.max(left.height,right.height) + 1;
        int maxDistance = Math.max(Math.max(left.maxDistance,right.maxDistance),
                left.height + right.height + 1);
        return new ReturnDistance(height,maxDistance);
    }
    public static int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        return process(root).maxDistance - 1;
    }

    public static void main(String[] args) {
        TreeNode node1  = null;
        TreeNode node  = new TreeNode(1);
        node.left  = new TreeNode(2);
        node.left.left  = new TreeNode(4);
        node.left.right  = new TreeNode(2);
        node.left.right.right  = new TreeNode(2);
        node.right  = new TreeNode(3);
        System.out.println(diameterOfBinaryTree(node1));
    }
}
