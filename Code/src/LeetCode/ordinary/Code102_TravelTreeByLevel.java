package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code102_TravelTreeByLevel {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int num = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
