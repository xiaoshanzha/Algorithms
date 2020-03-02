package Graph;

import java.util.*;

/*
* 最小生成树：
*   在保证图中所有节点都联通的情况下，所需权重最小的边的集合
*   仅适用于无向图
*
* P算法：按照点来考察，(通过解锁点来解锁边 进行选择)
*   从任意一点出发，与其相邻的所有边都被解锁，可选择，选择边权重最小的
*   如果该边解锁的点是新出现的则要该边， 该边连的点被解锁， 继续通过点解锁边，直到所有点都加入
* */
public class Prim {
    public static Set<Edge> primMST(Graph graph){
        PriorityQueue<Edge> priorityQueue = new
                PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        HashSet<Node> set = new HashSet<Node>();
        Set<Edge> result = new HashSet<Edge>();
        //图中遍历所有点，解决森林问题，一个图被分成好几个部分
        for (Node node : graph.nodes.values()){

            //如果该点未被解锁，解锁该点以及所有边
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()){
                    //选择权重最小的边,如果所连结点没有被解锁，则解锁该节点以及所有连接边
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if(!set.contains(toNode)){
                        set.add(toNode);
                        result.add(edge);
                        for(Edge nextEdge : toNode.edges){
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }


        }
        return result;
    }
}
