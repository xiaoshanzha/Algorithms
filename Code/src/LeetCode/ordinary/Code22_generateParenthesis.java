package LeetCode.ordinary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code22_generateParenthesis {
    //深度优先:使用递归（回溯+剪枝）
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        dfs("",n,n,res);
        for (String s : res) {
            System.out.println(s);
        }
        return res;
    }

    //left 表示左括号剩余数量，right表示右括号剩余数量，s为目前字符串状态
    private void dfs(String s, int left, int right, List<String> res) {
        //递归终止条件，终止时将目前字符串添加到结果集
        if(left == 0 && right == 0){
            res.add(s);
            return;
        }
        //产生左分支的条件
        if(left > 0){
            dfs(s+"(",left - 1,right,res);
        }
        //产生右分支的条件
        if(right > 0 && right > left){
            dfs(s+")",left ,right - 1,res);
        }
    }

    /*
    * 广度优先搜索：需要自己编写结点类，用队列
    * */

    class Node{
        private String s; //当前得到的字符串
        private int left; //剩余左括号的数量
        private int right; //剩余右括号数量

        public Node(String s, int left, int right) {
            this.s = s;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("",n,n));
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode.left == 0 && curNode.right == 0){
                res.add(curNode.s);
            }
            if(curNode.left > 0){
                queue.offer(new Node(curNode.s + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.s + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Code22_generateParenthesis c = new Code22_generateParenthesis();
        c.generateParenthesis(3);
    }
}
