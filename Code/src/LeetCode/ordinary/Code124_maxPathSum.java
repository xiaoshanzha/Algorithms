package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

public class Code124_maxPathSum {
    public static class ReturnData{
        private int maxsum;
        private int sum;
        public ReturnData(int maxsum, int sum) {
            this.maxsum = maxsum;
            this.sum = sum;
        }
    }
    public static ReturnData process(TreeNode node){
        if(node == null){
            return new ReturnData(Integer.MIN_VALUE,Integer.MIN_VALUE);
        }
        ReturnData left = process(node.left);
        ReturnData right = process(node.right);
        int maxsum = 0;
        int sum = 0;
        if(left.sum == Integer.MIN_VALUE && node.val < 0){
            left.sum = node.val - 1;
        }
        if(right.sum == Integer.MIN_VALUE && node.val < 0){
            right.sum = node.val - 1;
        }

        if(left.sum == Integer.MIN_VALUE && right.sum == Integer.MIN_VALUE){
            maxsum = node.val;
            sum = node.val;
        }else {
            sum = Math.max(left.sum,right.sum) + node.val;
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

        TreeNode node2 = new TreeNode(2);
        node2.left = new TreeNode(-1);
        System.out.println(maxPathSum(node2));
    }
}
