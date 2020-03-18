package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

public class Code104_maxDepth {
    public int Process(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = Process(root.left);
        int right = Process(root.right);
        int height = Math.max(left,right) + 1;
        return height;
    }
    public int maxDepth(TreeNode root) {
        if(root ==  null){
            return 0;
        }
        return Process(root);
    }
}
