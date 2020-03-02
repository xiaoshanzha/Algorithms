package Graph;

import java.util.*;

/*
* 拓扑排序：要求有向图，且有入度为0的节点，且没有环
*   保证没有依赖的值或者依赖已经完成的值位于 需要依赖值的前面。
* eg:完成A事件需要B，C事件同时完成
*    完成B事件需要D,E事件同时完成
*    完成D事件需要F事件完成
*   拓扑排序: (F,E,C),D,B,A
*
*  实现:先找到一张图里面所有入度为0的节点，再删掉这些节点，再次找入度为0的节点
*  hashmap ；记录每个点的入度
*  queue : 记录所有入度为0的节点
*
*  */
public class TopologySort {
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node,Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for(Node node : graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in == 0){
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            //入度为0的节点加入后，改变该节点邻居节点的入度
            for(Node next : cur.nexts){
                inMap.put(next,inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}