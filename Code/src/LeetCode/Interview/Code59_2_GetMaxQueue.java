package LeetCode.Interview;

import java.util.LinkedList;
import java.util.Queue;

public class Code59_2_GetMaxQueue {
    /*
    * 对列+双端队列来实现
    * push(x): x只要大于双端的末，就从后弹出该值，保证x之前只有比自己大或相等的。
    * */
    private Queue<Integer> data = null;
    private LinkedList<Integer> max = null;
    public Code59_2_GetMaxQueue() {
        data = new LinkedList<>();
        max = new LinkedList<>();
    }

    public int max_value() {
        if(data.isEmpty()){
            return -1;
        }
        return max.peekFirst();
    }

    public void push_back(int value) {
        data.add(value);
        if(max.isEmpty()){
            max.add(value);
        }else {
            while (!max.isEmpty() && value > max.peekLast()){
                max.pollLast();
            }
            max.add(value);
        }
    }

    public int pop_front() {
        if(data.isEmpty()){
            return -1;
        }
        int value = data.poll();
        if(value == max.peekFirst()){
            max.pollFirst();
        }
        return value;
    }

    public static void main(String[] args) {
        Code59_2_GetMaxQueue queue = new Code59_2_GetMaxQueue();
        queue.push_back(1);
        queue.push_back(2);
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
    }
}
