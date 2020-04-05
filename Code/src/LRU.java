import java.util.HashMap;

public class LRU {
    class Node{
        public int key,val;
        public Node last,next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /*
    * 依靠Node类型构建一个双链表
    * 优先级最低的节点是head，最高的是tail
    *
    * 含有三个操作：
    * 1.加入一个新节点，将新加入的节点放在链表的尾部，并将该节点设置为新的尾部
    * 2.该结构的任意节点，都可以分离出来并放到链表的尾部，
    * 3.移除head节点并返回这个节点，将老head的下一个节点设置为新的head;
    * */
    class NodeDoubleList{
        public Node head;
        public Node tail;

        public NodeDoubleList() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node newNode){
            if(newNode == null){
                return;
            }
            if(this.head == null){
                this.head = newNode;
                this.tail = newNode;
            }else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                this.tail = newNode;
            }
        }

        public void moveNodeToTail(Node node){
            if(this.tail == node){
                return;
            }
            if(this.tail == head){
                this.head = head.next;
                this.head.last = null;
            }else {
                node.last.next = node.next;
                node.next.last = node.last;
            }

            //node节点放到尾部的处理
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        public Node removeHead(){
            if(this.head == null){
                return null;
            }
            Node res = this.head;
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            }else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    public class MyCache{
        private HashMap<Integer,Node> map;
        private NodeDoubleList cache;
        private int capacity;

        public MyCache(int capacity) {
            this.map = new HashMap<>();
            this.cache = new NodeDoubleList();
            this.capacity = capacity;
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            Node res = map.get(key);
            cache.moveNodeToTail(res);
            return res.val;
        }

        public void put(int key,int val){
            if(map.containsKey(key)){
                Node node = map.get(key);
                node.val = val;
                cache.moveNodeToTail(node);
            }else {
                Node newNode = new Node(key,val);
                map.put(key,newNode);
                cache.addNode(newNode);
                if(map.size() == this.capacity + 1){
                    //达到容量后，删除链表的头
                    Node removeNode = this.cache.removeHead();
                    map.remove(removeNode.key);
                }
            }
        }
    }
}
