package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

import java.util.Stack;

public class Code112_hasPathSum {
    //栈进行迭代，dfs
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        Stack<TreeNode> stack = new Stack();
        Stack<Integer> node_sum = new Stack();
        stack.add(root);
        node_sum.add(root.val);
        TreeNode cur = null;
        int cur_sum ;
        while(!stack.isEmpty()){
            cur = stack.pop();
            cur_sum = node_sum.pop();
            if(cur.right != null){
                stack.add(cur.right);
                node_sum.add(cur_sum + cur.right.val);
            }
            if(cur.left != null){
                stack.add(cur.left);
                node_sum.add(cur_sum + cur.left.val);
            }
            if(cur.left == null && cur.right == null && cur_sum == sum){
                return true;
            }
        }
        return false;
    }

    //递归实现 : 先判断该节点，再判断left，right，只要有一个true 就是true，所以 ||
    public static boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        sum -= root.val;
        if(root.left == null && root.right == null){
            return sum == 0;
        }
        return hasPathSum1(root.left,sum) || hasPathSum1(root.right,sum);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.right = new TreeNode(2);
        System.out.println(hasPathSum(node,8));
    }
}
