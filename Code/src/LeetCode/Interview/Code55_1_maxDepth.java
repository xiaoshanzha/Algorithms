package LeetCode.Interview;

import LeetCode.ordinary.Base.TreeNode;

public class Code55_1_maxDepth {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return process(root);
    }
    public int process(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = process(node.left);
        int right = process(node.right);
        int height = Math.max(left,right) + 1;
        return height;
    }
}
