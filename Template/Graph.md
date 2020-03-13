## 图算法总结
#### 1.bfs + dfs
```java
/*
* 广度优先搜索：按距离node点距离的远近依次输出
* 队列和hashset来实现：
*           先将node加入队列中，队列不为空就循环。
*           从队列中取出节点，输出该节点，并将该节点指向的nexts节点全部加入队列
*       保证进队列节点的顺序为：距离node远近的顺序，
*  hashset的作用：保证一个点只进入依次队列。
*       eg: 1->2   3->2  当2已经存在set中，则不再次加入
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
```
```java
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
```