import java.util.Scanner;

public class LinkedList{
    Lnode h = null;    //头结点
    //设置头结点
    public void setH(Lnode _h){
        h = _h;
    }
    //头插法
    /*
    public LinkedList(){
        char ch;
        Lnode p;
        h = new Lnode();
        h.next = null;
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int i = 0;
        while(i<str.length()){
            ch = str.charAt(i);
            p = new Lnode();
            p.data = ch;
            p.next = h.next;
            h.next = p;
            i++;
        }
    }
    */
    //尾插法
    public LinkedList(){
        char ch;
        h = new Lnode();
        h.next = null;
        // p为新结点，last一直为链表的尾结点。
        Lnode p,last;
        last = h;
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int i = 0;
        while(i<str.length()){
            ch = str.charAt(i);
            p = new Lnode();
            p.data = ch;
            p.next = null;
            last.next = p;
            last = p;
            i++;
        }
    }
    //求链表长度
    public int length(){
        Lnode p;
        int i = 0;
        p = h.next;
        while(p!=null){
            i++;
            p = p.next;
        }
        return i;
    }
    //p结点之后插入新结点
    public void insertElementAfter(Lnode p,char x){
        Lnode t = new Lnode();
        t.data = x;
        t.next = p.next;
        p.next = t;
    }
    //在第i个位置插入新节点
    int insertElementAt(int i,char x){
        Lnode p,t;
        p = h;
        int j = 0;
        //先寻找第i-1号结点
        while(p.next!=null && j<i-1){
            p = p.next;
            j++;
        }
        if(p!=null && j==i-1){
            t = new Lnode();
            t.data = x;
            t.next = p.next;
            p.next = t;
            return 1;  //返回1表示插入成功
        }
        return 0;  //返回0表示插入失败
    }
    //按值查找
    public Lnode search(char x){
        Lnode p;
        p = h.next;
        while(p!=null&&p.data!=x){
            p = p.next;
        }
        return p;
    }
    //读取第i个元素
    public Lnode  get(int i){
        Lnode p;
        p = h.next;
        int j = 1;
        while(p!=null && j<i){
            p = p.next;
            j++;
        }
        if(i==j){
            return p;
        }else{
            return null;
        }
    }
    public void print(){
        int len = length();
        Lnode p = h.next;
        for(int i = 0;i<len;i++){
            System.out.println(p.data);
            p = p.next;
        }
    }
}
