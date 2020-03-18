package LeetCode.Interview;

import LeetCode.ordinary.Base.TreeNode;

public class Code28_isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return Symmetric(root.right,root.left);
    }
    public boolean Symmetric(TreeNode t1,TreeNode t2) {
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 == null || t2 == null){
            return false;
        }
        if(t1.val != t2.val){
            return false;
        }
        return (Symmetric(t1.left,t2.right))&&(Symmetric(t1.right,t2.left));
    }

}
