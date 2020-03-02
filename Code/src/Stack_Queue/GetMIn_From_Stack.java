package Stack_Queue;

import java.util.Stack;

public class GetMIn_From_Stack {
    /*
    * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
    * 要求：1．pop、push、getMin操作的时间复杂度都是O(1)。
    *      2．设计的栈类型可以使用现成的栈结构。
    *
    * 方法1:准备两个栈分别记录data值和栈当前min，newnum进栈时，如果newnum = getmin(),只改变data栈，min栈不改变
    *       数据出栈时，判断出栈的值和getmin()大小，来确定min栈是否弹出数据。
    * 方法2:newnum进栈时，不管newnum的值，min栈和data栈都进行改变，出栈时，min栈和data栈都弹出
    * */
    private static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1(){
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }
        /*
        * 进栈时，如果newNum > 栈中最小值，则栈中最小值不发生变化
        * */
        public void push(int newNum){
            if(this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if(newNum <= this.getmin()){
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        /*
        * 出栈时，取出的值如果等于栈的最小值，则记录最小值的栈 发生改变，栈顶弹出
        * */

        public int pop(){
            if(this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty!");
            }
            int value = this.stackData.pop();
            if(value == this.getmin()){
                this.stackMin.pop();
            }
            return value;
        }
        private int getmin() {
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }


    private static class MyStack2{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(4);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }

}
