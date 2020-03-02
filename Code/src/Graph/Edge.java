package Graph;

/*
* 边保存的信息：
*   weight：边的权重
*   from: 该边的起始节点
*   to: 该边的指向节点
* */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
