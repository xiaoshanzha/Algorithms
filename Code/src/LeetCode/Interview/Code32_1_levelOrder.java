package LeetCode.Interview;

import LeetCode.ordinary.Base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Code32_1_levelOrder {
    public static int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> nums = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            nums.add(temp.val);
            if(temp.left != null){
                queue.add(temp.left);
            }
            if(temp.right != null){
                queue.add(temp.right);
            }
        }
        int[] res = new int[nums.size()];
        int len = nums.size();
        for (int i = 0; i < len; i++) {
            res[i] = nums.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
     /*   head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        head.right.right.left = new TreeNode(7);*/

        int[] arr = levelOrder(head);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
