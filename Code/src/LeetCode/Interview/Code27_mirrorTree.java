package LeetCode.Interview;

import LeetCode.ordinary.Base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Code27_mirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        //反转一颗空树，结果还是空树， 对非空树，左 右互换
        if(root == null){
            return null;
        }
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //迭代
    /*
    * 我们需要交换树中所有节点的左孩子和右孩子。
    * 因此可以创一个队列来存储所有左孩子和右孩子还没有被交换过的节点。
    *
    * 开始的时候，只有根节点在这个队列里面。
    * 只要这个队列不空，就一直从队列中出队节点，然后互换这个节点的左右孩子节点，接着再把孩子节点入队到队列，
    * 对于其中的空节点不需要加入队列。最终队列一定会空，这时候所有节点的孩子节点都被互换过了，直接返回最初的根节点就可以了。
    * */
    public TreeNode mirrorTree1(TreeNode root) {
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
        return root;
    }
}
