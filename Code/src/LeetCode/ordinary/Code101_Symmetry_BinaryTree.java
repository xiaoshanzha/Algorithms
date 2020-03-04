package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
* 判断给定的树是否是镜像对称的
* */
public class Code101_Symmetry_BinaryTree {

    /*
     * 递归思路：一个树的左子树和右子树镜像对称，那么这个树对称
     *         两个树对称的条件:1.根结点值相同
     *                        2.每个树的左子树都与另一个树的右子树镜像对称。
     * */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmeric(root.left,root.right);
    }
    public static boolean isSymmeric(TreeNode t1,TreeNode t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 == null || t2 == null){
            return false;
        }
        if(t1.val != t2.val){
            return false;
        }
        return isSymmeric(t1.left,t2.right)&&isSymmeric(t1.right,t2.left);
    }

    /*
    * 通过迭代，类似BFS,每次取出两个节点，比较t1的左和t2的右，t1的右和t2的左
    *
    * 所以将节点添加队列时，应按此顺序
    * */
    public static boolean isSymmetric2(TreeNode root) {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1 ==null && t2 == null){
                continue;
            }
            if(t1 == null || t2 == null){
                return false;
            }
            if(t1.val != t2.val){
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.right);
        }
        return true;
    }
    /*
    * 中序遍历判断 结果是不是回文即可
    * */
    public static boolean isSymmetric3(TreeNode root) {
        if(root == null) {
            return true;
        }
        return intravel(root);
    }
    public static Boolean intravel(TreeNode root){
        ArrayList<Integer> s = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode node = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                node = stack.pop();
                s.add(node.val);
                cur = node.right;
            }
        }
        int first = 0;
        int last = s.size() - 1;
        while (first < last){
            if(s.get(first) != s.get(last)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        System.out.println(intravel(head));
    }
}
