## 栈和队列问题总结

#### 1.栈实现队列，队列实现栈
```java
public class StackAndQueueConvert {
    /*
     * 用两个队列实现栈：data队列和help队列，进栈时，只进data队列。
     *                 出栈时将data队列中的数据留一个，剩下的数据倒在help队列里，
     *                 然后help和data队列互换引用，保持data队列存放数据，help队列为空，作为辅助队列
     *
     * 用两个栈实现队列：push栈和pop栈，进队列时只进push栈，出队列时只从pop栈出
     *                 push栈给pop栈倒东西时，需要一次倒完；
     *                 pop栈有东西时，push栈一定不要倒。不然会发生混乱
     *                 保证pop栈的数据是最先进入push栈的，pop栈的数据用完之后，push栈再将自己的数据全部倒进pop栈
     *
     * 队列是push()和poll();
     * 栈是push()和pop();
     * */
    private static class TwoQueuesStack{
        private Queue<Integer> data;
        private Queue<Integer> help;
        public TwoQueuesStack(){
            data = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }
        public void push(int pushInt){
            data.add(pushInt);
        }
        public int peek(){
            if(data.isEmpty()){
                throw new RuntimeException("Stack is empty");
            }
            while (data.size() != 1){
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        public int pop(){
            if(data.isEmpty()){
                throw new RuntimeException("Stack is empty");
            }
            while (data.size() != 1){
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> tmp = help;
            help = data;
            data = tmp;
        }
    }
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;
        public TwoStacksQueue(){
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }
        public void push(int pushInt){
            stackPush.push(pushInt);
        }
        public int poll(){
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty");
            }else if(stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }
        public int peek(){
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty");
            }else if(stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }
}
```
#### 2.