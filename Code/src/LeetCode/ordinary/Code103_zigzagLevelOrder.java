package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code103_zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return res;
        }
        boolean right = false;
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
            if(right){
                List<Integer> list_right = new ArrayList<>();
                for (int i = list.size() - 1 ; i >= 0 ; i--) {
                    list_right.add(list.get(i));
                }
                res.add(list_right);
            }else {
                res.add(list);
            }
            right = !right;
        }
        return res;
    }
}
