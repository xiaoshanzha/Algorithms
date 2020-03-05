package LeetCode.ordinary;

import java.util.Stack;

public class Code155_GetMinStack {
    public static class MinStack{
        private Stack<Integer> data;
        private Stack<Integer> min;
        public MinStack() {
            data = new Stack<>();
            min = new Stack<>();
        }

        //方法一：遇到小于等于min.peek()时，才进min栈
       /* public void push(int x) {
            data.push(x);
            if(min.isEmpty()){
                min.push(x);
            }else {
                if(min.peek() >= x){
                    min.push(x);
                }
            }
        }

        public void pop() {
            int x = data.pop();
            if(x == min.peek()){
                min.pop();
            }
        }*/

        public void push(int x) {
            data.push(x);
            if(min.isEmpty()){
                min.push(x);
            }else{
                min.push(Math.min(x,min.peek()));
            }
        }

        public void pop() {
            data.pop();
            min.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }
}
