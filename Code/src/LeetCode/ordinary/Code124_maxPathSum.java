package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

/*
* 树形dp：
* 可能性：对于以x为头结点的树
*       1.最大路径和是该节点左树上的最大路径和
*       2.最大路径和是右树上的最大路径和
*       3.最大路径和是该节点值
*       4.最大路径和是该节点值 + 包含x左孩子节点的最大路径
*       5.最大路径和是该节点值 + 包含x右孩子节点的最大路径
*       6.最大路径和是该节点值 + 包含x左、右孩子节点的最大路径
*
* 所以定义返回值类型时 需要包含的信息： 以该节点为根节点该树上的最大路径和， 该节点包括该节点的最大路径和
* */
public class Code124_maxPathSum {
    public static class ReturnData{
        private int maxsum; //该节点为根节点该树上的最大路径和
        private int sum;  //包括该节点的最大路径和
        public ReturnData(int maxsum, int sum) {
            this.maxsum = maxsum;
            this.sum = sum;
        }
    }
    public static ReturnData process(TreeNode node){
        if(node == null){
            //因为可能出现负数，所以不能为0；
            return new ReturnData(Integer.MIN_VALUE,Integer.MIN_VALUE);
        }
        ReturnData left = process(node.left);
        ReturnData right = process(node.right);
        int maxsum = 0;
        int sum = 0;

        // 后面计算sum、maxsum时，会出现系统最小值和负数相加溢出的情况
        // 处理叶子节点
        if(left.sum == Integer.MIN_VALUE && right.sum == Integer.MIN_VALUE){
            maxsum = node.val;
            sum = node.val;
        }else {
            //左孩子为空
            if(left.sum == Integer.MIN_VALUE){
                if(node.val < 0){
                    //保证不会影响后面的计算，而且不会溢出
                    left.sum = node.val ;
                }else {
                    left.sum = 0;
                }
            }
            //右孩子为空

            if(right.sum == Integer.MIN_VALUE){
                if(node.val < 0){
                    right.sum = node.val;
                }else {
                    right.sum = 0;
                }
            }
            //包含该节点的最大路径和可能 只包含该节点
            sum = Math.max(Math.max(left.sum,right.sum) + node.val,node.val);
            maxsum = Math.max(Math.max(Math.max(Math.max
                            (left.maxsum,
                                    right.maxsum),
                    left.sum + right.sum +node.val),
                    sum),
                    node.val);

        }
        return new ReturnData(maxsum,sum);
    }
    public static int maxPathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        return process(root).maxsum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(maxPathSum(node));


        TreeNode node1 = new TreeNode(-10);
        node1.left = new TreeNode(9);
        node1.right = new TreeNode(20);
        node1.right.left = new TreeNode(15);
        node1.right.right = new TreeNode(7);
        System.out.println(maxPathSum(node1));

        TreeNode node2 = new TreeNode(-2);
        node2.left = new TreeNode(1);
        System.out.println(maxPathSum(node2));

        TreeNode node3 = new TreeNode(9);
        node3.left = new TreeNode(-3);
        node3.left.left = new TreeNode(3);
        node3.right = new TreeNode(0);
        node3.right.right = new TreeNode(-8);
        System.out.println(maxPathSum(node3));

        TreeNode node4 = new TreeNode(9);
        node4.left = new TreeNode(6);
        node4.right = new TreeNode(-3);
        node4.right.left = new TreeNode(-6);
        node4.right.right = new TreeNode(2);
        node4.right.right.left = new TreeNode(2);
        node4.right.right.left.left = new TreeNode(-6);
        node4.right.right.left.left.left = new TreeNode(-6);
        node4.right.right.left.right = new TreeNode(-6);
        System.out.println(maxPathSum(node4));
    }
}
