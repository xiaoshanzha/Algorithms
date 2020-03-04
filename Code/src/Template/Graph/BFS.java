package Template.Graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
* 广度优先搜索：按距离node点距离的远近依次输出
* 队列和hashset来实现：
*           先将node加入队列中，队列不为空就循环。
*           从队列中取出节点，输出该节点，并将该节点指向的nexts节点全部加入队列
*       保证进队列节点的顺序为：距离node远近的顺序，
*  hashset的作用：保证一个点只进入依次队列。
*       eg: 1->2   3->2  当2已经存在set中，则不再次加入
*
* 问题：leetCode994 橘子腐烂问题，
* */
public class BFS {
    public static void bfs(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next:  cur.nexts) {
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
