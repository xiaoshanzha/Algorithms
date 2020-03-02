package Graph;

import java.util.ArrayList;

/*
* 图上节点需要的信息：
* value：节点值
* in：节点入度(有几个节点指向该节点)
* out：节点出度
* nexts：该节点指向的节点集合
* edges: 该节点指向的边集合
* */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
