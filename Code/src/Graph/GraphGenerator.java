package Graph;

/*
* 用邻接矩阵生成图
* {边权重，开始节点编号，终止节点编号}
*
*流程：根据邻接矩阵边信息，生成图
*      获取边权重，开始节点的编号，终止节点的编号，
*      判断两节点是否存在图中，不存在则加入节点，
*      根据两节点生成边，将边加入图中
*      改变起始节点的出度和指向节点和指向边信息，
*      改变终止节点的入度信息，
*
* */
public class GraphGenerator {
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight,fromNode,toNode);
            graph.edges.add(newEdge);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.edges.add(newEdge);
            toNode.in++;
        }
        return graph;
    }
}
