import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
* 牛课题：项目最大收益。
* 注意W为long型。不然会越界
* */
public class Max_Profit {
    public static class Program{
        public int cost;
        public int profit;
        public Program(int cost,int profit){
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class CostMinComp implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static long getMaxMoney(long W,int K,int[] costs,int[] profits){
        if(W < 1 || K <  0 || costs == null || profits == null || costs.length != profits.length){
            return W;
        }
        PriorityQueue<Program> costMinHeap = new PriorityQueue<>(new CostMinComp());
        //也可以直接在里面写比较器
        PriorityQueue<Program> profitMaxHeap = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o2.profit - o1.profit;
            }
        });
        for (int i = 0; i < costs.length; i++) {
            costMinHeap.add(new Program(costs[i],profits[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!costMinHeap.isEmpty() && costMinHeap.peek().cost <= W){
                profitMaxHeap.add(costMinHeap.poll());
            }
            if(profitMaxHeap.isEmpty()){
                return W;
            }
            W += profitMaxHeap.poll().profit;
        }
        return W;
    }

    public static void main(String[] args) throws IOException {
        int max1Value = 1000000000;
        int max2Value = 100000;
        int n =  (int) ((max2Value + 1) * Math.random());
        int w =  (int) ((max1Value + 1) * Math.random());
        int k =  (int) ((max2Value + 1) * Math.random());
        System.out.println(n +" "+ w +" "+ k);
        generateArray(n,max1Value);


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int N = Integer.parseInt(strings[0]);
        long W = Integer.parseInt(strings[1]);
        int K = Integer.parseInt(strings[2]);
        String[] aa  = bf.readLine().split(" ");
        String[] bb  = bf.readLine().split(" ");
        int[] costs = new int[N];
        int[] profits = new int[N];
        for(int i = 0;i < N; i++){
            costs[i] = Integer.parseInt(aa[i]);
            profits[i] = Integer.parseInt(bb[i]);
        }
        System.out.println(getMaxMoney(W,K,costs,profits));
    }


    //生成长度为N的整型数组
    private static void generateArray(int N, int maxValue) {
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = (int) ((maxValue + 1) * Math.random());
            arr2[i] = (int) ((maxValue + 1) * Math.random());
        }
        for (int i = 0; i < N; i++) {
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(arr2[i]+" ");
        }
        System.out.println();
    }
}
