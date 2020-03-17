package LeetCode.Interview;

import LeetCode.ordinary.Base.TreeNode;

public class Code55_2_isBalanced {
    public class ReturnData{
        private boolean isB;
        private int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return process(root).isB;
    }
    public ReturnData process(TreeNode node){
        if(node == null){
            return new ReturnData(true,0);
        }
        ReturnData leftData = process(node.left);
        if(!leftData.isB){
            return new ReturnData(false,0);
        }
        ReturnData rightData = process(node.right);
        if(!rightData.isB){
            return new ReturnData(false,0);
        }
        if(Math.abs(leftData.h - rightData.h) > 1){
            return new ReturnData(false,0);
        }
        int max_h = Math.max(leftData.h,rightData.h);
        return new ReturnData(true,max_h + 1);
    }

    public static void main(String[] args) {
        Code55_2_isBalanced c = new Code55_2_isBalanced();
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        head.right.right.left = new TreeNode(7);

        System.out.println(c.isBalanced(head));
    }
}
