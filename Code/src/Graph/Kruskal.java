package Graph;

import java.util.*;

/*
* 最小生成树K算法：
*  依次找小权重的边，且不构成回路，则选择，直到所有点都进来
*
*  开始将每一个点作为并查集中小的集合，按边的权重组成小根堆，
*  每次弹出一个边，如果边的from和to已经属于一个集合，该边不要，
*  不属于一个集合则要，并且把from和to所在集合合并在一起
*
* */
public class Kruskal {
    public static class UnionFind {
        private HashMap<Node,Node> fatherMap;
        private HashMap<Node,Integer> rankMap;
        public UnionFind(){
            fatherMap = new HashMap<Node,Node>();
            rankMap = new HashMap<Node,Integer>();
        }
        /*
        * 通过递归寻找父节点,并将所有经过的结点父节点都置为代表结点
        * */
        private Node findFatherByRec(Node n){
            Node father = fatherMap.get(n);
            if(father != n){
                father = findFatherByRec(father);
            }
            fatherMap.put(n,father);
            return father;
        }
        /*
        * 非递归进行寻找
        * */
        private Node findFatherByUnRec(Node n){
            Stack<Node> path = new Stack<>();
            while(n != fatherMap.get(n)){
                path.push(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()){
                fatherMap.put(path.pop(),n);
            }
            return n;
        }
        // 并查集进行初始化
        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            rankMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a,Node b){
            return findFatherByRec(a) == findFatherByRec(b);
        }

        public void union(Node a ,Node b){
            if(a == null || b == null){
                return;
            }
            Node aFather = findFatherByRec(a);
            Node bFather = findFatherByRec(b);
            if(aFather != bFather){
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if (aFrank <= bFrank) {
                    fatherMap.put(aFather, bFather);
                    rankMap.put(bFather, aFrank + bFrank);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + bFrank);
                }
            }
        }
    }



    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue =
                new PriorityQueue<Edge>(new Comparator<Edge>() {
                    @Override
                    public int compare(Edge o1, Edge o2) {
                        return o1.weight - o2.weight;
                    }
                });
        for(Edge edge : graph.edges){
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<Edge>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!unionFind.isSameSet(edge.from,edge.to)){
                result.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return result;
    }
}
