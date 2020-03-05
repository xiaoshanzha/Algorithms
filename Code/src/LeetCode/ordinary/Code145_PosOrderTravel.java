package LeetCode.ordinary;

import LeetCode.ordinary.Base.TreeNode;
import javafx.scene.control.skin.SliderSkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code145_PosOrderTravel {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> new_stack = new Stack<>();
        TreeNode node = null;
        TreeNode cur = root;
        stack.push(cur);
        while(!stack.isEmpty()){
            node = stack.pop();
            new_stack.push(node);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        while (!new_stack.isEmpty()){
            res.add(new_stack.pop().val);
        }
        return res;
    }
}
