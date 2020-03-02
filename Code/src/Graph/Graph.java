package Graph;

import java.util.HashMap;
import java.util.HashSet;

/*
* 图保存的信息：
*   nodes: 所有节点，用map(节点号，节点)
*   edges: 所有边信息，用set
* */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
