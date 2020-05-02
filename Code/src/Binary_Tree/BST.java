package Binary_Tree;

import LeetCode.ordinary.Base.TreeNode;
import com.sun.source.tree.Tree;

public class BST {
    //判断二叉搜索树
    //root需要和整个左子树和整个右子树所有节点进行比较
    boolean isValidBST(TreeNode root){
        //通过增加函数参数列表，在参数中携带额外的信息
        return isValidBST(root,null,null);
    }

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if(root == null){
            return true;
        }
        if(min != null && root.val <= min.val){
            return false;
        }
        if(max != null && root.val >= max.val){
            return false;
        }
        return isValidBST(root.left,min,root) && isValidBST(root.right,root,max);
    }

    //在BST中查找一个数是否存在
    //不需要递归的搜索两边，利用左小右大特性排除一边
    boolean isInBST(TreeNode root,int target){
        if(root == null){
            return false;
        }
        if(root.val == target){
            return true;
        }
        if(root.val < target){
            return isInBST(root.right,target);
        }
        return isInBST(root.left,target);
    }

    //在BST中插入一个数
    //涉及到“改”，函数就要返回TreeNode类型，并且对递归调用的返回值进行接收
    TreeNode insertIntoBST(TreeNode root,int var){
        if(root == null){
            return new TreeNode(var);
        }
        /*if(root.val == var){
            //BST一般不会插入已存在的元素
        }*/
        if(root.val < var){
            root.right = insertIntoBST(root.right,var);
        }
        if(root.val > var){
            root.left = insertIntoBST(root.left,var);
        }
        return root;
    }

    //在BST中删除一个数
    TreeNode deleteNode(TreeNode root,int key){
        if(root.val == key){
            /*
            * 找到了该值进行删除(共三种情况)
            * 1.恰好是末端节点，两个子节点都为空，直接删除
            * 2.只有一个非空子节点，让这个子节点接替自己的位置
            * 3.有两个非空子节点，找出左子树最大的节点或者右子树中最小的节点接替自己的位置
            * */
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //统一找出右子树最小节点,
            //将要删除节点的值变为右子树最小值，然后删除右子树最小值
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right,minNode.val);
        }else if(root.val > key){
            root.left = deleteNode(root.left,key);
        }else if(root.val < key){
            root.right = deleteNode(root.right,key);
        }
        return root;
    }
    TreeNode getMin(TreeNode node){
        //BST最左边的值就是最小值
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
}
