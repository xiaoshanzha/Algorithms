package Template.Graph;

import java.util.HashSet;
import java.util.Stack;

/*
* 深度优先遍历：依次将节点的所有后代遍历完，才开始另一个节点的遍历
* 利用栈和HashSet来实现:
*   先将node进入栈和set，节点进栈就打印。
*   栈不为空循环{
*       栈弹出节点，
*       只要当前节点有邻居节点没进过set，就将当前节点和邻居节点一起入栈。
*       邻居节点入set， break；
*   }
* */
public class DFS {
    public static void dfs(Node node){
        if(node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next: cur.nexts) {
                if(!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
