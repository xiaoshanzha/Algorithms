package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;

import javax.swing.table.TableRowSorter;
import java.util.*;

public class Code129_sumNumbers {
    public static int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> strings = new Stack<>();
        stack.add(root);
        strings.add(Integer.toString(root.val));
        TreeNode cur = null;
        while (!stack.isEmpty()){
            cur = stack.pop();
            String s = strings.pop();
            if(cur.right != null){
                stack.add(cur.right);
                strings.add(s + cur.right.val);
            }
            if(cur.left != null){
                stack.add(cur.left);
                strings.add(s + cur.left.val);
            }
            if(cur.left == null && cur.right == null){
                sum += Integer.valueOf(s);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(9);
        node.right = new TreeNode(0);
        node.left.left = new TreeNode(5);
        node.left.right = new TreeNode(1);

        TreeNode node1 = null;
        System.out.println(sumNumbers(node1));
    }
}
