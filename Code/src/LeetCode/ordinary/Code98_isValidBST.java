package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

public class Code98_isValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
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
