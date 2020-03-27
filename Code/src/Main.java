import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int k = Integer.parseInt(strings[2]);
        int index = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t0, Integer t1) {
                return t1 - t0;
            }
        });
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(((i - 1) * m + j )<= k){
                    queue.add( i * j);
                }else {
                    if(queue.peek() > (i * j)){
                        queue.poll();
                        queue.add(i * j);
                    }
                }
                if(queue.size() == k &&(i * j) >= queue.peek()){
                    break;
                }
            }
        }
        System.out.println(queue.peek());
    }
}
