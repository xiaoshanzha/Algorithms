package Stack_Queue;

public class Array_To_Stack_Queue {
    /*
    * 用数组结构实现大小固定的队列和栈
    *
    * 数组实现栈：只需要一个数组和记录数组的size
    * 数组实现队列： 需要一个数组，一个记录数组的size,记录队列的头和尾，数组可循环利用
    *               如果size小于数组的length，则尾指针折叠循环回去指向0位置，还可以进
    *               如果头指针指向了数组的length-1位置，size还大于0，则头指针折叠回去指向0位置，还可出
    * */
    public static class Array_To_Stack{
        private Integer[] arr;
        private Integer size;

        public Array_To_Stack(int initSize) {
            if(initSize < 0){
                //抛出非法形参异常
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
        }
        public Integer peek(){
            if(size == 0){
                return null;
            }
            return arr[size - 1];
        }
        public void push(int obj){
            if(size == arr.length){
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            arr[size++] = obj;
        }
        public Integer pop(){
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            return arr[--size];
        }
    }

    public static class Array_To_Queue{
        private Integer[] arr;
        private Integer size;
        private Integer first;
        private Integer last;
        public Array_To_Queue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
            first = 0;
            last = 0;
        }
        public Integer peek(){
            if (size == 0) {
                return null;
            }
            return arr[first];
        }
        public void push(int obj){
            if(size == arr.length){
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            size++;
            arr[last] = obj;
            last = last == arr.length - 1 ? 0 : last + 1;
        }
        public Integer poll(){
            if(size == 0){
                throw new IndexOutOfBoundsException("The queue is empty");
            }
            size--;
            int tmp = first;
            first = first == arr.length - 1 ? 0 : first + 1;
            return arr[tmp];
        }
    }

    public static void main(String[] args) {

    }
}
