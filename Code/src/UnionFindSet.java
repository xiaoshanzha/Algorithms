import java.io.*;
import java.util.HashMap;
import java.util.Stack;

//牛课题：并查集的使用。
/*
* 给定一个没有重复值的整形数组arr，初始时认为arr中每一个数各自都是一个单独的集合。请设计一种叫UnionFind的结构，并提供以下两个操作。
* 1.boolean isSameSet(int a, int b): 查询a和b这两个数是否属于一个集合
* 2.void union(int a, int b): 把a所在的集合与b所在的集合合并在一起，
*   原本两个集合各自的元素以后都算作同一个集合
*
* 第一行两个整数N, M。分别表示数组大小、操作次数
* 接下来M行，每行有一个整数opt
* 若opt = 1，后面有两个数x, y，表示查询(x, y)这两个数是否属于同一个集合
* 若opt = 2，后面有两个数x, y，表示把x, y所在的集合合并在一起
*
*
* 解决：该题不应该使用hashmap会超时，应该使用数组，因为只有整型，可以满足，
* */
public class UnionFindSet {
    public static class unionfindset{
        public HashMap<Integer,Integer> fathermap;
        public HashMap<Integer,Integer> rankmap;
        public unionfindset(int n) {
            fathermap = new HashMap<>();
            rankmap = new HashMap<>();

            for (int i = 1; i <= n; i++) {
                fathermap.put(i,i);
                rankmap.put(i,1);
            }
        }
        private int findHead(int e){
            Stack<Integer> path = new Stack<>();
            while (fathermap.get(e) != e){
                path.push(e);
                e = fathermap.get(e);
            }
            while (!path.isEmpty()){
                fathermap.put(path.pop(),e);
            }
            return e;
        }

        public boolean isSameSet(int a, int b){
            if(fathermap.containsKey(a) && fathermap.containsKey(b)){
                return findHead(a) == findHead(b);
            }
            return false;
        }
        public void union(int a,int b){
            if(fathermap.containsKey(a) && fathermap.containsKey(b)){
                //找到两个节点的代表节点
                int aF = findHead(a);
                int bF = findHead(b);
                if(aF != bF){
                    //big表示所在集合更大的节点
                    int aSetSize = rankmap.get(aF);
                    int bSetSize = rankmap.get(bF);

                    if (aSetSize <= bSetSize) {
                        fathermap.put(aF, bF);
                        rankmap.put(bF, aSetSize + bSetSize);
                    } else {
                        fathermap.put(bF, aF);
                        rankmap.put(aF, aSetSize + bSetSize);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        String[] strings = line.split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        unionfindset ufs = new unionfindset(n);
        for (int i = 0; i < m; i++) {
            line = bf.readLine();
            strings = line.split(" ");
            int opt = Integer.parseInt(strings[0]);
            int p = Integer.parseInt(strings[1]);
            int q = Integer.parseInt(strings[2]);
            if (opt == 1) {
                if (ufs.isSameSet(p, q)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            } else if (opt == 2) {
                ufs.union(p, q);
            }
        }
    }
}
