package Binary_Tree;

import LeetCode.ordinary.Base.TreeNode;

public class BST {
    //判断二叉搜索树,root需要和整个左子树和整个右子树所有节点进行比较
    boolean isValidBST(TreeNode root){
        //通过增加函数参数列表，在参数中携带额外的信息
        return isValidBST(root,null,null);
    }

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if(root == null){
            return true;
        }
        if(min != null && root.val <= min.val){
            return false;
        }
        if(max != null && root.val >= max.val){
            return false;
        }
        return isValidBST(root.left,min,root) && isValidBST(root.right,root,max);
    }
}
