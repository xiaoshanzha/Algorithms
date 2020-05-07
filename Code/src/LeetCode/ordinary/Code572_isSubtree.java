package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

public class Code572_isSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(t == null){
            return true;
        }
        if(s == null){
            return false;
        }
        return isSame(s,t) || isSubtree(s.left,t) || isSubtree(s.right,t);
    }
    public boolean isSame(TreeNode a, TreeNode b){
        if(a == null && b == null){
            return true;
        }
        if(a == null || b == null){
            return false;
        }
        if(a.val != b.val){
            return false;
        }
        return isSame(a.left,b.left) && isSame(a.right,b.right);
    }
}
