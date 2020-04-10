package LeetCode.ordinary.Base;

public class Code337_rob {
    public int rob(TreeNode root){
        int[] res = dp(root);
        return Math.max(res[0],res[1]);
    }
    public int[] dp(TreeNode node){
        if(node == null){
            return new int[]{0,0};
        }
        int[] left = dp(node.left);
        int[] right = dp(node.right);

        //抢该节点，不抢子节点
        int rob = node.val + left[0] + right[0];
        //抢该节点，下家可抢可不抢
        int not_rob = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        return new int[]{not_rob,rob};
    }
}
