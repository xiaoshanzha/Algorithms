package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code199_rightSideView {
    //BFS
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node = null;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                if(i == size - 1){
                    list.add(node.val);
                }
            }
        }
        return list;
    }

    //DFS
    public List<Integer> rightSideViewByDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }
    private void dfs(List<Integer> res, TreeNode node, int level) {
        if(node != null) {
            //表示 该层还没有节点加入
            if(res.size() == level) {
                res.add(node.val);
            }
            dfs(res, node.right, level + 1);
            dfs(res, node.left, level + 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        Code199_rightSideView c = new Code199_rightSideView();
        c.rightSideViewByDFS(root);
    }
}
